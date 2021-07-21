package com.sample.dao;

import com.sample.vo.CartItem;
import org.apache.ibatis.annotations.Param;

public interface CartItemDao {

    void insertCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    CartItem getCartItem(@Param("userId") String userId, @Param("productNo") int productNo);
}
