package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.mortbay.util.ajax.JSON;

import java.util.List;

public class ZKNodeOperate extends Test{
    public static void main(String[] args) throws Exception {
        getAllChildren();
    }
    static String connString = "had1:2181/zk-book";
    static int timeout = 5000;
    static Watcher watcher = new ZKNodeOperate();

    // 创建节点
    public static void testCreate() throws Exception {
        ZooKeeper zooKeeper = getConn();
        String s = zooKeeper.create("/test-zk", "abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("created pat: " + s);
        s = zooKeeper.create("/test-zk-", "abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("created pat: " + s);
        Thread.sleep(10000);
    }

    // 获取节点下的所有子节点
    public static void getAllChildren() throws Exception{
        ZooKeeper zooKeeper = getConn();
        List<String> children = zooKeeper.getChildren("/", true);
        System.out.println(JSON.toString(children));
        children = zooKeeper.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getState());
                try {
                    System.out.println(zooKeeper.getChildren("/", true));
                } catch (Exception e) {

                }
            }
        });
        Thread.sleep(10000);
    }
}
