package com.liu.hbase;

import com.liu.kafka.Consumer;
import com.liu.kafka.model.User;

/**
 * @author Liush
 * @description Hbase消费者，消费注册用户队列
 * @date 2019/4/23 0023 14:59
 **/
public class HbaseUserConsumer {


    public void start() {
        Thread thread=new Thread(()-> {
            Consumer<User> consumer = new Consumer<>("node1:9092", "hbase_consumer", User.class);
            consumer.startReceive("user_demo",
                    user -> {
                        try {
                            HbaseService hbaseService = new HbaseService();
                            hbaseService.put("user_demo", user.getId(), "info", "userName", user.getUserName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
            );
        }
        );
        thread.start();

    }


}
