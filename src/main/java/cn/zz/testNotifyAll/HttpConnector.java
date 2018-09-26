package cn.zz.testNotifyAll;

import java.util.Scanner;
import java.util.Stack;


public class HttpConnector implements Runnable{
	
	private Thread thread = null;
	
	private String threadName = null;
	
	private int curProcessors = 0;
	
	protected int minProcessors = 5;
	
	private Stack processors = new Stack();
	
	private int processId = 1;
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("----------------唤醒processor-------------------");
			HttpProcessor processor = createProcessor();
			processor.assign();
		}
		
	}
	
	private HttpProcessor createProcessor() {
		synchronized (processors) {
			if (processors.size() > 0) {
				return (HttpProcessor) processors.pop();
			} else {
				return newProcessor();
			}
		}
	}
	
	private HttpProcessor newProcessor() {
		HttpProcessor processor = new HttpProcessor(this,processId++);
		processor.start();
		return processor;
	}
	
	private void threadStart() {
		thread = new Thread(this,threadName);
		thread.setDaemon(true);
		thread.start();
	}
	
	public void start() {
		threadName = "HttpConnector[8080]";
		threadStart();
		while (curProcessors < minProcessors) {
			HttpProcessor processor = newProcessor();
			recycle(processor);
			curProcessors ++;
		}
	}
	
	public void recycle(HttpProcessor processor) {
		processors.push(processor);
	}
	
}
