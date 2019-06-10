package com.integration.mapper;

import com.integration.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 13:54
 **/
@Mapper
public interface UserMapper {

    @SelectProvider(type = UserMapperSql.class,method ="findUserById" )
    User findUserById(String id);


}
