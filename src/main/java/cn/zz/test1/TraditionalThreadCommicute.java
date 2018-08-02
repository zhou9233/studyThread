package cn.zz.test1;

public class TraditionalThreadCommicute {
	public static void main(String[] args) {
		Outer outer = new Outer();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					outer.sub();
				}

			}
		}).start();
		for (int i = 0; i < 50; i++) {
			outer.main();
		}

	}

}

class Outer {
	private Boolean runMain = true;

	public synchronized void main() {
		while (!runMain) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("main  " + i);
		}
		runMain = false;
		this.notify();
	}

	public synchronized void sub() {
		while (runMain) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 5; i++) {
			System.out.println("sub  " + i);
		}
		runMain = true;
		this.notify();

	}
}
