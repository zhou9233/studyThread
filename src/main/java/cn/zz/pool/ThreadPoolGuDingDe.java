package cn.zz.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolGuDingDe {
	public static void main(String[] args){
		//固定的线程池，三个线程
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for(int i =1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(int j=1;j<=10;j++){
						System.out.println(
								Thread.currentThread().getName()+" "+
								j+" task "+task);
					}
					
				}
			});
		}
		System.out.println("十个任务全部提交");
		//所有的任务执行完了结束线程池
		threadPool.shutdown();
		System.out.println("所有的任务执行完了结束线程池，这句话在任务结束前已经打印");
	}
}
