package com.bestcommerce.product.repository;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.product.entity.Brand;
import com.bestcommerce.product.entity.BrandLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BrandLikeRepository extends JpaRepository<BrandLike, Long> {

    Boolean existsByCustomerAndBrand(Customer customer, Brand brand);

    @Transactional
    void deleteByCustomerAndBrand(Customer customer, Brand brand);
}
