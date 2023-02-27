package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> getAddressesByCustomer(Customer customer);

    @Transactional
    void deleteAddressByAddrId(Long addr_id);
}
