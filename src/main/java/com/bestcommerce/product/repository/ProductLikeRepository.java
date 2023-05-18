package com.bestcommerce.product.repository;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {

    boolean existsByCustomerAndProduct(Customer customer, Product product);

    @Transactional
    void deleteByCustomerAndProduct(Customer customer, Product product);
}
