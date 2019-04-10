import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author Liush
 * @description
 * @date 2019/4/9 0009 16:33
 **/
public class ConnectionWatcher implements Watcher {

    protected ZooKeeper zooKeeper;

    //闭锁控制只有链接成功收到回参数后才能继续执行
    private CountDownLatch countDownLatch=new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState()== Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }

    public void close()throws InterruptedException{
        zooKeeper.close();
    }

    public void connect(String host)throws Exception{
        zooKeeper=new ZooKeeper(host,5000,this);
        countDownLatch.await();
    }
}
