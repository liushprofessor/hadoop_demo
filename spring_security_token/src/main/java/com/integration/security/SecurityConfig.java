package com.integration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 15:57
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private DbUserDetailsServer dbUserDetailsServer;

    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                //设置认证异常统一处理 捕获AuthenticationException 401
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                //.exceptionHandling().accessDeniedHandler()设置鉴权异常处理捕获 AccessDeniedException 403
                // don't create session

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //开启表单验证的话会默认使用表单验证，如果没有认证会自动跳转到登录页
                //且在过滤器链中在最后会添加UsernamePasswordAuthenticationFilter过滤器
                //我们这里使用token方式进行认证
                // .formLogin().and()
                .authorizeRequests()

                // Un-secure H2 Database
                //.antMatchers("/h2-console/**/**").permitAll()

                //.antMatchers("/auth/**").permitAll()
                //自定义鉴权
                .anyRequest().access("@securityAccess.access(authentication,request)");//authenticated();


        http.addFilterBefore(jwtAuthorizationTokenFilter,UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(dbUserDetailsServer);
    }
}
