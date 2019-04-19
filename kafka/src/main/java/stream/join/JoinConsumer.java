package stream.join;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import serializable.liu.UserStateJsonDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 8:59
 **/
public class JoinConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // 设置连接的kafka集群服务器
        properties.setProperty("bootstrap.servers", "node1:9092");
        // 设置消费者的组id
        properties.setProperty("group.id", "group1");
        // 设置序列化格式
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        JoinDeserializer<UserView> joinDeserializer =new JoinDeserializer<>();
        properties.setProperty("value.deserializer", joinDeserializer.getClass().getName());
        properties.setProperty("auto.offset.reset","earliest");

        KafkaConsumer<String,ViewInfo> kafkaConsumer=new KafkaConsumer<>(properties);
        List<String> topics=new ArrayList<>();
        topics.add("join_out");
        kafkaConsumer.subscribe(topics);
        while (true) {
            ConsumerRecords<String, ViewInfo> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord consumerRecord : consumerRecords) {
                System.out.println("消费获取到的键是" + consumerRecord.key());
                System.out.println("消费者获取到的值是" + consumerRecord.value());


            }
        }




    	}


}
