package serializable.liu;


import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import serializable.JsonDeserializer;
import serializable.JsonSerializer;
import stream.liu.domain.User;
import stream.liu.domain.UserState;


import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 11:15
 **/
public class UserStateSerdes  extends Serdes.WrapperSerde<UserState> {


    public UserStateSerdes() {
        super(new JsonSerializer<UserState>(), new JsonDeserializer<UserState>(UserState.class));
    }
}
