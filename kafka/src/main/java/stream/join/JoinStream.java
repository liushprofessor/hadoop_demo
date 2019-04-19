package stream.join;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import serializable.liu.UserStateSerdes;

import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 9:19
 **/
public class JoinStream {

    private static Properties props;

    private static StreamsBuilder builder ;



    public static void main(String[] args) {


        JoinStream joinStream = new JoinStream();
        joinStream.init();

        KTable<String, UserInfo> kTable = joinStream.getUser();

        KStream<String, ViewInfo> kStream = joinStream.getView2();

        KStream<String, UserView> userViewStream = kStream.leftJoin(kTable, (viewInfo, userInfo) -> {
            System.out.println("join...........");
            System.out.println(viewInfo);
            System.out.println(userInfo);
            UserView userView=new UserView();
            if (userInfo!=null)
            userView.setUserName(userInfo.getUserName());
            return userView;
        }
        , Joined.with(Serdes.String(), new ViewInfoSerdes(), new UserInfoSerdes()));

        userViewStream.foreach((k,v)->{
            System.out.println(k);
            System.out.println(v);

        });

        userViewStream.to("userViewOut",Produced.with(Serdes.String(),new UserViewSerde()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        //streams.cleanUp();
        streams.start();
        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }



    public void init(){
        props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "userInfo1");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092");
        //props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        //为什么在kafka在流中要用Serdes包装序列化和反序列化，因为窗口和聚合需要将聚合后的数据重新写入到kafka，
        // 所以需要序列化
        //和反序列化对象，而消费者只需要反序列化，生产者只需要序列化
        //由于做join stream和table处理时序列化的类不一样，所以配置序列化在具体的stream和table中实现
        /*props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, UserInfoSerdes.class.getName());*/

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        builder=new StreamsBuilder();

    }



    public KTable<String, UserInfo> getUser() {
        //配置扩扑
        return builder.table("userInfo",Consumed.with(Serdes.String(),new UserInfoSerdes()));

    }


    public KStream<String, ViewInfo> getView2(){
        KStream kStream= builder.stream("userView",Consumed.with(Serdes.String(),new ViewInfoSerdes()));
        kStream.foreach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
        return kStream;
    }



    public KStream<String, ViewInfoState> getView() {
        //配置扩扑
        KStream<String, ViewInfo> kStream = builder.stream("viewInfo",Consumed.with(Serdes.String(),new ViewInfoSerdes()));
        ViewInfoState userViewState = new ViewInfoState();
        KStream<String, ViewInfoState> kStream1= kStream.
                selectKey((s, view) -> {
                    System.out.println("view 的key "+s);
                    if ("1".equals(s)) {
                        return s;
                    }
                    return null;

                }).
                groupByKey().windowedBy(TimeWindows.of(60000).advanceBy(1000)).
                aggregate(() -> userViewState,
                        (key, value, state) -> state.add(value),
                        Materialized.<String, ViewInfoState, WindowStore<Bytes, byte[]>>as("ww").withValueSerde(new ViewInfoStateSerdes())

                ).toStream().map((stringWindowed, state) -> new KeyValue<>(stringWindowed.key(), state));


        kStream1.foreach((s,v)->{
            System.out.println(s);
            System.out.println(v);

        });

        return kStream1;

    }


}
