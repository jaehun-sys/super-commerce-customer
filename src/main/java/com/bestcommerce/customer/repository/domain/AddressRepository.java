package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> getAddressesByCustomer(Customer customer);
}
