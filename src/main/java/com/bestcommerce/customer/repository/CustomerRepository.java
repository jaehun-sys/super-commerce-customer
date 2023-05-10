package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMember(Member member);


}
