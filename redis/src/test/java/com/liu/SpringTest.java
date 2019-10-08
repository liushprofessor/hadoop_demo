package com.liu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author Liush
 * @description
 * @date 2019/10/8 10:10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Test
    public void keyVal() {
        ValueOperations<String,String> valueOperations=redisTemplate.opsForValue();
        valueOperations.set("name","liuSH");

    }

    @Test
    public void getKeyVal(){
        ValueOperations<String,String> valueOperations=redisTemplate.opsForValue();
        System.out.println(valueOperations.get("name"));
    }


    @Test
    public void zSet(){
        ZSetOperations<String,String> zSetOperations=redisTemplate.opsForZSet();
        zSetOperations.add("price","phone",100L);
        zSetOperations.add("price","tel",50L);
        Set set=zSetOperations.rangeByScoreWithScores("price",0,1000);
        System.out.println(set);
        //Set set=zSetOperations.rangeByScore()
    }

    @Test
    public void hash(){
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        hashOperations.put("item","phone","nokia");
        String phoneName=hashOperations.get("item","phone");
        System.out.println(phoneName);

    }

}
