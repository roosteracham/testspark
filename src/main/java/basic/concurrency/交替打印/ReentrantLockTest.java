package basic.concurrency.交替打印;

import basic.Threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 3; i++) {
            Threads.startThread(() -> {
                try {
                    lock.lock();
                    TimeUnit.MINUTES.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
        }
    }
}
