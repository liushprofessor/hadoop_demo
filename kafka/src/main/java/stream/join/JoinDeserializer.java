package stream.join;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 8:48
 **/
public class JoinDeserializer<T> implements Deserializer<T>{

    Gson gson=new Gson();

    Class<T> tClass;


    public JoinDeserializer() {
    }

    public JoinDeserializer(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes),tClass);
    }

    @Override
    public void close() {

    }
}
