package com.integration.security;

import com.integration.domain.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Liush
 * @description
 * @date 2019/6/10 14:45
 **/
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {


    private String tokenHeader="Authorization";

    @Autowired
    private DbUserDetailsServer dbUserDetailsServer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")){
            authToken = requestHeader.substring(7);
            username = JwtUtil.getUsernameFromToken(authToken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails=dbUserDetailsServer.loadUserByUsername(username);
            MyUserDetails myUserDetails=(MyUserDetails)userDetails;
            try {
                //验证token是否过期，用户名是否对应的上
                if(JwtUtil.validateToken(authToken,myUserDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (UsernameNotFoundException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

            }

        }
        filterChain.doFilter(request, response);
    }
}
