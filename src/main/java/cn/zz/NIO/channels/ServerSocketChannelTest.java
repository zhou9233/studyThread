package cn.zz.NIO.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerSocketChannelTest {
    private int size = 1024;
    private ServerSocketChannel socketChannel;
    private ServerSocketChannel socketChannel2;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private final int port = 1234;
    private int remoteClientNum = 0;

    public ServerSocketChannelTest() {
        try {
            initChannel();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void initChannel() throws Exception {
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(port));
        System.out.println("listener on port:" + port);
        selector = Selector.open();
        //一个选择器可以注册多个可选择通道
        /*socketChannel2 = ServerSocketChannel.open();
        socketChannel2.configureBlocking(false);
        socketChannel2.bind(new InetSocketAddress(2234));
        socketChannel2.register(selector, SelectionKey.OP_ACCEPT);*/

        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //selector.wakeup();
        byteBuffer = ByteBuffer.allocateDirect(size);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    private void listener() throws Exception {
        /*Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    selector.select();
                    System.out.println("选择结束1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread.sleep(100);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    selector.select();
                    System.out.println("选择结束2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
        Thread.sleep(1000);*/
        while (true) {
            //selector.wakeup();//将使下次调用select()方法立即返回，下次不再阻塞

            int n = selector.select();
            //selector.wakeup(); 立即唤醒第一个还没有返回的选择器操作
            //selector.close();//选择器相关通道将注销，键取消，直接抛异常
            //thread1.interrupt();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //a connection was accepted by a ServerSocketChannel.
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    remoteClientNum++;
                    System.out.println("online client num=" + remoteClientNum);
                    replyClient(channel);
                }
                //a channel is ready for reading
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                ite.remove();//must
            }
        }
    }

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            // Make buffer readable // Send the data; don't assume it goes all at once
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();
            // Empty buffer
        }
        /*if (count < 0) {
            socketChannel.close();
        }*/
        //测试一个通道关闭，它相关的键取消
        socketChannel.close();
    }

    private void replyClient(SocketChannel channel) throws IOException {
        byteBuffer.clear();
        byteBuffer.put("hello client!\r\n".getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    private void registerChannel(Selector selector, SocketChannel channel, int ops) throws Exception {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    public static void main(String[] args) {
        try {
            new ServerSocketChannelTest().listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
