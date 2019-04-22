package com.liu.kafka;

import com.liu.kafka.model.UserClick;
import com.liu.kafka.serd.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/22 0022 17:25
 **/
public class Consumer<T> {

    Properties properties;
    public Consumer(String brokers,String groupId){


        properties = new Properties();
        // 设置连接的kafka集群服务器
        properties.setProperty("bootstrap.servers", brokers);
        // 设置消费者的组id
        properties.setProperty("group.id", groupId);
        // 设置序列化格式
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        JsonDeserializer<UserClick> joinDeserializer=new JsonDeserializer<>(UserClick.class);
        properties.setProperty("value.deserializer", joinDeserializer.getClass().getName());

    }


    public void startReceive(String topicName){
        KafkaConsumer<String,T> consumer=new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topicName));
        while (true) {
            ConsumerRecords<String, T> records = consumer.poll(Duration.ofSeconds(5));
            if (records.count()==0){
                continue;
            }
            for (ConsumerRecord<String, T> record : records) {
                System.out.println("键的值位 " + record.key());
                System.out.println(record.value());
            }
        }



    }

}
