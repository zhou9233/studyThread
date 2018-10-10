package cn.zz.testNotifyAll;

import java.net.ServerSocket;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class HttpConnector implements Runnable {

    private Thread thread = null;

    private String threadName = null;

    private int curProcessors = 0;

    protected int minProcessors = 5;

    private int maxProcessors = 20;

    private Stack processors = new Stack();

    /**
     * Has this component been initialized yet?
     */
    private boolean initialized = false;

    /**
     * The server socket through which we listen for incoming TCP connections.
     */
    private ServerSocket serverSocket = null;

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            try {
                Thread.sleep(rand.nextInt(1));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------------唤醒processor-------------------");
            HttpProcessor processor = createProcessor();
            if (processor == null) {
                continue;
            }
            processor.assign();
        }

    }

    private HttpProcessor createProcessor() {
        synchronized (processors) {
            if (processors.size() > 0) {
                return (HttpProcessor) processors.pop();
            }
            if ((maxProcessors > 0) && (curProcessors < maxProcessors)) {
                return (newProcessor());
            } else {
                if (maxProcessors < 0) {
                    return newProcessor();
                } else {
                    return null;
                }

            }
        }
    }

    private HttpProcessor newProcessor() {
        HttpProcessor processor = new HttpProcessor(this, curProcessors++);
        processor.start();
        return processor;
    }

    private void threadStart() {
        thread = new Thread(this, threadName);
        thread.setDaemon(true);
        thread.start();
    }

    public void initialize() throws IllegalAccessException{
        if (initialized) {
            throw  new IllegalAccessException("HttpConnector 已经初始化过了");
        }
        this.initialized = true;
        serverSocket = open();

    }

    private ServerSocket open() {
        return null;
    }

    public void start() {
        threadName = "HttpConnector[8080]";
        threadStart();
        while (curProcessors < minProcessors) {
            HttpProcessor processor = newProcessor();
            recycle(processor);
            curProcessors++;
        }
    }

    public void recycle(HttpProcessor processor) {
        processors.push(processor);
    }

}
