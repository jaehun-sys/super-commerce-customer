package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Cart;
import com.bestcommerce.customer.domain.CartKey;
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
            "where c.customer.cuId = :cu_id " +
            "and c.product.productId = :product_id " +
            "and c.size.sizeId = :size_id")
    int increaseProductCountByCartKey(Long cu_id, Long product_id, Long size_id, int count);
}
