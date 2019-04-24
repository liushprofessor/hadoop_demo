package com.liu.hbase;

import com.liu.kafka.Producer;
import com.liu.kafka.model.User;

/**
 * @author Liush
 * @description
 * @date 2019/4/23 0023 15:09
 **/
public class HbaseProducer<T> {

    public void start(String topic,String key,T t )throws Exception{

        Producer<T> producer=new Producer<>("node1:9092");
        producer.send(topic,key,t);


    }

}
