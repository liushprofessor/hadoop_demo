import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/4/9 0009 16:59
 **/
public class DeleteGroup extends ConnectionWatcher {

    public void delete(String groupName)throws Exception{
        String path="/"+groupName;
        List<String> children=zooKeeper.getChildren(path,false);
        for (String child : children){
            zooKeeper.delete(path+"/"+child,-1);
        }
        zooKeeper.delete(path,-1);

    }
    public static void main(String[] args)throws Exception {
        DeleteGroup deleteGroup=new DeleteGroup();
        deleteGroup.connect("node1");
        deleteGroup.delete("liu");
        deleteGroup.close();
	}





}
