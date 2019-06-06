package com.liu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author Liush
 * @description
 * @date 2019/6/4 14:08
 **/
@Mapper
public interface UrlRoleMapper {

    @SelectProvider(type =UrlRoleSql.class ,method ="findUrlByRole" )
    UrlRole findUrlByRole(String url);


}
