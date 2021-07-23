package com.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.service.UserService;
import com.sample.vo.User;

public class SpringApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-tx-aop.xml");
		
		UserService userService = context.getBean(UserService.class);
		User user = userService.getUserDetail("hong1000");
		System.out.println(user);
		
		User user2 = User.builder()
						 .id("hong1512").password("zxcv1234")
						 .name("홍길똥").email("hong1512").phone("010-125-1598")
						 .build();
		
		System.out.println();
		System.out.println("############ 신규 사용자 정보 등록"); 
		userService.addNewUser(user2);
		 
	}
}
