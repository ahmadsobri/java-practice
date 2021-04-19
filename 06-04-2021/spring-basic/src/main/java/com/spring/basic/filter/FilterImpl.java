package com.spring.basic.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class FilterImpl extends OncePerRequestFilter { //filter all url application

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Second Filter Request URI is: " + httpServletRequest.getRequestURI());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("Second Filter Response Status Code is: " + httpServletResponse.getStatus());
    }
}
