package com.bestcommerce.address.repository;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> getAddressesByCustomer(Customer customer);

    @Transactional
    void deleteAddressByAddrId(Long addr_id);

    @Transactional
    @Modifying
    @Query("update address set addr = :addressInfo, zipCode = :zipCode where addrId = :addr_id")
    void updateAddressInformation(Long addr_id, String addressInfo, String zipCode);
}
