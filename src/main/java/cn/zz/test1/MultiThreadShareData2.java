package cn.zz.test1;

public class MultiThreadShareData2 {

	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					shareData.inc();
				}
				

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					shareData.des();
				}

			}
		}).start();
	}
}

class ShareData {
	private int j = 10;

	public synchronized void inc() {
		j++;
		System.out.println(j);
	}

	public synchronized void des() {
		j--;
		System.out.println(j);
	}
}
