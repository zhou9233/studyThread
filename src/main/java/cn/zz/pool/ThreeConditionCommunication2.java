package cn.zz.pool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sub1唤醒sub2,sub2唤醒sub3,sub3唤醒sub1
 * 注意 sub1 sub2 sub3的线程数量必须相等
 * @author Administrator
 *
 */
public class ThreeConditionCommunication2 {
	public static void main(String[] args) {
		Three three = new Three();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					three.sub1(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					three.sub2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					three.sub3(i);
				}
			}
		}).start();
	}
	
	
}

class Three {
	//设置一个锁
	Lock lock = new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();
	private int subNum = 1;
	//子线程逻辑
	public void sub1 (int i) {
		//lock保证互斥
		lock.lock();
		try {
			//防止虚假唤醒
			while (subNum != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int j=1;j<=10;j++){
				System.out.println("subNum:"+subNum+" sub thread sequence of " + j + ",loop of " + i);
			}
			subNum = 2;
			condition2.signal();
		}finally {
			lock.unlock();
		}
	}
	
	public void sub2 (int i) {
		//lock保证互斥
		lock.lock();
		try {
			//防止虚假唤醒
			while (subNum != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int j=1;j<=10;j++){
				System.out.println("subNum:"+subNum+" sub thread sequence of " + j + ",loop of " + i);
			}
			subNum = 3;
			condition3.signal();
		}finally {
			lock.unlock();
		}
	}
	
	public void sub3 (int i) {
		//lock保证互斥
		lock.lock();
		try {
			//防止虚假唤醒
			while (subNum != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int j=1;j<=10;j++){
				System.out.println("subNum:"+subNum+" sub thread sequence of " + j + ",loop of " + i);
			}
			subNum = 1;
			condition1.signal();
		}finally {
			lock.unlock();
		}
	}
	
}













