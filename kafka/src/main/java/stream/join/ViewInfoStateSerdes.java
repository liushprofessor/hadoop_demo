package stream.join;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import serializable.JsonSerializer;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 10:04
 **/
public class ViewInfoStateSerdes extends Serdes.WrapperSerde<ViewInfoState> {
    public ViewInfoStateSerdes() {
        super(new JoinSerializer<ViewInfoState>(), new JoinDeserializer<ViewInfoState>(ViewInfoState.class));
    }
}
