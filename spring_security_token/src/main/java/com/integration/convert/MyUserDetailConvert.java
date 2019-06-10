package com.integration.convert;

import com.integration.domain.MyUserDetails;
import com.integration.domain.Role;
import com.integration.domain.RoleGrantedAuthority;
import com.integration.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 14:12
 **/
@Component
public class MyUserDetailConvert {



    public MyUserDetails toMyUserDetail(User user){
        if (user==null){
            return null;
        }
        Set<Role> roles=user.getRoles();
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        if (roles!=null){
            roles.forEach(role -> {
                RoleGrantedAuthority roleGrantedAuthority=new RoleGrantedAuthority(role.getRoleName());
                grantedAuthorities.add(roleGrantedAuthority);

            });
        }
        return  new MyUserDetails(user.getId(),user.getName(),user.getPassword(),grantedAuthorities);
    }


}
