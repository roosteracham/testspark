package zookeeper.zkclient;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import zookeeper.ZKNodeOperate;

import java.util.List;

public class Test {

    public static ZkClient getConn() {
        ZkConnection zkConnection = new ZkConnection("had1:2181/zk-book");
        return new ZkClient(zkConnection);
    }

    public static void main(String[] args) throws Exception {
        getData();
    }

    public static void createZNode() throws InterruptedException {
        ZkClient conn = getConn();
        conn.create("/test-zc-create", "123", CreateMode.EPHEMERAL);
        Thread.sleep(10000);
    }

    public static void getChildren() throws InterruptedException {
        ZkClient conn = getConn();
        conn.subscribeChildChanges("/", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s + "changed, " + JSON.toJSONString(list));
            }
        });
        Thread.sleep(200000);
    }

    public static void getData() throws InterruptedException {
        ZkClient conn = getConn();
        conn.create("/c2", "123", CreateMode.EPHEMERAL);
        conn.subscribeDataChanges("/c2", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("path: " + s + ", data: " + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("path: " + s + " deteled;");
            }
        });
        Thread.sleep(1000);
        conn.writeData("/c2", "2");
        Thread.sleep(200000);
    }
}
