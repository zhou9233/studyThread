package cn.zz.pool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现主线程和子线程依次执行，主线程执行完，通知子线程执行， 子线程执行完，通知主线程执行
 * @author Administrator
 *
 */
public class ConditionCommunication {
	public static void main(String[] args) {
		BUS bus = new BUS();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					bus.sub(i);
				}
			}
		}).start();
		
		for(int i=1;i<=50;i++){
			bus.main(i);
		}
	}
	
	
}

class BUS {
	//设置一个锁
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	private boolean bShouldSub = false;
	//子线程逻辑
	public void sub (int i) {
		//lock保证互斥
		lock.lock();
		try {
			//防止虚假唤醒
			while (!bShouldSub) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int j=1;j<=10;j++){
				System.out.println("sub thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = false;
			condition.signal();
		}finally {
			lock.unlock();
		}
	}
	
	//主线程逻辑
	public void main(int i) {
		lock.lock();
		try {
			while(bShouldSub) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int j=1;j<=10;j++){
				System.out.println("main thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = true;
			condition.signal();
		}finally {
			lock.unlock();
		}
	}
	
	
}













