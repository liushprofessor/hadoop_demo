package com.liu.kafka.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/4/22 0022 16:00
 **/
public class UserClick {

    private String userId;

    private String userName;

    private List<Click> clicks=new ArrayList<>();


    public UserClick add(Click click){
        clicks.add(click);
        return this;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Click> getClicks() {
        return clicks;
    }

    public void setClicks(List<Click> clicks) {
        this.clicks = clicks;
    }
}
