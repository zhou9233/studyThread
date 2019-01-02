package cn.zz.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaiingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
        new Thread(new Muilt(),"MuiltThread").start();
    }
    static class TimeWaiting implements Runnable {
        @Override
        public void run(){
            while (true) {
                SleepUtils.second(100);
            }
        }
    }
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
    static class Muilt implements Runnable {
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取java线程管理器MXBean
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
            ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
            //遍历线程信息，仅打印线程ID和线程名称信息
            for (ThreadInfo threadInfo:threadInfos) {
                System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
            }
        }
    }
}
