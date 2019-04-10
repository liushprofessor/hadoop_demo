import lock.DistributedLock;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Liush
 * @description
 * @date 2019/4/10 0010 10:07
 **/
public class LockTest {



    public  DistributedLock distributedLock;

    @Before
    public void connect()throws Exception{
        this.distributedLock=new DistributedLock();
        distributedLock.connect("node1");

    }

    @Test
    public void createLock()throws Exception{
        this.distributedLock.createLock("lock1");

    }

    @Test
    public void getLock()throws Exception{
        this.distributedLock=new DistributedLock();
        boolean result=distributedLock.requestLock("lock1");
    }


}
