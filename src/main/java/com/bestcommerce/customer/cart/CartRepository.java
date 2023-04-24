package com.bestcommerce.customer.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, CartKey> {
    @Override
    boolean existsById(CartKey cartKey);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update cart c set c.productCount = c.productCount + :count " +
            "where c.cartKey = :cartKey")
    int increaseProductCountByCartKey(CartKey cartKey, int count);

    Cart getCartByCartKey(CartKey cartKey);

}
