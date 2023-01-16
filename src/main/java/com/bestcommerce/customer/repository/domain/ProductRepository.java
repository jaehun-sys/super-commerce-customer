package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
