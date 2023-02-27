package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Cart;
import com.bestcommerce.customer.domain.CartKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, CartKey> {
    @Override
    boolean existsById(CartKey cartKey);
}
