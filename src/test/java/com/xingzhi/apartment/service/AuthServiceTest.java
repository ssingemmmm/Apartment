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
    User user = new User();

    @Before
    public void init(){
        user.setEmail("dwang@training.ascendingdc.com");
        user.setPassword("25f9e794323b453885f5181f1b624d0b");
    }

    @Test
    public void getUser(){
        System.out.println( authService.authenticate(user));

    }
}
