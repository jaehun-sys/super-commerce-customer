package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMember(Member member);

    @Transactional
    void deleteCustomerByMember(Member member);

}
