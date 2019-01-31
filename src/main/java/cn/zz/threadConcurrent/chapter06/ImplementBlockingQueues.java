package cn.zz.threadConcurrent.chapter06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ImplementBlockingQueues {
    /**
     * jdk使用通知这模式实现阻塞队列
     * 当生产者往满的队列里添加元素时会阻塞住生产者
     * 当消费者消费了一个队列中的元素后，会通知生产者当前队列可用
     */
    final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    final Object[] items;
    private volatile int count;
    public ImplementBlockingQueues(int capacity,boolean fair){
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }
    public void put(String e) {
        final ReentrantLock lock = this.lock;
        try {
            lock.lockInterruptibly();
            while (count == 0) {
                notFull.await();
            }
            insert(e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public String take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0){
                notEmpty.await();
            }
            return extract();
        } finally {
            lock.unlock();
        }

    }

    public String extract(){
        return null;
    }

    public void insert(String x) {
        //......
        notEmpty.signal();
    }
}
