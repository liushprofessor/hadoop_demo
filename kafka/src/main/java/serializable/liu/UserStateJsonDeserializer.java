package serializable.liu;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import stream.liu.domain.User;
import stream.liu.domain.UserState;

import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/16 0016 10:03
 **/
public class UserStateJsonDeserializer implements Deserializer<UserState> {


    private Gson gson=new Gson();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public UserState deserialize(String s, byte[] bytes) {
        System.out.println("解析"+s);
        System.out.println(bytes.length);
        return gson.fromJson(new String(bytes),UserState.class);
    }

    @Override
    public void close() {

    }
}
