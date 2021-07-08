package com.sample.app;

import com.sample.dao.UserDao;
import com.sample.vo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context-jdbc.xml");

        UserDao userDao = context.getBean(UserDao.class);

        User user = new User();
        user.setId("hong1234");
        user.setPassword("zxcv1234");
        user.setName("홍길동");
        user.setEmail("hong@naver.com");
        user.setPhone("010-1234-5678");

        userDao.insertUser(user);
        System.out.println("유저정보 입력이 완료되었습니다.");
    }
}
