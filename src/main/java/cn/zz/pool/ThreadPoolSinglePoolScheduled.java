package cn.zz.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSinglePoolScheduled {
	public static void main(String[] args) { 
		//启动三个线程，只设置一个定时任务，那个线程有空那个线程执行，1秒后执行,之后每2秒执行一次
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("定时任务 "+Thread.currentThread().getName());
			}
		}, 1,2, TimeUnit.SECONDS);
	}
}
