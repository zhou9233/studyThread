package cn.zz.LockSupportTest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class ParkAndUnPartTest {
    private static final sun.misc.Unsafe UNSAFE = getUnsafe();
    private static Unsafe getUnsafe(){

        Class<?> unsafeClass = Unsafe.class;

        for (Field f : unsafeClass.getDeclaredFields()) {

            if ("theUnsafe".equals(f.getName())) {

                f.setAccessible(true);

                try {
                    return (Unsafe) f.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
        return null;
        //throw new IllegalAccessException("no declared field: theUnsafe");

    }
    public static void main(String[] args) {
        Boolean flag = true;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(System.currentTimeMillis());
                    if (flag) {
                        //LockSupport.park(flag);
                        UNSAFE.park(false, 0L);
                    }
                }
                    /*System.out.println("t1睡醒了");*/

            }
        });
        t1.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        //UNSAFE.unpark(t1);
    }
}
