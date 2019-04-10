package lock;

/**
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
