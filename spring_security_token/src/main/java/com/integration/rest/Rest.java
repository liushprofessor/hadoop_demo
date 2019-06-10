package com.integration.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 16:08
 **/
@RestController
public class Rest {

    @RequestMapping("hello")
    public String hello(){

        return "hello";
    }


}
