package com.liu;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/5/27 10:30
 **/
public class UserRole {


    private String id;

    private String name;

    private List<String> role;


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

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
