package com.sample.dao;

import com.sample.vo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CartItemDao {

    void insertCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    CartItem getCartItem(@Param("userId") String userId, @Param("productNo") int productNo);
    List<Map<String, Object>> getCartItemsByUserId(String userId);
}
