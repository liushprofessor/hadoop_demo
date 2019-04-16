package stream.liu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import serializable.JsonSerializer;
import serializable.liu.UserSer;
import serializable.liu.UserSerdes;
import stream.liu.domain.User;

import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 15:06
 **/
public class ProducerUser {

    private KafkaProducer<String,User> producer=null;

    public void init(String brokers){
        Properties properties = new Properties();
        // 设置连接的kafka集群服务器
        properties.setProperty("bootstrap.servers", brokers);
        // 设置序列化的键值对
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        UserSer userSer=new UserSer();
        properties.setProperty("value.serializer", userSer.getClass().getName());
        producer=new KafkaProducer<>(properties);
        System.out.println();
    }


    public void send(String topicName,String key,User value)throws Exception{
        // ProducerRecord(String topic, Integer partition, K key, V value)
        // 上述方法可以选择将数据发送的分区
        RecordMetadata recordMetadata =producer.send(new ProducerRecord<String,User>(topicName,key,value)).get();
        System.out.println("分区是 "+recordMetadata.partition());
        System.out.println("topic是 "+recordMetadata.topic());
        System.out.println("key是"+ recordMetadata.serializedKeySize());
    }
}
