package cn.zz.test1;

public class TraditionalThreadSyn2 {
	public static void main(String[] args) {
		new TraditionalThreadSyn2().init();
	}

	public void init() {
		Outer outer = new Outer();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					/*
					 * try { Thread.sleep(10); } catch (InterruptedException e)
					 * { // TODO Auto-generated catch block e.printStackTrace();
					 * }
					 */
					outer.log("nike");

				}

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					/*
					 * try { Thread.sleep(10); } catch (InterruptedException e)
					 * { // TODO Auto-generated catch block e.printStackTrace();
					 * }
					 */
					outer.log("lining");

				}

			}
		}).start();
	}

	// 互斥
	class Outer {
		//直接在方法上加互斥也可以
		public synchronized void log(String log) {
			for (int i = 0; i < log.length(); i++) {
				System.out.print(log.charAt(i));
			}
			System.out.println();

		}
	}
}
