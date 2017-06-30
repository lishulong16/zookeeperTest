package zk.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Title: MasterWatcher
 * @Description:
 * @Author lishulong
 * @Date 2017/6/30 14:08
 */
public class MasterWatcher implements Watcher {
    public void process(WatchedEvent watchedEvent) {

        if (watchedEvent.getType() == Event.EventType.NodeDeleted)
            //@TODO
            System.out.println("=================change to active");


    }
}
