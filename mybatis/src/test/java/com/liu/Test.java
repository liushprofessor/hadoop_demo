package com.liu;

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
        List<User> users=searchUserXmlI.searchUsers("1");
        System.out.println(users);
    }














}
