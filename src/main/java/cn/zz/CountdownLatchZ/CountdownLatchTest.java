package cn.zz.CountdownLatchZ;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		CountDownLatch order = new CountDownLatch(1);
		CountDownLatch answer = new CountDownLatch(3);
		
		for(int i=0;i<3;i++) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println("线程" + Thread.currentThread().getName() + 
								"正准备接受命令");
						//主线程发 order,子线程才开始执行
						order.await();
						System.out.println("线程" + Thread.currentThread().getName() + 
								"已接受命令");								
								Thread.sleep((long)(Math.random()*10000));	
								System.out.println("线程" + Thread.currentThread().getName() + 
										"回应命令处理结果");
						//三个线程都执行了answer计数减到0,主线程继续执行
						answer.countDown();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
		try {
			Thread.sleep((long)(Math.random()*10000));
		
			System.out.println("线程" + Thread.currentThread().getName() + 
					"即将发布命令");						
			order.countDown();
			System.out.println("线程" + Thread.currentThread().getName() + 
			"已发送命令，正在等待结果");	
			answer.await();
			System.out.println("线程" + Thread.currentThread().getName() + 
			"已收到所有响应结果");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
