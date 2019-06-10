package com.integration.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 14:20
 **/
public class RoleGrantedAuthority implements GrantedAuthority {

    private String roleName;

    public RoleGrantedAuthority(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
