package cn.zz.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableAndFutureCompletionService {
	public static void main(String[] args) {
		ExecutorService threadPool =  Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = 
				new ExecutorCompletionService<>(threadPool);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
				
			});
		}
		for (int i=0;i<10;i++){
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
