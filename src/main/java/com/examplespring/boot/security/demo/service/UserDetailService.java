package com.examplespring.boot.security.demo.service;

import com.examplespring.boot.security.demo.model.UserDto;
import com.examplespring.boot.security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据获取账号信息
        UserDto userDto = userRepository.getUserByUserName(username);
        if (userDto == null) {
            return null;
        }
        UserDetails userDetails = User.withUsername(userDto.getUsername())
                .password(userDto.getPassword())
                .authorities("select","admin:modify").build();
        return userDetails;
    }
}
