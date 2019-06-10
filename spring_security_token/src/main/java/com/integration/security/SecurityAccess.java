package com.integration.security;

import com.integration.domain.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 17:44
 **/
@Component
public class SecurityAccess {


    public boolean access(Authentication authentication, HttpServletRequest request){
        MyUserDetails myUserDetails=(MyUserDetails)authentication.getPrincipal();
        return "2".equals(myUserDetails.getId());
    }


}
