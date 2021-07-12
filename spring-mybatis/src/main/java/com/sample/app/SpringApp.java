package com.sample.app;

import com.sample.service.ProductService;
import com.sample.service.UserService;
import com.sample.vo.Product;
import com.sample.vo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;
import java.util.List;

public class SpringApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-mybatis.xml");

        /*ProductService service = context.getBean(ProductService.class);

        List<Product> products = service.getAllProductList();

        for (Product product : products) {
            System.out.println(product.getName() + ", " + product.getMaker());
        }*/

        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setId("kim");
        user.setName("kimusin");
        user.setPassword("zxcv1234");
        user.setEmail("kim2@naver.com");
        user.setPhone("01098765431");

        userService.addNewUser(user);
    }
}
