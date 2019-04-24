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




    @RequestMapping("click")
    public String click(Click click)throws Exception{
        Producer<Click> producer=new Producer<>("node1:9092");
        producer.send("click_demo",click.getUserId(),click);
        return "success";

    }


    @RequestMapping("register")
    public String register(User user)throws Exception{
        HbaseProducer<User> producer=new HbaseProducer<>();
        producer.start("user_demo",user.getId(),user);
        return "success";
    }

    @RequestMapping("startHbaseConsumer")
    public String startHbaseConsumer(){

        HbaseUserConsumer userConsumer=new HbaseUserConsumer();
        userConsumer.start();
        return "success";
    }



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
