package lock;

/**
 *
 *  只有在zookeeper单机模式下才可以实现分布式事务，因为集群下的znode同步时只能保证超过半数节点是最新状态，如果连到不是
 *  最新状态的节点则事务会失效
 * @author Liush
 * @description
 * @date 2019/4/10 0010 10:15
 **/
public class LockMain {

    public static void main(String[] args)throws Exception {

        DistributedLock distributedLock=new DistributedLock();
        distributedLock.connect("node1");
        boolean result=distributedLock.requestLock("lock1");
        System.out.println();

    	}
}
