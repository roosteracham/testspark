package basic.concurrency.threadpool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);
        MyExecutorThreadPool pool = new MyExecutorThreadPool(availableProcessors, availableProcessors,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new DefaultThreadFactory("test"));

        for (int i = 0; i < 10; i++) {
            int j = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ", " + j);
            });
        }
        pool.shutdown();
    }
}
