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

        zooKeeper.create("/onepeice", "onepeice is luffie".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        byte[] bytes = zooKeeper.getData("/onepeice", false, null);

        System.out.println(new String(bytes));

        zooKeeper.close();
    }
}
