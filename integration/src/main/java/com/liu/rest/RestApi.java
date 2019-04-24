package com.liu.rest;

import com.liu.hbase.HbaseProducer;
import com.liu.hbase.HbaseUserConsumer;
import com.liu.hbase.HbaseService;
import com.liu.hive.HiveService;
import com.liu.kafka.Producer;
import com.liu.kafka.model.Click;
import com.liu.kafka.model.User;
import com.liu.kafka.stream.UserClickStreamBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/4/23 0023 9:15
 **/
@RestController
public class RestApi {


    /**
     * 点击事件，将点击事件传入kafka topic，为了后续将用户流和点击流进行连接，得到一个流即包含该用户信息也包含用户点击信息
     * @param click
     * @return
     * @throws Exception
     */
    @RequestMapping("click")
    public String click(Click click)throws Exception{
        Producer<Click> producer=new Producer<>("node1:9092");
        producer.send("click_demo",click.getUserId(),click);
        return "success";

    }

    /**
     * 用户注册，将用户提交的信息放入kafka队列，后续由消费者进行消费
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("register")
    public String register(User user)throws Exception{
        HbaseProducer<User> producer=new HbaseProducer<>();
        producer.start("user_demo",user.getId(),user);
        return "success";
    }

    /**
     * 开启注册用户的消费者接口，开启新线程订阅注册用户生产者，循环遍历
     * @return
     */
    @RequestMapping("startHbaseConsumer")
    public String startHbaseConsumer(){

        HbaseUserConsumer userConsumer=new HbaseUserConsumer();
        userConsumer.start();
        return "success";
    }


    /**
     * hive 离线分析，根据用户名离线查找用户对象
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("getUser")
    public List<User> getUser(String name)throws Exception{

        HiveService hiveService=new HiveService();
        ResultSet resultSet =hiveService.selectData("select key,value from  user_demo where value='"+name+"'");
        List<User> list=new ArrayList<>();
        while (resultSet.next()){
                String userId=resultSet.getString("key");
                String userName=resultSet.getString("value");
                User user=new User();
                user.setId(userId);
                user.setUserName(userName);
                list.add(user);
        }
        return list;
    }


    /**
     * 开启流分析，将用户点击事件和用户事件进行连接，得到即有用户信息又有点击事件的流
     * @return
     */
    @RequestMapping("startStream")
    public String startStream(){

        UserClickStreamBuilder builder=new UserClickStreamBuilder();
        builder.build();

        return "success";
    }



    @RequestMapping("hello")
    public String hello(){

        return "hello";
    }








}
