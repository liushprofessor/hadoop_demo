package serializable.liu;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;
import stream.liu.domain.UserState;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/18 0018 10:04
 **/
public class UserStateJsonSerializer  implements Serializer<UserState>{


    private Gson gson=new Gson();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, UserState userState) {
        return gson.toJson(userState).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public void close() {

    }
}
