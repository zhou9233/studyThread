package cn.zz.thread;

public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }
    static class DaemonRunner implements Runnable {
        public void run() {
            SleepUtils.second(100000);
        }
    }
}
