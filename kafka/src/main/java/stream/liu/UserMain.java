package stream.liu;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import serializable.JsonDeserializer;
import serializable.JsonSerializer;
import serializable.TradeStats;
import serializable.liu.UserSerdes;
import serializable.liu.UserStateSerdes;
import stream.liu.domain.User;
import stream.liu.domain.UserState;

import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 10:40
 **/
public class UserMain {

    public static void main(String[] args)throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "user_aggregate2143");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092");
        //props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        //为什么在kafka在流中要用Serdes包装序列化和反序列化，因为窗口和聚合需要将聚合后的数据重新写入到kafka，
        // 所以需要序列化
        //和反序列化对象，而消费者只需要反序列化，生产者只需要序列化
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, UserSerdes.class.getName());

        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        // Note: To re-run the demo, you need to use the offset reset tool:
        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        //UserState userState3=new UserState();
        StreamsBuilder builder=new StreamsBuilder();
        KStream<String,User> source=builder.stream("user_in4");
        /*KStream<String ,Long> kStream=source.
                groupByKey()
                .count().toStream().map(new KeyValueMapper<String, Long, KeyValue<? extends String, ? extends Long>>() {
            @Override
            public KeyValue<? extends String, ? extends Long> apply(String s, Long aLong) {
                System.out.println(s);
                System.out.println(aLong);
                return new KeyValue<>(s,aLong);
            }
        });*/
       /* KStream<String ,Long> kStream=source.
                groupBy(new KeyValueMapper<String, User, String>() {

                    @Override
                    public String apply(String key, User user) {
                        System.out.println(key);
                        System.out.println(user);
                        return key;
                    }
                })
                .count().toStream();*/


        UserState userState=new UserState();
        KStream<String ,UserState> kStream=source.
                groupByKey().windowedBy(TimeWindows.of(20000).advanceBy(5000)).
                aggregate(()->userState,
                        (s, user, userState1) -> userState1.addViewCount(user),
                        //指明store保存的中间值的序列化实现
                        Materialized.<String, UserState, WindowStore<Bytes, byte[]>>as("www4").withValueSerde(new UserStateSerdes())
                        .withKeySerde(Serdes.String())
                ).toStream().map((stringWindowed, userState2) ->{
                    System.out.println("-------------------");
                    System.out.println(stringWindowed.key());
                    System.out.println(userState.getViewCount());
                    return new KeyValue<>(stringWindowed.key(),userState2);});

        kStream.to("user_out4",Produced.with(Serdes.String(), new UserStateSerdes()));



        Topology topology = builder.build();

        KafkaStreams streams = new KafkaStreams(topology, props);


       // streams.cleanUp();

        streams.start();

        System.out.println("okkkkkkkkkkkkkkkkkkkkk");
        kStream.foreach((k,v)->System.out.println(k));

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));


    	}






}
