package com.liu;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Liush
 * @description
 * @date 2019/6/6 15:51
 **/
public class MyToken extends AbstractAuthenticationToken {

    private String username;

    private String password;


    public MyToken(Collection<? extends GrantedAuthority> authorities,String username,String password) {
        super(authorities);
        this.username=username;
        this.password=password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
