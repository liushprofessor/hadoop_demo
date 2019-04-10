package lock;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Liush
 * @description
 * @date 2019/4/10 0010 8:59
 **/
public class DistributedLock extends LockConnectionWatcher {


    private String lockName;

    private String lockString;

    private boolean getLockResult;

    private CountDownLatch countDownLatch=new CountDownLatch(1);



    public String createLock(String lockName)throws Exception{
        return zooKeeper.create("/"+lockName,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }

    public boolean requestLock(String lockName)throws Exception{
        this.lockName=lockName;
        String path="/"+lockName;
        String childPath=zooKeeper.create(path+"/",null, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        String[] paths=childPath.split("\\/");
        this.lockString=paths[paths.length-1];
        System.out.println("生成的锁是"+lockString);
        LockWatcher lockWatcher=new  LockWatcher();
        lockWatcher.lockName=lockName;
        lockWatcher.lockString=lockString;
        lockWatcher.countDownLatch=this.countDownLatch;
        List<String> otherLocks=zooKeeper.getChildren(path,lockWatcher);
        this.getLockResult=getLock(otherLocks,lockString);
        if (getLockResult){
            System.out.println("执行成功");
        }else {
            System.out.println("获取锁失败，请等待执行");
            countDownLatch.await();
        }
        return getLockResult;
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
