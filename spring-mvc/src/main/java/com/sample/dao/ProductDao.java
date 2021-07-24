package com.sample.dao;

import com.sample.vo.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 모든 상품정보를 반환한다.
     * @return 상품정보 목록
     */
    List<Product> getAllProducts();

    /**
     * 지정된 상품번호에 해당하는 상품정보를 반환한다
     * @param productNo 상품번호
     * @return 상품정보
     */
    Product getProductByNo(int productNo);
}
