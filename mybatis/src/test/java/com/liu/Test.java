package com.liu;

import com.liu.transaction.TransactionService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/5/27 10:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private SearchUserI searchUserI;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SearchUserXmlI searchUserXmlI;

    @org.junit.Test
    public void searchUsers(){

        List<User> users=searchUserI.searchUsers("1");
        System.out.println(users);

    }

    @org.junit.Test
    public void searchUsers2(){
        List<User> users=searchUserI.searchUsers2("1");
        System.out.println(users);

    }


    @org.junit.Test
    public void searchUserXml(){
        //List<User> users=searchUserXmlI.searchUsers("1");
        //System.out.println(users);
    }

    //联合查询
    @org.junit.Test
    public void searchUserRoles(){
        List<UserRole> users=searchUserXmlI.searchUserRoles("1");
        System.out.println(users);

    }

    //联合结果查询
    @org.junit.Test
    public void searchUserRoles3(){
        List<UserRole> users=searchUserXmlI.searchUserRoles3("1");
        System.out.println(users);
    }



    //事务控制
    @org.junit.Test
    public void transactionTest(){

        User user=new User();
        user.setId("6");
        user.setName("ggg");
        transactionService.insertUser(user);

    }

    @org.junit.Test
    public void searchUser() throws Exception {
        //transactionService.searchUser();
        transactionService.findUserOne();
       // transactionService.findUserOne();

    }

    @org.junit.Test
    public void updateUser(){

        User user=new User();
        user.setId("5");
        user.setName("wwww");
        transactionService.updateUser(user);



    }











}
