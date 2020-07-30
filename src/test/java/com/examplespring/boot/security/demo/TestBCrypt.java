package com.examplespring.boot.security.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {
    @Test
    public void testBCrypt(){
        //  对密码进行加密(加盐，每次不一样)
        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
        System.out.println(hashpw);

        //  校验
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$f/bCBGuReABB56mK4Ub4Cu56dRjtQ5TwL9x7YJZfvckIvUwjL5k8e");
        System.out.println(checkpw);

    }
}
