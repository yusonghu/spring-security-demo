package com.examplespring.boot.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/list")
    public String listRoles(){
        return "roleList";
    }

    @GetMapping("/add")
    public String add(){
        return "roleAdd";
    }
}
