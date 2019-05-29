package com.liu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Liush
 * @description  对应的xml要放在resources对应的包下，加上Mapper注解或者用MapperScan扫描
 * @date 2019/5/28 9:47
 **/
@Mapper
public interface SearchUserXmlI {

    List<User> searchUsers( String id);

    //联合查询
    List<UserRole> searchUserRoles(String id);

    //结果联合查询
    List<UserRole> searchUserRoles3(String id);

}
