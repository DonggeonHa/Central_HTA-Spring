package com.sample.service;

import com.sample.mapper.ProductMapper;
import com.sample.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @Service
        - <context:component /> 설정으로 클래스가 자동으로 스캔되고, 객체 생성 후 스프링의 빈으로 등록되게 한다.
 */
@Service
public class ProductService {

    /*
     *  @Autowired
     *      - 비즈니스 로직 수행에 필요한 DB access작업을
     */
    @Autowired
    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * 모든 상품 정보를 조회한다.
     * @return 상품리스트
     */
    public List<Product> getAllProductList() {
        System.out.println("의존성 주입된 객체: " + productMapper);
        return productMapper.getAllProducts();
    }

    /**
     * 새 상품정보를 데이터베이스에 저장한다.
     * @param product 새 상품정보
     */
    public void insertNewProduct(Product product) {
        productMapper.insertProduct(product);
    }

    /**
     * 지정된 상품번호의 상품에 대하여 재고량을 증가시킨다.
     * @param productNo 상품번호
     * @param amount 새로 입고된 상품 수량
     */
    public void addProducts(int productNo, int amount) {
        Product product = productMapper.getProductByNo(productNo);
        product.setStock(product.getStock() + amount);

        productMapper.updateProduct(product);
    }
}
