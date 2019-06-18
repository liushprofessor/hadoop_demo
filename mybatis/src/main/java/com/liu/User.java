package com.liu;

import java.io.Serializable;

/**
 * @author Liush
 * @description
 * @date 2019/5/27 10:09
 **/
public class User implements Serializable {

    private String id;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
