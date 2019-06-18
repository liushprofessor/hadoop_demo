package com.liu;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Liush
 * @description
 * spring事务有四个等级，在mysql inndbn内核中默认采用可重复读（两个事务同时处理一条数据
 * 一个事务更新了，在另一个事务中读取到的仍然是旧的值），mybatis中有一级缓存，如果事务等级低于可重复读
 * 那么再两个事务同时操作一条记录中如（@Transactional( isolation =Isolation.READ_COMMITTED )），一个
 * 事务修改另一个事务就会看见该修改，但是mybatis默认会在当前事务中从缓存中读取，需要手动调用sqlSession.clearCache();
 * 或者在xml select标签中加上 flushCache="true"读取时刷新缓存，如果采用编码方式则加上@Options(flushCache =Options.FlushCachePolicy.TRUE )
 * 注解读取时刷新mybatis缓存
 * 在Spring中只有同一所中读取数据才能用到一级缓存，因为同一锁中的sqlSession是同一个，两次调用是获取不同的
 * sqlSession所以一级缓存无效
 * https://blog.csdn.net/ctwy291314/article/details/81938882
 * @date 2019/5/27 9:52
 **/
@SpringBootApplication
@EnableTransactionManagement
public class MybatisApp {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class, args);
        SqlSessionTemplate sqlSessionTemplate;

    }


}
