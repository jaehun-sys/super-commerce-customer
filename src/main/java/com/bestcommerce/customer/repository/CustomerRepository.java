package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
