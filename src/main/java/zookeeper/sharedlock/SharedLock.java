package zookeeper.sharedlock;

import basic.Threads;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import zookeeper.curator.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode.POST_INITIALIZED_EVENT;

public class SharedLock {
    public static final String PARENT_NODE = "/zookeeper/shared_lock";
    public static final String READ = "/r";
    public static final String WRITE = "/w";
    public static final String DASH = "-";

    public static final CuratorFramework client = Test.getConn(true);

    static {
        client.start();
    }
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Threads.startThread(() -> {
                CuratorFramework conn = Test.getConn(true);
                conn.start();
                DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(conn, "/zookeeper" +
                        "/barrier", 10);
                try {
                    TimeUnit.SECONDS.sleep(Math.round(Math.random() * 3));
                    System.out.println(Thread.currentThread().getName() + " is ready.");
                    barrier.enter();
                    System.out.println(Thread.currentThread().getName() + " complete1.");
                    Thread.sleep(Math.round(Math.random() * 3000));
                    // 再次等待
                    barrier.leave();
                    System.out.println(Thread.currentThread().getName() + " complete.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void mutexDemo() {
        InterProcessMutex mutex = new InterProcessMutex(client, PARENT_NODE);
        for (int i = 0; i < 10; i++) {
            Threads.startThread(() -> mutex(mutex));
        }
    }

    private static void mutex(InterProcessMutex mutex) {
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " acquired lock.");
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void counter() {
        String path = "/zookeeper/counter";
        DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(client, path,
                new RetryOneTime(1000));
        for (int i = 0; i < 20; i++) {
            Threads.startThread(() -> {
                try {
                    AtomicValue<Integer> add = distributedAtomicInteger.add(8);
                    System.out.println("res: " + add.succeeded() + "  " + add.preValue() + " - " + add.postValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 读写锁
     * @throws InterruptedException
     */
    private static void readWriteLock() throws InterruptedException {
        client.start();
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, PARENT_NODE);
        Threads.startThread(() -> {
            try {
                read(readWriteLock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(1);
        Threads.startThread(() -> {
            try {
                read(readWriteLock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Threads.startThread(() -> {
            try {
                write(readWriteLock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void read(InterProcessReadWriteLock readWriteLock) throws Exception {
        try {
            readWriteLock.readLock().acquire();
            System.out.println(Thread.currentThread().getName() + " read lock.");
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().release();
        }
    }

    private static void write(InterProcessReadWriteLock readWriteLock) throws Exception {
        try {
            readWriteLock.writeLock().acquire();
            System.out.println(Thread.currentThread().getName() + " write lock.");
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().release();
        }
    }

    private static void watchChildNode() throws Exception {
        CuratorFramework conn = Test.getConn(true);
        conn.start();
        PathChildrenCache cache = new PathChildrenCache(conn, PARENT_NODE, true);
        cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        cache.getListenable().addListener((client, event) -> {
            System.out.println(event.getType());
            switch (event.getType()) {
                case INITIALIZED:
                    System.out.println("INITIALIZED: " + event.getData());
                    break;
                case CHILD_ADDED:
                    System.out.println("create: " + event.getData());
                    break;
                case CHILD_UPDATED:
                    System.out.println("update: " + event.getData());
                    break;
                case CHILD_REMOVED:
                    System.out.println("remove: " + event.getData());
                    break;
                case CONNECTION_LOST:
                    System.out.println("CONNECTION_LOST: " + event.getData());
                    break;
            }
        });
        String path = PARENT_NODE + READ + DASH + Thread.currentThread().getName() + DASH;
//        watchNode(conn, PARENT_NODE);
        TimeUnit.SECONDS.sleep(10);
        String forPath = conn.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath(path);
        System.out.println("create path " + forPath);
    }

    private static void watchNode(CuratorFramework conn, String path) throws Exception {
        NodeCache nodeCache = new NodeCache(conn, path);
        nodeCache.start();
        nodeCache.getListenable().addListener(() -> {
            System.out.println((nodeCache.getCurrentData().getPath() + " : " + new String(nodeCache.getCurrentData().getData())));
        });
    }
}
