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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Liush
 * @description  动态加载权限url，实现从数据库获取url并鉴权，这部分主要实现的是鉴权部分的过滤器
 * //.access("@permissionChecker.hasPermission(authentication,request)")
 * 可以采用这种方法更加简单，@permissionChecker为Spring中注入的类，hasPermission(authentication,request)
 * access（@permissionChecker.checkUserId(authentication,#userId)） 在userid是变量的情况下
 * 为方法和参数
 * @date 2019/5/30 13:50
 **/
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
        Authentication authentication;
        ProviderManager providerManager;
        AccessDecisionVoter accessDecisionVoter;
        UserDetails userDetails;
        SecurityContextHolder securityContextHolder;
        UserCache userCache;
        ChannelProcessingFilter channelProcessingFilter;
        ConcurrentSessionFilter concurrentSessionFilter;
        AuthenticationProvider authenticationProvider;
        AbstractUserDetailsAuthenticationProvider abstractUserDetailsAuthenticationProvider;
        AuthenticationManager authenticationManager;
        GenericFilterBean genericFilterBean;
        AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter;
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
