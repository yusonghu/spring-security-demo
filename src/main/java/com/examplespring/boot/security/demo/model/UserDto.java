package com.examplespring.boot.security.demo.model;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String mobile;
}
