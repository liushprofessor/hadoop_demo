package com.liu.dynamically;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liush
 * @description  这里是验证部分，自定义验证账号密码，原理是登录后悔调用此方法，从数据库中读取账号和密码
 * 然后返回UserDetail ，后续访问url会获取UserDetial中的角色也就是所有SimpleGrantedAuthority
 * 来和鉴权中查找的到的权限进行比较
 * @date 2019/6/4 15:17
 **/
@Component
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(!"user1".equals(s)){
            return null;
        }
        List<GrantedAuthority> list=new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("ROLE_user1");
        list.add(simpleGrantedAuthority);
        PasswordEncoderFactories passwordEncoderFactories;
        //第一个参数就是用户名，第二个参数就是密码，第三个参数就是我们的角色（做授权）
        return new User("user1","123456",list);
    }
}
