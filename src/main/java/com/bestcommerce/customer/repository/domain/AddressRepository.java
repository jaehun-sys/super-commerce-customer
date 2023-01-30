package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> getAddressesByCustomerId(Long cu_id);
}
