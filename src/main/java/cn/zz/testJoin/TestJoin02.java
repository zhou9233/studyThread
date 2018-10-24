package cn.zz.testJoin;


import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class TestJoin02 {
    //使用通道中断线程
    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\websocketBim\\1441795\\tree.json");
        ByteChannel channel = fis.getChannel();
        Sleeper sleeper = new Sleeper(channel);
        sleeper.start();
        //中断获取通道的线程也会是通道关闭
        sleeper.interrupt();
        sleeper.join();
        System.out.println("main");
        System.out.println(channel.isOpen());
    }

    private static class Sleeper extends Thread {

        private ByteChannel channel;

        public Sleeper(ByteChannel channel) {
            this.channel = channel;
        }

        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            try {
                //这里是阻塞的
                int n = this.channel.read(buffer);
                System.out.println("123");

            } catch (Throwable e) {
                System.out.println("caught " + e);
            } finally {
                System.out.println("exiting, "
                        + "interrupted=" + isInterrupted());
            }
        }
    }
}
