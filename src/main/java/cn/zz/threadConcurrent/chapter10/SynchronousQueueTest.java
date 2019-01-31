package cn.zz.threadConcurrent.chapter10;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<String> strings = new SynchronousQueue<String>();
        strings.offer("123");
        System.out.println("123");
        System.out.println(strings.poll());
        strings.offer("234");
        System.out.println("234");
        System.out.println(strings.poll());
        System.out.println(strings.poll());
    }
}
