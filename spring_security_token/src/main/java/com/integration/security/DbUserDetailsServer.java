package com.integration.security;

import com.integration.convert.MyUserDetailConvert;
import com.integration.domain.MyUserDetails;
import com.integration.domain.User;
import com.integration.mapper.UserMapper;
import com.liu.mapper.UrlRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 11:38
 **/
@Component
public class DbUserDetailsServer implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyUserDetailConvert convert;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user=userMapper.findUserById(id);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", id));
        } else {
            return convert.toMyUserDetail(user);
        }
    }
}
