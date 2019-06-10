package com.liu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liush
 * @description  从数据库读取动态读取url信息
 * @date 2019/6/4 14:28
 **/
//@Configuration
//@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private MyUserDetailService myUserDetailService;



    *//**
     * 在SpringSecuritity中需要对密码进行编码
     * 所有的编码方式在PasswordEncoderFactories中，下面设置的是没有编码格式
     *//*

    @Autowired
   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        authenticationManagerBuilder.userDetailsService(myUserDetailService).
                passwordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationManagerBuilder.authenticationProvider(new MyProvider());


   }



    *//*
     * 创建我的自定义的url的interceptor
     * z
     *//*
    @Bean
    public MyFilter myFilter()throws Exception {
        //新建过滤器
        MyFilter filter=new MyFilter("/login2");
        filter.setAuthenticationManager(authenticationManager());
        return filter;
        *//*DynamicallyUrlInterceptor interceptor = new DynamicallyUrlInterceptor();
        interceptor.setSecurityMetadataSource(myMataDataSource);//设置授权验证数据的来源，这里是从数据库中读取授权

        //配置RoleVoter决策，这里使用的是角色投票者（角色钱加ROLE_），框架中之所以这样定义是因为，SpringSecurity
        //可以针对不同的url进行不同的验证，用AccessDecisionVoter做区分
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        decisionVoters.add(new RoleVoter());
        //设置认证决策管理器，主要设置投票范式，也就是鉴权时候已经什么前置开头，主要是为了对不同url进行不同方式鉴权而设定，提高效率一般用默认的RoleVoter即可（ROLE_）
        interceptor.setAccessDecisionManager(new DynamicallyUrlAccessDecisionManager(decisionVoters));
        return interceptor;*//*
    }

    *//**
     * 配置鉴权
     * @param http
     * @throws Exception
     *//*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(myFilter(), UsernamePasswordAuthenticationFilter.class).//添加自定义的过滤器
                csrf().disable()//关闭跨域验证
                .authorizeRequests()
                    .antMatchers("/login.html","/login2").permitAll()
                    .anyRequest().authenticated()   //所有没配置请求路径全部需要认证通过
                .and()
                    .formLogin()
                     .loginPage("/login.html")
                .loginProcessingUrl("/login2")//登录的rest接口url
                .and()
                     .logout()
                     .permitAll();
    }*/
}
