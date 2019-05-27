package com.liu;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author Liush
 * @description
 * @date 2019/5/27 10:04
 **/
public class UserSql {

    //如果是当个简单参数的话也可以直接用id去接收，然后mapper中不要加@parm注解
    public String searchUsers(Map<String,Object> id){

        return "select id ,name from t_user where id="+id.get("id");

    }





}
