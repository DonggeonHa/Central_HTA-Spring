package com.sample.service;

import com.sample.dao.ProductDao;
import com.sample.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 상품정보관련 업무로직 메소드를 전부 구현하고 있는 구현 클래스다. <br />
 */
@Service
public class ProductServiceImpl implements ProductService{

    /*
        ProductDao 매퍼 인터페이스를 구현한 매퍼 인스턴스(객체)가 주입된다.
        ProductDao 매퍼 인터페이스의 구현은 mybatis가 책임진다.
     */
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getAllProducts() {

        return null;
    }
}
