package com.examplespring.boot.security.demo.repository;

import com.examplespring.boot.security.demo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //  根据账号查询用户信息
    public UserDto getUserByUserName(String username){
        String sql = "SELECT * FROM `t_user` WHERE username = ?";
        List<UserDto> userDtoList = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if(CollectionUtils.isEmpty(userDtoList)){
            return null;
        }
        return userDtoList.get(0);
    }

}
