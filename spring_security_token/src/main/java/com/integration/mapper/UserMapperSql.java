package com.integration.mapper;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 13:57
 **/
public class UserMapperSql {

    public String findUserById(String id){

        return "select * from  t_user where id=#{id}";

    }

}
