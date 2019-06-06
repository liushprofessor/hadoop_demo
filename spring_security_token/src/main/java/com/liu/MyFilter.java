package com.liu;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Liush
 * @description
 * @date 2019/6/6 15:35
 **/
public class MyFilter extends AbstractAuthenticationProcessingFilter {




    protected MyFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String token=httpServletRequest.getHeader("t_token");
        if(token==null || "".equals(token.trim())){
            httpServletResponse.sendError(403,"找不到token");
            return null;
        }
        String[] tokens=token.split(",");
        String username=tokens[0];
        String role=tokens[1];
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(role);
        MyToken myToken=new MyToken(Arrays.asList(grantedAuthority),username,"123");
        Authentication authentication =getAuthenticationManager().authenticate(myToken);
        return authentication;
    }


}
