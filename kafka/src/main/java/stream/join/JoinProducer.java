package stream.join;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import serializable.liu.UserSerdes;

import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 8:52
 **/
public class JoinProducer {


    public static void main(String[] args) {
        JoinProducer joinProducer=new JoinProducer();

    }


    public void sendView() {
        Properties properties = new Properties();
        // 设置连接的kafka集群服务器
        properties.setProperty("bootstrap.servers", "node1:9092");
        // 设置序列化的键值对
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        JoinSerializer<ViewInfo> userInfoJoinSerializer = new JoinSerializer<>();
        properties.setProperty("value.serializer", userInfoJoinSerializer.getClass().getName());
        KafkaProducer<String, ViewInfo> kafkaProducer = new KafkaProducer<>(properties);
        ViewInfo viewInfo=new ViewInfo();
        viewInfo.setPageName("页面名字");
        viewInfo.setPageName("url");
        kafkaProducer.send(new ProducerRecord<>("userView","1", viewInfo));

    }


    public void sendUser() {
        Properties properties = new Properties();
        // 设置连接的kafka集群服务器
        properties.setProperty("bootstrap.servers", "node1:9092");
        // 设置序列化的键值对
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        JoinSerializer<UserInfo> userInfoJoinSerializer = new JoinSerializer<>();
        properties.setProperty("value.serializer", userInfoJoinSerializer.getClass().getName());
        KafkaProducer<String, UserInfo> kafkaProducer = new KafkaProducer<>(properties);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserID("1");
        userInfo.setUserName("周润发");
        kafkaProducer.send(new ProducerRecord<>("userView","1", userInfo));


    }

}
