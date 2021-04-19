package com.spring.basic.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterWithUrlPattern implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("Registration Filter Request URI pattern is: " + req.getRequestURI());
        chain.doFilter(request, response);
        System.out.println("Registration Filter Response Status Code is: " + res.getStatus());
    }
}
