package com.examplespring.boot.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/page")
@RestController
public class PageController {
    @GetMapping("/home")
    public String login(){
        return "访问资源";
    }

}
