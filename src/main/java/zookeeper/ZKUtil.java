package zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZKUtil {

    public static ZooKeeper getConn(String connString, int timeout) throws IOException {
        return getConn(connString, timeout, null);
    }

    public static ZooKeeper getConn(String connString, int timeout, Watcher watcher) throws IOException {
        return new ZooKeeper(connString, timeout, watcher);
    }

    public static ZooKeeper getConn(String connString, int timeout, Watcher watcher, long sessionId, byte[] pwd) throws IOException {
        return new ZooKeeper(connString, timeout, watcher, sessionId, pwd);
    }
}
