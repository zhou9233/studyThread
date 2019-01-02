package cn.zz.happensbefore;

public class VolatileHappensBefore {
    private int a = 0;
    private volatile boolean flag = false;
    public void write() {
        a = 1;
        flag = true;
    }

    public void read() {
        while (true) {
            if (flag) {
                System.out.println(a);
                break;
            }
        }
    }

    public static void main(String[] args) {
        VolatileHappensBefore volatileHappensBefore = new VolatileHappensBefore();
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileHappensBefore.read();
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
                volatileHappensBefore.write();
            }
        }).start();

    }

}
