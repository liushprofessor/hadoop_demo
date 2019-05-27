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
public class MySpringTest {

    @Autowired
    private MyBean myBean;

    @org.junit.Test
    public void MyBeanTest(){


        System.out.println(myBean.getBeanName());


    }














}
