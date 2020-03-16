package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

public class MasterSelect {

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework conn = Test.getConn(true);
        final String path = "/master_select";
        conn.start();
        LeaderSelector selector = new LeaderSelector(conn, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println(curatorFramework.getNamespace() + " be the master");
                Thread.sleep(3000);
                System.out.println("完成master操作，释放master权力");
            }
        });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
