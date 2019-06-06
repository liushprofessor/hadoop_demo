package com.liu;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Liush
 * @description
 * @date 2019/6/6 15:56
 **/
public class MyProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        MyToken token= (MyToken) authentication;
        String userName=token.getUsername();
        if("user1".equals(userName)){
            token.setAuthenticated(true);
            return token;
        }else{
            throw new MyAuthenticationException("验证失败");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MyToken.class.isAssignableFrom(aClass);

    }
}
