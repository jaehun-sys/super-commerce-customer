package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
