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

    //删除节点
    public static void deleteZNode() throws Exception {
        ZooKeeper zooKeeper = getConn();
        zooKeeper.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper.create("/auth-test", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        ZooKeeper conn = getConn();
        Thread.sleep(10000);
        conn.delete("/auth-test", -1);
        Thread.sleep(10000);

    }


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
