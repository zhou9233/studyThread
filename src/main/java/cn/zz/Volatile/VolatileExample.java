package cn.zz.Volatile;

public class VolatileExample {
    int a = 0;
    volatile boolean flag = false;
    volatile int test = 0;
    public void write() {
        //当第一个操作为普通变量的读或写时，如果第二个操作为volatile写，则编译器不能重排序这两个操作
        a = 1;
        flag = true;
        //当第一个操作为volatile写的时候，第二个操作为volatile读写是，不能重排序
        if (test == 0) {
            test = 1;
        }
    }

    public void reader() {
        //当一个操作是volatile读的时候，第二个操作都不能重排序
        if (flag) {
            int i = a;
            if (i ==0){
                System.out.println(i);
            }
        }
    }


    public static void main(String[] args) {
        while (true) {
            VolatileExample volatileExample = new VolatileExample();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileExample.write();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileExample.reader();
                }
            }).start();

        }


    }
}
