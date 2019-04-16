import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import serializable.liu.UserStateJsonDeserializer;
import stream.liu.domain.User;
import stream.liu.domain.UserState;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/11 0011 14:12
 **/
public class Consumer {

        private Properties properties;


        public void init(String brokers,String groupId){

            properties = new Properties();
            // 设置连接的kafka集群服务器
            properties.setProperty("bootstrap.servers", brokers);
            // 设置消费者的组id
            properties.setProperty("group.id", groupId);
            // 设置序列化格式
            properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
            properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
            // When a group is first created, it has no offset stored to start reading from. This tells it to start
            // with the earliest record in the stream.
            //设置读取队列的偏移位
            //properties.setProperty("auto.offset.reset","earliest");
            //properties.setProperty("max.poll.records","10");
            //properties.setProperty("enable.auto.commit", "true");


        }


        public void receive(String topicName){
            KafkaConsumer<String,UserState> consumer=new KafkaConsumer<>(properties);
              consumer.subscribe(Arrays.asList(topicName));
            //consumer.assign(Arrays.asList(new TopicPartition(topicName,2)));
            while (true) {
                ConsumerRecords<String, UserState> records = consumer.poll(Duration.ofSeconds(1));
                if (records.count()==0){
                    continue;
                }
                for (ConsumerRecord<String, UserState> record : records) {
                    //System.out.println("分区为 " + record.partition());
                    System.out.print("键的值位 " + record.key());
                    System.out.println("   值为 " + record.value());


                }
            }



        }


    public void receive2(String topicName){
        KafkaConsumer<String,User> consumer=new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topicName));
        //consumer.assign(Arrays.asList(new TopicPartition(topicName,2)));
        while (true) {
            ConsumerRecords<String, User> records = consumer.poll(Duration.ofSeconds(1));
            if (records.count()==0){
                continue;
            }
            for (ConsumerRecord<String, User> record : records) {
                //System.out.println("分区为 " + record.partition());
                System.out.println("键的值位 " + record.key());
                System.out.println(record);
                //System.out.println(" "+record.value().getView());
                //System.out.println("   值为 " + record.value().getViewCount());


            }
        }



    }



}
