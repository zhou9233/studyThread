package cn.zz.testNotifyAll;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpProcessor implements Runnable {

	private Thread thread = null;

	private String threadName = null;

	private boolean available = false;
	
	private HttpConnector connector = null;

	/**
	 * The server socket through which we listen for incoming TCP connections.
	 */
	private ServerSocket serverSocket = null;

	public HttpProcessor(HttpConnector connector, int id) {
		this.connector = connector;
		this.threadName = "HttpProcessor[" + id + "]";
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("唤醒所有" + Thread.currentThread().getName());
			await();
			System.out.println(Thread.currentThread().getName());
			connector.recycle(this);
		}

	}

	synchronized void assign() {
		while (available) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		available = true;
		notifyAll();
	}

	public synchronized void await() {
		while (!available) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		available = false;
		notifyAll();
	}

	private void threadStart() {
		thread = new Thread(this, threadName);
		thread.setDaemon(true);
		thread.start();
	}

	public void start() {
		threadStart();
	}

}
