package serializable.liu;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import stream.liu.domain.User;

import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/16 0016 16:13
 **/
public class UserDes implements Deserializer<User> {

    Gson gson=new Gson();


    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public User deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes),User.class);
    }

    @Override
    public void close() {

    }
}
