package cn.zz.test1;

public class SynOneObject {
    public static void main(String[] args) {
        SynOneObject synOneObject = new SynOneObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synOneObject.sayHi();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                synOneObject.sayHello();
            }
        }).start();
    }

    public void sayHi() {
        synchronized (this) {
            System.out.println("hi");
            while (true) {

            }
        }
    }

    public void sayHello() {
        synchronized (this) {
            System.out.println("hello");
        }
    }
}
