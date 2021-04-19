package com.spring.basic.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/web-filter/*") //need @ServletComponentScan in main class
public class FilterWithWebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("WebFilter Request URI is: " + req.getRequestURI());
        chain.doFilter(request, response);
        System.out.println("WebFilter Response Status Code is: " + res.getStatus());
    }
}
