package basic.concurrency.同步工具类;

import java.util.concurrent.Semaphore;

/**
 * 信号量实现互斥锁
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        testSemaphore();
    }

    private static void testSemaphore() {
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphore.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphore.release();
        }).start();
    }
}
