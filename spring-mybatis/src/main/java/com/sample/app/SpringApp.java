package com.sample.app;

import com.sample.service.ProductService;
import com.sample.vo.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;
import java.util.List;

public class SpringApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/context-mybatis.xml");

        ProductService service = context.getBean(ProductService.class);

        List<Product> products = service.getAllProductList();

        for (Product product : products) {
            System.out.println(product.getName() + ", " + product.getMaker());
        }
    }
}
