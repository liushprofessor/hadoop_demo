import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author Liush
 * @description
 * @date 2019/4/11 0011 13:42
 * 生成者
 **/
public class Producer {


        private KafkaProducer<String,String> producer=null;

        public void init(String brokers){
            Properties properties = new Properties();
            // 设置连接的kafka集群服务器
            properties.setProperty("bootstrap.servers", brokers);
            // 设置序列化的键值对
            properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
            properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            producer=new KafkaProducer<>(properties);

        }


        public void send(String topicName,String key,String value)throws Exception{
            // ProducerRecord(String topic, Integer partition, K key, V value)
            // 上述方法可以选择将数据发送的分区
            RecordMetadata recordMetadata =producer.send(new ProducerRecord<>(topicName,key,value)).get();
            System.out.println("分区是 "+recordMetadata.partition());
            System.out.println("topic是 "+recordMetadata.topic());
        }





}
