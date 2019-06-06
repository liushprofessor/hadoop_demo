package com.liu;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Liush
 * @description
 * @date 2019/6/6 16:07
 **/
public class MyAuthenticationException extends AuthenticationException{
    public MyAuthenticationException(String msg) {
        super(msg);
    }
}
