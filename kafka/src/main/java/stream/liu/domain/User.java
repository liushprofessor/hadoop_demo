package stream.liu.domain;

import com.google.gson.Gson;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 10:40
 **/
public class User  {

    private String userName;

    private String view;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "用户字符串"+userName+"   "+view;
    }
}
