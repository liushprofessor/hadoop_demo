import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author Liush
 * @description
 * @date 2019/4/9 0009 16:10
 **/
public class CreateGroup implements Watcher {


    private ZooKeeper zooKeeper;

    //闭锁控制只有链接成功收到回参数后才能继续执行
    private CountDownLatch countDownLatch=new CountDownLatch(1);


    public void connect(String host)throws Exception{
        zooKeeper=new ZooKeeper(host,5000,this);
        countDownLatch.await();
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState()== Event.KeeperState.SyncConnected){
                countDownLatch.countDown();
            }
    }



    public void create(String groupName) throws KeeperException ,InterruptedException{
            String path="/"+groupName;
            String createPath=zooKeeper.create(path,null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            System.out.println(createPath);

    }

    public void close()throws InterruptedException{
        zooKeeper.close();
    }

    public static void main(String[] args) throws Exception{
        CreateGroup createGroup=new CreateGroup();
        createGroup.connect("node1");
        createGroup.create("liu");
        createGroup.close();
    	}

}
