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
 * 一级缓存 会话基本 mybatis对应sqlsession
 * 二级缓存  应用基本，不同会话也可访问 mybatis对应namespace
 * 三级缓存  跨jvm 如redis
 * spring事务有四个等级，在mysql inndbn内核中默认采用可重复读（两个事务同时处理一条数据
 * 一个事务更新了，在另一个事务中读取到的仍然是旧的值），mybatis中有一级缓存，如果事务等级低于可重复读
 * 那么再两个事务同时操作一条记录中如（@Transactional( isolation =Isolation.READ_COMMITTED )），一个
 * 事务修改另一个事务就会看见该修改，但是mybatis默认会在当前事务中从缓存中读取，需要手动调用sqlSession.clearCache();
 * 或者在xml select标签中加上 flushCache="true"读取时刷新缓存，如果采用编码方式则加上@Options(flushCache =Options.FlushCachePolicy.TRUE )
 * 注解读取时刷新mybatis缓存
 * 在Spring中只有同一所中读取数据才能用到一级缓存，因为同一锁中的sqlSession是同一个，两次调用是获取不同的
 * sqlSession所以一级缓存无效,同样因为sqlsession不同所以update也无法对一级缓存进行刷新
 * 另外尽量不要对mybatis返回的对象进行操作，因为返回的对象是缓存在一级缓存中的，改变对象一级缓存中的对象也会改变
 * https://blog.csdn.net/ctwy291314/article/details/81938882
 * 二级缓存是基于namespqce的缓存（xml中 <mapper  namespace="com.liu.SearchUserXmlI"  >中的namespace），
 * 所有对于一张表的操作都需要放在一个namespace中，这样保证更新或者insert操作后后二级缓存可以更新，不然会导致
 * 脏读（在另一个namespace中操作后二级缓存没更新，因为没在用一个namespace下），xml开启二级缓存在xml中添加<cache/>即可，如SearchUserXmlI.xml所示
 * 注解版开启二级缓存在mapper接口上添加@CacheNamespace(blocking = true)（如SearchUserI）
 *
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
