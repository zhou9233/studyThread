package cn.zz.test1;

public class CloseOneThread {
    private int i = 10;
    public static void main(String[] args) {
        new CloseOneThread().test();
        while (true) {

        }
    }
    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1开始");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            System.out.println(i++);
                            System.out.println("线程2开始");
                        }
                    }
                }).start();
                System.out.println("线程1结束");
            }
        }).start();
    }
}
