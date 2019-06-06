package com.liu.mapper;

/**
 * @author Liush
 * @description
 * @date 2019/6/4 14:09
 **/
public class UrlRoleSql {

    public String findUrlByRole(){

        return "select * from t_url where url=#{url}";
    }

}
