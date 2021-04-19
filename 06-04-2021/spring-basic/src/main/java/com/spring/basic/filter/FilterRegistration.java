package com.spring.basic.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean<FilterWithUrlPattern> provideTestFilter() {
        final FilterRegistrationBean<FilterWithUrlPattern> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FilterWithUrlPattern());
        registrationBean.addUrlPatterns("/register/*");
        return registrationBean;
    }
}
