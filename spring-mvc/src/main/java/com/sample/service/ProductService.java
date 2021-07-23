package com.sample.service;

import com.sample.vo.CartItem;
import com.sample.vo.Product;

import java.util.List;
import java.util.Map;

/**
 * 상품과 관련된 업무로직 메소드가 정의된 인터페이스다. <br />
 * 여기 정의된 모든 기능은 ProductServiceImpl에서 구현한다.
 */
public interface ProductService {

    /**
     * 판매중인 모든 상품정보를 제공하는 서비스
     * @return 상품정보 목록
     */
    List<Product> getAllProducts();

    /**
     * 지정된 상품번호에 해당하는 상품의 상세정보를 제공하는 서비스
     * @param productNo 상품번호
     * @return 상품정보
     */
    Product getProductDetail(int productNo);

    /**
     * 지정된 장바구니 아이템정보를 장바구니에 추가하는 서비스
     * @param cartItem 장바구니 아이템 정보
     */
    void addCartItem(CartItem cartItem);

    /**
     * 지정된 사용자의 장바구니 아이템정보를 제공하는 서비스
     * @param userId 사용자 아이디
     * @return 장바구니 아이템
     */
    List<Map<String, Object>> getMyCartItems(String userId);
}
