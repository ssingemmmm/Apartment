package com.xingzhi.apartment.service;


import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class AuthServiceTest {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    User user = new User();

    @Before
    public void init(){
        user.setEmail("abc@abc.com");
        user.setPassword("c4ca4238a0b923820dcc509a6f75849b");
        user.setName("abc");
        userService.save(user);
    }

    @Test
    public void getUser(){
        System.out.println( authService.authenticate(user));

    }
}
