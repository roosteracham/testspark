package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import zookeeper.ZKNodeOperate;

import java.util.Arrays;

public class Test {

    static String connString = "8.131.65.50:2181";
    static int timeout = 5000;
    static Watcher watcher = new ZKNodeOperate();

    public static CuratorFramework getConn(boolean fluent) {
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(1000, 3);
        if (fluent) {
            return CuratorFrameworkFactory.builder()
                    .connectString(connString)
                    .sessionTimeoutMs(timeout)
                    .retryPolicy(backoffRetry)
                    .build();
        } else {
            return CuratorFrameworkFactory.newClient(connString, timeout, timeout,
                    backoffRetry);
        }
    }

    public static void main(String[] args) throws Exception {
        testPathChildrenCache();
    }

    static void testCreate() throws Exception {
        CuratorFramework conn = getConn(true);
        conn.start();
        conn.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/curator/c1",
                "123".getBytes());
    }

    // 无法对子节点的子节点进行监听
    public static void testCacheNode() throws Exception {
        String path = "/cachenode";
        CuratorFramework conn = getConn(true);
        conn.start();
        NodeCache nodeCache = new NodeCache(conn, path, false);
        conn.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(Thread.currentThread().getName() + " , changed, " + nodeCache.getCurrentData().getPath() + ", version: " +
                        nodeCache.getCurrentData().getStat().getVersion() + "data: " + Arrays.toString(nodeCache.getCurrentData().getData()));
            }
        });
        Thread.sleep(1000);
        conn.setData().forPath(path, "u".getBytes());
        conn.delete().deletingChildrenIfNeeded().forPath(path);
    }

    public static void testPathChildrenCache() throws InterruptedException {
        String path = "/";
        CuratorFramework conn = getConn(true);
        conn.start();
        PathChildrenCache cache = new PathChildrenCache(conn, path, true);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        System.out.println(pathChildrenCacheEvent.getData().getPath() + " CHILD_ADDED");
                        break;
                    case CHILD_UPDATED:
                        System.out.println(pathChildrenCacheEvent.getData().getPath() + " CHILD_UPDATED");
                        break;
                    case CHILD_REMOVED:
                        System.out.println(pathChildrenCacheEvent.getData().getPath() + " CHILD_REMOVED");
                        break;
                    default:
                        break;
                }
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
