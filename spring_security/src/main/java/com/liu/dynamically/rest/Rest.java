package com.liu.dynamically.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/6/4 14:41
 **/
@RestController
public class Rest {

    @RequestMapping("/login2")
    public String test1(String username,String password){
        System.out.println(username);
        System.out.println(password);
        return "success";
    }


    @RequestMapping("/liu/find")
    public String test(){
        return "OK";
    }




}
