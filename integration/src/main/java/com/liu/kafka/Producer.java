package com.liu.kafka;

import com.liu.kafka.model.User;
import com.liu.kafka.serd.JsonDeserializer;
import com.liu.kafka.serd.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/22 0022 17:24
 **/
public class Producer<T> {


    private KafkaProducer<String,T> producer=null;

    public Producer(String brokers){
        Properties prop = new Properties();
        // 设置连接的kafka集群服务器
        prop.setProperty("bootstrap.servers", brokers);
        //设置键的序列化类为String
        prop.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer",JsonSerializer.class.getName());
        producer=new KafkaProducer<>(prop);
    }


    public void send(String topicName,String key,T value)throws Exception{
        // ProducerRecord(String topic, Integer partition, K key, V value)
        // 上述方法可以选择将数据发送的分区
        producer.send(new ProducerRecord<>(topicName,key,value)).get();

    }





}
