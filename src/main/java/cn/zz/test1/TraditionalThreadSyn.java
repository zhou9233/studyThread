package cn.zz.test1;

public class TraditionalThreadSyn {
	public static void main(String[] args) {
		new TraditionalThreadSyn().init();
	}

	public void init() {
		Outer outer = new Outer();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					/*try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					outer.log("nike");

				}

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					/*try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					outer.log("lining");

				}

			}
		}).start();
	}
	//互斥
	class Outer {
		String syn = "";
		public void log(String log) {
			//同一个对象
			synchronized (syn) {
				for (int i = 0; i < log.length(); i++) {
					System.out.print(log.charAt(i));
				}
				System.out.println();
			}
			//用this也可以
			synchronized (this) {
				for (int i = 0; i < log.length(); i++) {
					System.out.print(log.charAt(i));
				}
				System.out.println();
			}
			
		}
	}
}
