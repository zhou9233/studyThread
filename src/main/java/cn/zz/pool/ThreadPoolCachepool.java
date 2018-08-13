package cn.zz.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolCachepool {
	public static void main(String[] args){
		//缓存线程池 根据任务的数量创建线程数，不确定有多少
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for(int i =1;i<=50;i++){
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
