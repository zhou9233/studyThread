package cn.zz.test1;

public class TraditionalThread {
	
	public static void main(String[] args) {
		//继承重写run()
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName());
					System.out.println(this.getName());
				}
				
			}
		};
		//thread1.start();
		//实现Runnable接口
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					//Thread.currentThread()得到当前的线程对象
					System.out.println(Thread.currentThread().getName());
					//this不代表线程
					//System.out.println(this.getName());
				}
			}
		});
		//thread2.start();
		//第二种更能体现面向对象，线程所运行代码在对象里面
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Runnable");
				
			}
		}) {
			@Override
			public void run() {
				System.out.println("Thread");
			}
		}.start();
		//输出Thread
	}
	
}
