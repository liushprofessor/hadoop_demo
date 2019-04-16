package serializable.liu;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import serializable.JsonDeserializer;
import serializable.JsonSerializer;
import serializable.Trade;
import stream.liu.domain.User;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 11:04
 **/
public class UserSerdes extends Serdes.WrapperSerde<User> {
    public UserSerdes() {
        super(new UserSer(), new UserDes());
    }
}
