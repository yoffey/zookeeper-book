package learn.curator;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * description:
 *
 * @author 宗永飞 (yongfei.zong@ucarinc.com)
 * @version 1.0
 * @date 2019-05-28 16:38
 */
public class TestingClusterSample {
    public static void main(String[] args) throws Exception {
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        Thread.sleep(3000);
        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer zk : cluster.getServers()) {
            System.out.println(zk.getInstanceSpec().getServerId() + "-");
            System.out.println(zk.getQuorumPeer().getServerState() + "-");
            System.out.println(zk.getInstanceSpec().getDataDirectory().getAbsolutePath());
            if (zk.getQuorumPeer().getServerState().equals("leading")) {
                leader = zk;
            }
        }
        leader.kill();
        System.out.println("After leader kill...");
        for (TestingZooKeeperServer zk : cluster.getServers()) {
            System.out.println(zk.getInstanceSpec().getServerId() + "-");
            System.out.println(zk.getQuorumPeer().getServerState() + "-");
            System.out.println(zk.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }
        cluster.stop();
    }
}
