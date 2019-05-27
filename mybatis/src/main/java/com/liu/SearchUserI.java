package com.liu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/5/27 10:04
 **/
@Mapper
public interface SearchUserI {

    @SelectProvider(type = UserSql.class, method = "searchUsers")
     List<User> searchUsers(@Param("id") String id);




}
