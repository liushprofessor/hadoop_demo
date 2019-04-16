package stream.liu.domain;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/4/15 0015 11:16
 **/
public class UserState {

    private String userName;

    private Long viewCount;


    public UserState addViewCount(User user){
        /*System.out.println(user.getUserName());
        System.out.println(user.getView());*/
        System.out.println(viewCount);
        System.out.println("user________"+user);
        if(viewCount!=null) {
            viewCount += 1;
        }else {
            viewCount=0L;
        }
        if(user!=null)
            userName=user.getUserName();
        return this;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return userName+"  ___  "+viewCount;
    }
}
