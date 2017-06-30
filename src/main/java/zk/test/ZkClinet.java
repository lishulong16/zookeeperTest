package zk.test;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;


/**
 * @Title: ZkClinet
 * @Description:
 * @Author lishulong
 * @Date 2017/6/30 13:19
 */
public class ZkClinet {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String CONNE = "192.168.6.8:2181,192.168.6.9:2181,192.168.6.10:2181,192.168.6.11:2181";

        //觀察這模式
        ZooKeeper zooKeeper = new ZooKeeper(CONNE, 5000, null);
        //java 并发

        while (true) {
            if (zooKeeper.getState() == ZooKeeper.States.CONNECTED)
                break;
        }

        System.out.println(zooKeeper.getState());
        System.out.println(zooKeeper.exists("/app", null));

//        zooKeeper.create("/onepeice", "onepeice is luffie".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//
//        byte[] bytes = zooKeeper.getData("/onepeice", false, null);
//
//        System.out.println(new String(bytes));

        if (zooKeeper.exists("/app/haizeiwang", new MasterWatcher()) != null)
            System.out.println("this node had created!");
        else {
            zooKeeper.create("/app/haizeiwang", "good boys".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("this is active");
        }

        Thread.sleep(Integer.MAX_VALUE);

        /***
         * first to run
         * the result is this is active
         *
         * second the same as first
         执行前
         [zk: slave1:2181(CONNECTED) 3] ls /app
         [config]
         执行后
         [zk: slave1:2181(CONNECTED) 4] ls /app
         [config, haizeiwang]
         [zk: slave1:2181(CONNECTED) 5]
         执行完
         [zk: slave1:2181(CONNECTED) 7] ls /app
         [config]


         *
         */



//        CONNECTED
//        12884901890,12884901894,1498827584099,1498827698821,1,8,0,0,7,2,12884901922
//
//        this node had created!
//                =================change to active
        zooKeeper.close();
    }
}
