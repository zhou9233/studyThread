package cn.zz.CyclicBarrierZ;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 玩家排队，每拍够5个，进入游戏
 * @author Administrator
 *
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        final CyclicBarrier barrier = new CyclicBarrier(5);
        //这里准备了32个线程,所以最终会有两个线程一直等待
        for (int i = 0; i < 32; i++) {
            service.execute(new Player("玩家" + i, barrier));
        }
        service.shutdown();
    }
}

class Player implements Runnable {
    private final String name;
    private final CyclicBarrier barrier;

    public Player(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
            System.out.println(name + "已准备,等待其他玩家准备...");
            barrier.await();
            TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
            System.out.println(name + "已加入游戏");
        } catch (InterruptedException e) {
            System.out.println(name + "离开游戏");
        } catch (BrokenBarrierException e) {
            System.out.println(name + "离开游戏");
        }

    }
}
