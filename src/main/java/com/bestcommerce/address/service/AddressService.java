package com.bestcommerce.address.service;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.address.repository.AddressRepository;
import com.bestcommerce.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void saveAddress(Address address){
        addressRepository.save(address);
    }

    public List<Address> getAllAddressesByCustomer(Customer customer){
        return addressRepository.getAddressesByCustomer(customer);
    }

    public void deleteAddressByAddrId(Long addressId){
        addressRepository.deleteAddressByAddrId(addressId);
    }

    public void updateAddress(Long id, String addressInformation, String zipCode){
        if(addressRepository.existsById(id)){
            addressRepository.updateAddressInformation(id,addressInformation,zipCode);
            return;
        }
        throw new RuntimeException("Wrong Address ID : "+id);
    }
}
