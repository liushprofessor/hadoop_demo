package com.liu.dynamically;

import com.liu.dynamically.mapper.UrlRole;
import com.liu.dynamically.mapper.UrlRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Liush
 * @description
 * @date 2019/6/4 13:50
 **/
@Component
public class MyMataDataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private UrlRoleMapper urlRoleMapper;


    /**
     * 这里是鉴权部分
     * 从数据库中获取角色的授权列表，将权限装换成Collection<ConfigAttribute>给系统认证
     *  在访问url时，系统将会去数据库中查询对应的授权，和登录时获取的UserDetail进行对比，如果匹配则通过授权
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        UrlRole urlByRole=urlRoleMapper.findUrlByRole(url);
        Collection<ConfigAttribute> list=new ArrayList<>();
        if(urlByRole==null){
            return list;
        }
        SecurityConfig securityConfig=new SecurityConfig("ROLE_"+urlByRole.getRole());
        list.add(securityConfig);
        return list;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }


}
