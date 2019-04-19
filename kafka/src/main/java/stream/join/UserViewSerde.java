package stream.join;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import serializable.JsonSerializer;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 13:42
 **/
public class UserViewSerde extends Serdes.WrapperSerde<UserView> {


    public UserViewSerde() {
        super(new JsonSerializer<UserView>(), new JoinDeserializer<UserView>());
    }
}
