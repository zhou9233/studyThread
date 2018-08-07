package cn.zz.test1;

public class MultiThreadShareData {
	private static ShareData1 data1 = new ShareData1();

	public static void main(String[] args) {
		ShareData1 data1 = new ShareData1();
		new Thread(data1).start();
		new Thread(data1).start();

	}
}

class ShareData1 implements Runnable {
	private int count = 100;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			count--;
			System.out.println(Thread.currentThread().getName()+" "+count);
		}
	}
}