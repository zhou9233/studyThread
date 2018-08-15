package cn.zz.pool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockTest {

	public static void main(String[] args) {
		new LockTest().init();
	}
	
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
				
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("lihuoming");
				}
				
			}
		}).start();
		
	}

	static class Outputer{
		//lock必须写在这里，Outputer对象必须是同一个，当前lock才会生效
		Lock lock = new ReentrantLock();
		public void output(String name){
			//写在这里相当于每次都创建一个新锁，不能到达互斥
			//Lock lock = new ReentrantLock();
			int len = name.length();
			lock.lock();
			try{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}finally{
				lock.unlock();
			}
		}
		
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}	
	}
}

