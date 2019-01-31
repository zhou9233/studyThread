package cn.zz.threadConcurrent.chapter05;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName()+" "+"Start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
                System.out.println(Thread.currentThread().getName()+" "+"end");
            }
        });
        thread1.start();
        Thread.sleep(2000);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName()+" "+"Start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
                System.out.println(Thread.currentThread().getName()+" "+"end");
            }
        });
        thread2.start();
    }
}
