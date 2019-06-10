package com.integration.domain;

import java.util.List;
import java.util.Set;

/**
 * @author Liush
 * @description  也可以实现自己的UserDetails类
 * @date 2019/6/10 13:55
 **/
public class User {

    private String id;

    private String name;

    private String password;

    private Set<Role> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
