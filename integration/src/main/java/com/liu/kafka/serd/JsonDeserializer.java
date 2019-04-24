package com.liu.kafka.serd;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.Properties;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 9:28
 **/
public class JsonDeserializer<T> implements Deserializer<T> {


    private Gson gson=new Gson();

    private Class<T> tClass;


    public JsonDeserializer(Class<T> tClass) {
        this.tClass = tClass;
    }

    public JsonDeserializer() {

    }

    @Override 
    @SuppressWarnings("unchecked")
    public void configure(Map<String, ?> map, boolean b) {
        if(tClass == null) {
            tClass = (Class<T>) map.get("deserializer.class");
        }

    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        return gson.fromJson(new String(bytes),tClass);
    }


    @Override
    public void close() {

    }
}
