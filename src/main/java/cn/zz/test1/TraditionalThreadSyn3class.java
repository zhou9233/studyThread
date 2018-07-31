package cn.zz.test1;


public class TraditionalThreadSyn3class {
	public static void main(String[] args) {
		new TraditionalThreadSyn3class().init();
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
					outer.log2("lining");

				}

			}
		}).start();
	}

	// 互斥 同一个对象 类
	static class Outer {
		//直接在方法上加互斥也可以
		public  void log(String log) {
			synchronized (Outer.class) {
				for (int i = 0; i < log.length(); i++) {
					System.out.print(log.charAt(i));
				}
				System.out.println();
			}
			

		}
		public static synchronized void log2(String log) {
			for (int i = 0; i < log.length(); i++) {
				System.out.print(log.charAt(i));
			}
			System.out.println();

		}
	}
}
