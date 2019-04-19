package stream.join;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import serializable.JsonSerializer;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 15:35
 **/
public class ViewInfoSerdes extends Serdes.WrapperSerde<ViewInfo> {
    public ViewInfoSerdes() {
        super(new JoinSerializer<ViewInfo>(),new JoinDeserializer<>(ViewInfo.class));
    }
}
