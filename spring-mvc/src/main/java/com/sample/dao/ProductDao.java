package com.sample.dao;

import com.sample.vo.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 모든 상품정보를 반환한다.
     * @return 상품정보 목록
     */
    List<Product> getAllProducts();
}
