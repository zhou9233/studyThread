package cn.zz.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

//可冲入锁  /riː'entrənt/
public class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();
    public void write(){
        lock.lock(); //获取锁
        try {
            a++;
        } finally {
            lock.unlock();//释放锁
        }
    }

    public void reader () {
        lock.lock(); //获取锁
        try {
            int i = a;
        } finally {
            lock.unlock();//释放锁
        }
    }


}
