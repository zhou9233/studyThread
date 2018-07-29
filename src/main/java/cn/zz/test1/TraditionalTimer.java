package cn.zz.test1;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimer {

	public static void main(String[] args) {
		/*
		 * new Timer().schedule(new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("boom");
		 * 
		 * } }, 10000,3000);
		 */
		/*
		 * new Timer().schedule(new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("1 boom"); new
		 * Timer().schedule(new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("2 boom");
		 * 
		 * 
		 * } }, 2000);
		 * 
		 * } }, 2000);
		 */
		class MyTimerTask extends TimerTask {

			@Override
			public void run() {
				System.out.println("boom");
				new Timer().schedule(new MyTimerTask(), 2000);
			}

		}
		new Timer().schedule(new MyTimerTask(), 2000);
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
