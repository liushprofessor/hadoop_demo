package com.liu.kafka.stream;

import com.liu.kafka.model.Click;
import com.liu.kafka.model.User;
import com.liu.kafka.model.UserClick;
import com.liu.kafka.serd.JsonDeserializer;
import com.liu.kafka.serd.JsonSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.stringtemplate.v4.ST;

import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/22 0022 16:06
 **/
public class UserClickStreamBuilder {


    private StreamsBuilder builder;

    private Properties props;

    public UserClickStreamBuilder() {

        builder = new StreamsBuilder();
        props = new Properties();
        props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "userClick");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    }

    public void build() {
        KTable<String, User> userTable=buildUserTable();
        KStream<String, UserClick> clickKStream=buildClick();
        KStream<String, UserClick> userClickStream=buildUserClick(clickKStream,userTable);
        sendToTopic("user_click_demo",userClickStream);
        startStream();
    }

    //根据扩扑图开启流程
    private void startStream() {
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }


    private void sendToTopic(String topicName,KStream<String, UserClick> userClickStream){
        userClickStream.to(topicName,Produced.with(Serdes.String(),new UserClickSerd()));

    }




    public KStream<String, UserClick> buildUserClick(KStream<String, UserClick> stream, KTable<String, User> table) {
        KStream<String, UserClick> userClickKStream= stream.leftJoin(table,
                (userClick, user) -> {
                    userClick.setUserName(user.getUserName());
                    return userClick;
                },
                Joined.with(Serdes.String(), new UserClickSerd(), new UserSerd())

        );
        userClickKStream.foreach((k,v)->{
            System.out.println("流key。。。"+k);
            System.out.println("流value。。。"+v.getUserName());
            for (Click click:v.getClicks()){
                    System.out.println(click.getClickName());

            }

        });
        return  userClickKStream;
    }


    private KStream<String, UserClick> buildClick() {
        KStream<String, Click> stream = builder.stream("click_demo", Consumed.with(Serdes.String(), new ClickSerd()));
        UserClick userClick = new UserClick();
        return stream.groupByKey().windowedBy(TimeWindows.of(10000).advanceBy(1000)).
                aggregate(() -> userClick, (key, value, click) ->
                                click.add(value),
                        Materialized.with(Serdes.String(), new UserClickSerd())
                ).toStream().map(
                (key, value) -> new KeyValue<>(key.key(), value)
        );
    }


    private KTable<String, User> buildUserTable() {

        return builder.table("user_demo", Consumed.with(Serdes.String(), new UserSerd()));
    }


    public class ClickSerd extends Serdes.WrapperSerde<Click> {
        public ClickSerd() {
            super(new JsonSerializer<Click>(), new JsonDeserializer<>(Click.class));
        }
    }


    public class UserClickSerd extends Serdes.WrapperSerde<UserClick> {

        public UserClickSerd() {
            super(new JsonSerializer<UserClick>(), new JsonDeserializer<>(UserClick.class));
        }
    }


    public class UserSerd extends Serdes.WrapperSerde<User> {


        public UserSerd() {
            super(new JsonSerializer<User>(), new JsonDeserializer<>(User.class));
        }
    }


}
