package stream.join;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 9:22
 **/
public class UserInfoSerdes extends Serdes.WrapperSerde<UserInfo> {
    public UserInfoSerdes() {
        super(new JoinSerializer<UserInfo>(),new JoinDeserializer<UserInfo>(UserInfo.class));
    }
}
