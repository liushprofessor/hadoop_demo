package com.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liush
 * @description
 * 首先先创建hive和hbase的关联表参考https://www.cnblogs.com/MOBIN/p/5704001.html
 *  再创建kafka的队列topic参考  http://kafka.apachecn.org/documentation.html#introduction
 *  注意如果要进行stream连接分析创建的topic的分区数，和分区策略需要一样。
 *
 *
 * @date 2019/4/23 0023 10:09
 **/
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);


    	}


}
