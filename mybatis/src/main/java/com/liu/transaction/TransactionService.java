package com.liu.transaction;

import com.liu.SearchUserI;
import com.liu.User;
import org.apache.ibatis.session.SqlSession;
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
    private SqlSession sqlSession;

    @Transactional
    public void insertUser(User user) {

        searchUserI.insertUser(user);
        //throw new RuntimeException("错误");


    }

    @Transactional
    public void searchUser() throws InterruptedException {
        while (true) {
            List<User> users=searchUserI.searchUsers("5");
            System.out.println(users.get(0).getName());
            Thread.sleep(5000);
        }

    }

    @Transactional
    public void updateUser(User user){
        searchUserI.updateUser(user);
        sqlSession.flushStatements();
    }



}
