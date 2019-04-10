package lock;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Liush
 * @description
 * @date 2019/4/10 0010 11:08
 **/
public class LockWatcher implements Watcher  {

    private ZooKeeper zooKeeper;

    public String lockName;

    public String lockString;

    public CountDownLatch countDownLatch;

    private CountDownLatch connectLatch=new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            System.out.println(this.getClass()+"  "+lockName);
            connect("node1");
            LockWatcher lockWatcher=new LockWatcher();
            lockWatcher.lockName=lockName;
            lockWatcher.lockString=lockString;
            lockWatcher.countDownLatch=countDownLatch;
            List<String> otherLocks=zooKeeper.getChildren("/"+lockWatcher.lockName,lockWatcher);
            boolean result=getLock(otherLocks,lockString);
            if(result){
                System.out.println("执行成功");
                lockWatcher.countDownLatch.countDown();
            }else {
                System.out.println("执行再次失败");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect(String host)throws Exception{
        zooKeeper=new ZooKeeper(host,5000,watchedEvent -> {
            if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
                connectLatch.countDown();
            }
        });
        connectLatch.await();
    }

    private boolean getLock(List<String> children,String lockString){
        long lockLong=Long.valueOf(lockString);
        for(String lock: children){
            long otherLock=Long.valueOf(lock);
            if(lockLong>otherLock){
                return false;
            }
        }
        return true;

    }
}
