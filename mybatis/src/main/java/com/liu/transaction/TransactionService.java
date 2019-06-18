package com.liu.transaction;

import com.liu.SearchUserI;
import com.liu.SearchUserXmlI;
import com.liu.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/6/17 15:29
 **/
@Component
public class TransactionService {

    @Autowired
    private SearchUserI searchUserI;

    @Autowired
    private SearchUserXmlI searchUserXml;

    @Autowired
    private SqlSession sqlSession;

    @Transactional
    public void insertUser(User user) {

        searchUserI.insertUser(user);
        //throw new RuntimeException("错误");


    }

    @Transactional(isolation =Isolation.READ_COMMITTED )
    public void searchUser() throws InterruptedException {
        while (true) {
            List<User> users=searchUserI.searchUsers2("5");
            System.out.println(users.get(0).getName());
            Thread.sleep(5000);
            System.out.println(sqlSession);
            //sqlSession.clearCache();
        }

    }

    //当前事务是READ_COMMITTED,两次读取可以读取到不同值（脏读取），如果需要脏读，则在xml select标签中加入
    //flushCache="true" 刷新mybatis的一级缓存，不然当前事务都会从缓存中读取
    //如果是用编码形式的话加上@Options(flushCache =Options.FlushCachePolicy.TRUE )
    //如SearchUserI中所示
    @Transactional( isolation =Isolation.READ_COMMITTED )
    public void updateUser(User user){
        searchUserXml.updateUser(user);
        System.out.println(sqlSession);
        System.out.println(sqlSession.getConfiguration().getLocalCacheScope());


        // sqlSession;
        //sqlSession.clearCache();
        //sqlSession.flushStatements();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findUserOne() throws InterruptedException {
        while (true) {
            User user = searchUserXml.searchUsers("5");
            System.out.println(user.getName());
            System.out.println(((SqlSessionTemplate) sqlSession));
            Thread.sleep(5000);
        }


    }



}
