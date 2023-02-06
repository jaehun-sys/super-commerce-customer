package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
