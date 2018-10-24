package cn.zz.NIO.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

public class SocketChannelTest2 {
    private int size = 1024;
    private ByteBuffer byteBuffer;
    private SocketChannel socketChannel;

    public void connectServer() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 1234));
        byteBuffer = ByteBuffer.allocate(size);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        //receive();
        byteBuffer.putInt(20);
        send(byteBuffer.array());
        while (true) {

        }
    }

    private void receive() throws IOException {
        while (true) {
            int count;
            byteBuffer.clear();
            while ((count = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
//send("send data to server\r\n".getBytes());
                byteBuffer.clear();
            }
        }
    }

    private void send(byte[] data) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(data);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException {
        new SocketChannelTest2().connectServer();
    }
}
