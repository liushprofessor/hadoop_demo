package com.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author Liush
 * @description  动态加载权限url，实现从数据库获取url并鉴权
 * @date 2019/5/30 13:50
 **/
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        //UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
       // AntPathRequestMatcher antPathRequestMatcher;
       // SecurityConfig securityConfig;
        //FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
        //DefaultFilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource;
        //RoleVoter roleVoter;
        /*AuthenticationManager authenticationManager;
        AuthenticationProvider authenticationProvider;
        ProviderManager providerManager;
        AccessDecisionManager accessDecisionManager;
        AccessDecisionVoter accessDecisionVoter;
        DelegatingFilterProxy delegatingFilterProxy;
        FilterChainProxy filterChainProxy;
        SecurityProperties.BASIC_AUTH_ORDER;*/
    }
}
