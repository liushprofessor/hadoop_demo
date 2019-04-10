import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * @author Liush
 * @description
 * @date 2019/4/9 0009 16:46
 **/
public class JoinGroup extends ConnectionWatcher {

    public void join(String groupName,String memberName)throws Exception{
        String path ="/"+groupName+"/"+memberName;
        String createPath=zooKeeper.create(path,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(createPath);
    }

    public static void main(String[] args) throws Exception{
            JoinGroup joinGroup=new JoinGroup();
            joinGroup.connect("node1");
            joinGroup.join("liu0000000001","");
            Thread.sleep(Long.MAX_VALUE);
    	}


}
