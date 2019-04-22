package com.liu.kafka.model;

/**
 * @author Liush
 * @description
 * @date 2019/4/22 0022 15:55
 **/
public class Click {

    private String userId;

    private String clickName;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClickName() {
        return clickName;
    }

    public void setClickName(String clickName) {
        this.clickName = clickName;
    }
}
