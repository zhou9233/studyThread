package cn.zz.threadConcurrent.chapter06;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkEdQueueTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
        //入队
        concurrentLinkedQueue.offer("123");
        concurrentLinkedQueue.offer("234");
        concurrentLinkedQueue.offer("345");
        concurrentLinkedQueue.poll();
        concurrentLinkedQueue.poll();
        concurrentLinkedQueue.poll();
        concurrentLinkedQueue.poll();
        /*Object tail = new Object();
        Object head = new Object();
        Object t = tail;
        Object p = null;
        p = (t != (t = tail)) ? t : head;*/
        //p = (t != (t = tail)) ? t : head;
    }
}
