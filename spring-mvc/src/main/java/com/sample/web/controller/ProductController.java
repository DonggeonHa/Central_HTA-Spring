package com.sample.web.controller;

import com.sample.service.ProductService;
import com.sample.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    /*
        ProductService 인터페이스의 구현객체가 주입된다.
     */
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String products(Model model) {
        // 판매중인 전체 상품정보 조회하기
        List<Product> productList = productService.getAllProducts();
        
        // 뷰 페이지에 상품정보 목록 전달하기
        model.addAttribute("products", productList);

        // WEB-INF/views/product/list.jsp로 내부이동해서 JSP 실행시키기
        return "product/list";
    }
}

