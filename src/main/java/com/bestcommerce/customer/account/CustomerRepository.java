package com.bestcommerce.customer.account;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCuEmail(String cu_email);

    @Transactional
    void deleteCustomerByCuEmail(String cu_email);

    boolean existsByCuEmail(String cu_email);
}
