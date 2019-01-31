package cn.zz.threadConcurrent.chapter05;

public class MutexTest {
    public static void main(String[] args) {
        Mutex mutex = new Mutex();

        for (int i = 0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mutex.lock();
                    try {
                        Thread.sleep(100000);
                        System.out.println(Thread.currentThread().getName()+" : "+System.nanoTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mutex.unlock();
                    }

                }
            }).start();

        }
    }
}
