package cn.zz.threadConcurrent.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10-20
 */
public class ConditionUseCase {
    Lock      lock      = new ReentrantLock();
    Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ConditionUseCase conditionUseCase = new ConditionUseCase();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    conditionUseCase.conditionWait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionWait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionWait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionWait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionWait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        Thread.sleep(5000);
        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionSignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread6.start();
    }

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" start await");
            condition.await();
            System.out.println(Thread.currentThread().getName()+" end await");
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
