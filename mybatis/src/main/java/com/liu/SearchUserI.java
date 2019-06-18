package com.liu;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/5/27 10:04
 **/
@Mapper
@CacheNamespace(blocking = true)
public interface SearchUserI {

    @SelectProvider(type = UserSql.class, method = "searchUsers")
     List<User> searchUsers(@Param("id") String id);

    @Select("select id ,name from t_user where id=#{id}")
    @Options(flushCache =Options.FlushCachePolicy.TRUE )
    List<User> searchUsers2(@Param("id") String id);

    @Insert("insert into t_user(id,name) values(#{id},#{name}) ")
    void insertUser(User user);

    @Update("update t_user set name=#{name} where id=#{id}")
    void updateUser(User user);

    @Select("select id ,name from t_user where id=#{id}")
    User findUserOne(@Param("id") String id);



}
