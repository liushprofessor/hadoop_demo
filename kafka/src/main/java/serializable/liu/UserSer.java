package serializable.liu;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;
import stream.liu.domain.User;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/16 0016 16:13
 **/
public class UserSer implements Serializer<User> {

    Gson gson=new Gson();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, User user) {
        return gson.toJson(user).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public void close() {

    }
}
