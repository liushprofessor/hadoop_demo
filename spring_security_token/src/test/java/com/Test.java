package com;

import com.integration.security.JwtUtil;

import java.util.HashMap;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 16:40
 **/
public class Test {

    @org.junit.Test
    public void test(){

        System.out.println(JwtUtil.createToken(new HashMap<>(),"1"));

    }


}
