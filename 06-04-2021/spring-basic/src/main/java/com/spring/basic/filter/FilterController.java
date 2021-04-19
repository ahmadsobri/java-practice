package com.spring.basic.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @GetMapping("/first/*")
    public String callFirstFilter(){
        return "fisrt filter";
    }

    @GetMapping("/second/*")
    public String callSecondFilter(){
        return "second filter";
    }

    @GetMapping("/register/*")
    public String callRegistrationFilter(){
        return "filter just only url /register/* pattern";
    }

    @GetMapping("/web-filter/*")
    public String callWebFilter(){
        return "filter using @WebFilter";
    }
}
