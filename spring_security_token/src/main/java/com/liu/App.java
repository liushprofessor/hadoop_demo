package com.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liush
 * @description  这部分是自定义认证过滤器，这部分模拟了用token进行认证（默认是session），结合spring_security
 * 完成一个基于token验证，和自定义数据库配置url进行鉴权的系统
 * @date 2019/6/6 15:23
 **/
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
