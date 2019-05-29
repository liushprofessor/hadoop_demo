package com.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liush
 * @description
 * @date 2019/5/29 16:56
 **/
//xml配置  https://www.liquibase.org/documentation/contexts.html
@SpringBootApplication
public class LiquibaseApp {


    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApp.class,args);

    	}

}
