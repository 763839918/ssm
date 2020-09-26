package cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class hello {
    @RequestMapping("/hello")
    public String find(){
        return "hello";
    }
}
