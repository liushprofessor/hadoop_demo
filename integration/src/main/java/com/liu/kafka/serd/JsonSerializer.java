package com.liu.kafka.serd;

import com.google.gson.Gson;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 9:24
 **/
public class JsonSerializer<T> implements Serializer<T> {


    private Gson gson=new Gson();

    @Override
    public void configure(Map map, boolean b) {



    }

    @Override
    public byte[] serialize(String topic, T t) {
        return gson.toJson(t).getBytes(Charset.forName("UTF-8"));
    }



    @Override
    public void close() {

    }
}
