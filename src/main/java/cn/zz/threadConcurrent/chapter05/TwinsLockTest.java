/**
 *
 */
package cn.zz.threadConcurrent.chapter05;

import cn.zz.threadConcurrent.chapter04.SleepUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.Lock;


/**
 * 10-11
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        TwinsLockTest twinsLockTest = new TwinsLockTest();
        twinsLockTest.test();
    }

    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 5; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 100; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}

