package com.sample.service;

import com.sample.vo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/context-mybatis.xml")
public class TestUserService {

    @Autowired
    UserService userService;

    @Test
    public void configTest() {
        Assert.assertNotNull(userService);
    }

    @Test
    public void testAddNewUser() {
        User user = User.builder()
                .id("hong1212")
                .password("zxcv1234")
                .name("홍길동")
                .email("hong@gmail.com")
                .phone("010-1234-5678")
                .build();

        userService.addNewUser(user);


    }

    @Test
    public void testAddNewUserExistId() {

    }

    @Test
    public void testAddNewUserExistEmail() {

    }

    @Test
    public void testAddNewUserExistPhone() {

    }
}
