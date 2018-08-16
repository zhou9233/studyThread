package cn.zz.pool;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.swing.plaf.SliderUI;

public class ReadWriteLockTest {
	public static void main(String[] args){
		Queue queue = new Queue();
		for(int i=0;i<30;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					queue.get();
					
				}
			}).start();
			
new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					queue.put(new Random().nextInt(5000));
					
				}
			}).start();
		}
	}
}
 
class Queue{
	private Object data;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	/*
	 * 读数据的方法，加读锁
	 */
	public void get(){
		lock.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + "have read data :" + data);			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			lock.readLock().unlock();
		}
	}
	/*
	 * 写数据方法，设置写锁
	 */
	public void put(Object data){
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");					
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;		
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.writeLock().unlock();
		}
	}
}
