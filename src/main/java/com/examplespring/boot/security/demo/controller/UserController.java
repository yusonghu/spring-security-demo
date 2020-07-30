package com.examplespring.boot.security.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

@RequestMapping("/user")
@RestController
public class UserController {


    @GetMapping("/list")
    public String userList() {
        return "userList";
    }

    @GetMapping("/info")
    public String info() {
        String username = null;
        //  当前认证通过的用户身份
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal != null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        return username;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('admin:add')")
    public String add() {
        return "我有add权限";
    }

    @PreAuthorize("hasAuthority('select')")
    @GetMapping("/select")
    public String select() {
        return "我有select权限";
    }

    @PreAuthorize("hasAnyAuthority('admin:add','admin:delete')")
    @GetMapping("/delete")
    public String delete() {
        return "我有delete权限";
    }
    @PreAuthorize("hasAnyAuthority('admin:add','admin:modify')")
    @GetMapping("/modify")
    public String modify() {
        return "我有modify权限";
    }
}
