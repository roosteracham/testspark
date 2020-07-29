package basic.concurrency.同步工具类;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量实现互斥锁
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        testSemaphore();
    }

    private static void testSemaphore() {
        Semaphore semaphore = new Semaphore(2);
        new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphore.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphore.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphore.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphore.release();
        }).start();
    }
}
