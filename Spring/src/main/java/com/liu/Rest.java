package com.liu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liush
 * @description
 * @date 2019/6/13 11:43
 **/
@RestController
public class Rest {

    @RequestMapping("hello")
    public String hello(){

        throw new RuntimeException("错误");

    }



}
