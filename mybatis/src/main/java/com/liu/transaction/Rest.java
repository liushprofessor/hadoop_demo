package com.liu.transaction;

import com.liu.SearchUserI;
import com.liu.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liush
 * @description
 * @date 2019/6/17 16:42
 **/
@RestController
public class Rest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SearchUserI searchUserI;

    @RequestMapping("findUser")
    public String findUser()throws Exception{
        transactionService.findUserOne();
        return null;
    }

    //注解版开启二级缓存
    @RequestMapping("findUser2")
    public String findUser2(){

        User user=searchUserI.findUserOne("5");
        System.out.println(user.getName());
        return null;
    }

    @RequestMapping("updateUser")
    public String updateUser(){
        User user=new User();
        user.setId("5");
        user.setName("tegwdf");
        transactionService.updateUser(user);
        return null;
    }




}
