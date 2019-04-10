import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/4/9 0009 16:51
 **/
public class ListGroup extends ConnectionWatcher {

    public void list(String groupName)throws Exception{
        String path="/"+groupName;
        List<String> children=zooKeeper.getChildren(path,false);
        children.forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) throws Exception{
    		ListGroup listGroup=new ListGroup();
    		listGroup.connect("node1");
    		listGroup.list("lock1");
    	}


}
