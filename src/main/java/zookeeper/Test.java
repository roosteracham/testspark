package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Test implements Watcher {

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        testConn();
    }

    public static ZooKeeper getConn() throws Exception{
        ZooKeeper conn = ZKUtil.getConn("had1:2181/zk-book", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("watcher event: " + event);
                    latch.countDown();
                }
            }
        });
        latch.await();
        return conn;
    }

    //  建立连接
    public static void testConn() throws IOException, InterruptedException {
        ZooKeeper conn = ZKUtil.getConn("8.131.65.50:2181/zk-book", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcher event: " + event);
                latch.countDown();
            }
        });
        System.out.println(conn.getState());
        try {
            latch.await();
        } catch (Exception e) {

        }
        System.out.println(conn.getState());
        conn.close();
    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("watcher event: " + event);
        latch.countDown();
    }

    // 通过sessionid和sessionpasswd创建conn，复用会话
    static void getConnWithSession() throws Exception{
        ZooKeeper conn = ZKUtil.getConn("had2:2181/zk-book", 5000, new Test());
        long sessionId = conn.getSessionId();
        byte[] sessionPasswd = conn.getSessionPasswd();
        conn = ZKUtil.getConn("had2:2181/zk-book", 5000, new Test(), 1, "sessionPasswd".getBytes());
        conn = ZKUtil.getConn("had2:2181/zk-book", 5000, new Test(), sessionId, sessionPasswd);
        Thread.sleep(Integer.MAX_VALUE);
    }

}
