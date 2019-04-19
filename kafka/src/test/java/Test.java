import stream.join.UserInfo;
import stream.join.UserView;
import stream.join.ViewInfo;
import stream.liu.ProducerUser;
import stream.liu.domain.User;

/**
 * @author Liush
 * @description
 * @date 2019/4/11 0011 13:58
 **/
public class Test {

    @org.junit.Test
    public void producer()throws Exception{
        Producer producer=new Producer();
        producer.init("node1:9092,node1:9093,node1:9094");
        producer.send("user_in","liu","qqq");
    }

    @org.junit.Test
    public void producerUser()throws Exception{
        ProducerUser producerUser=new ProducerUser();
        producerUser.init("node1:9092,node1:9093,node1:9094");
        User user=new User();
        user.setUserName("kkkkkk");
        user.setView("sss");
        producerUser.send("user_in4","kkkkk",user);
    }


    @org.junit.Test
    public void producerUserInfo()throws Exception{

        ProducerUser producerUser=new ProducerUser();
        producerUser.infoInit("node1:9092");
        UserInfo userInfo=new UserInfo();
        userInfo.setUserID("1");
        userInfo.setUserName("周润发");
        producerUser.send("userInfo","1",userInfo);

    }

    @org.junit.Test
    public void producerViewInfo()throws Exception{
        ProducerUser producerUser=new ProducerUser();
        producerUser.infoInit("node1:9092");
        ViewInfo viewInfo=new ViewInfo();
        viewInfo.setPageName("百度");
        viewInfo.setUrl("www.baidu.com");
        producerUser.send("userView","1",viewInfo);

    }






    @org.junit.Test
    public void consumer(){
        Consumer consumer=new Consumer();
        consumer.init("node1:9092,node1:9093,node1:9094","liu");
        consumer.receive2("user_out");
    }

}
