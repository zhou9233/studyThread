package cn.zz.test1;

import java.util.Random;

public class ThreadLocalTest2 {

    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

    public static void main(String[] args) {

        Thread thread1 =
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int data = new Random().nextInt();
                        System.out.println(Thread.currentThread().getName() +
                                ": " + data);
                        x.set(data);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int data2 = x.get();
                        System.out.println(Thread.currentThread().getName() +
                                ": " + data2);
                    }
                });
        Thread thread2 =
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int data = new Random().nextInt();
                        System.out.println(Thread.currentThread().getName() +
                                ": " + data);
                        x.set(data);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int data2 = x.get();
                        System.out.println(Thread.currentThread().getName() +
                                ": " + data2);
                    }
                });
        Thread thread3 =
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int data = new Random().nextInt();
                        System.out.println(Thread.currentThread().getName() +
                                ": " + data);
                        x.set(data);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int data2 = x.get();
                        System.out.println(Thread.currentThread().getName() +
                                ": " + data2);
                    }
                });
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
