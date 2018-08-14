package cn.zz.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSinglePoolScheduledFix {
	public static void main(String[] args) {
		//设置一个定时任务，1秒后执行
		Executors.newScheduledThreadPool(1).schedule(new Runnable() {

			@Override
			public void run() {
				System.out.println("定时任务1");
			}
		}, 1, TimeUnit.SECONDS);
	}
}
