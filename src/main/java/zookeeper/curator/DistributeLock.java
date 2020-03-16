package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class DistributeLock {

    public static void main(String[] args) {
        withDistributeLock();
    }

    private static void oriGenerateOrder() {
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (Exception e) {

                    }
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss|SSS");
                    String s = format.format(new Date());
                    System.out.println("订单：" + s);
                }
            }).start();
            latch.countDown();
        }
    }


    // 分布式锁
    public static void withDistributeLock() {
        CuratorFramework conn = Test.getConn(true);
        conn.start();
        InterProcessMutex mutex = new InterProcessMutex(conn, "/dlock");
        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        mutex.acquire();
                    } catch (Exception e) {

                    }
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss|SSS");
                    String s = format.format(new Date());
                    System.out.println("订单：" + s);

                    try {
                        mutex.release();
                    } catch (Exception e) {

                    }
                }
            }).start();
            latch.countDown();
        }

    }
}
