package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.exception.MallBusinessException;
import com.sample.mapper.ProductMapper;
import com.sample.vo.Product;

/*
 * @Service
 * 		- <context:component-scan /> 설정으로 클래스가 자동으로 스캔되고, 객체 생성 후 스프링의 빈으로 등록되게 한다.
 */
@Service
public class ProductService {

	/*
	 * @Autowired
	 * 		- 비즈니스 로직 수행에 필요한 db access작업을 실행할 매퍼 인터페이스의 구현객체가 자동으로 의존성 주입되게 한다.
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
	public void addProduct(int productNo, int amount) {
		Product product = productMapper.getProductByNo(productNo);
		product.setStock(product.getStock() + amount);
		
		productMapper.updateProduct(product);
	}
	
	/**
	 * 상품번호를 전달받아서 해당 상품정보를 반환하낟
	 * @param productNo 상품정보, 상품이 존재하지 않는 경우 ull을 반환한다.
	 */
	public Product getProductDetail(int productNo) {
		Product product = productMapper.getProductByNo(productNo);
		if (product == null) {
			throw new MallBusinessException("상품번호가 올바르지 았습니다.");
		}
		
		return product;
	}
	
}
