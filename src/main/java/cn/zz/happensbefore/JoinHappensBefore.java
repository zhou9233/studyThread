package cn.zz.happensbefore;

public class JoinHappensBefore {
    private int a = 0;
    public static void main(String[] args) {


        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B start");
                try {
                    System.out.println("Thread B sleep");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread B end");
            }
        });

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A start");
                try {
                    System.out.println("Thread A B.join()");
                    B.start();
                    B.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread A end");
            }
        });
        A.start();

    }
}
