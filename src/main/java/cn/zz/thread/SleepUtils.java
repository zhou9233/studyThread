package cn.zz.thread;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}
