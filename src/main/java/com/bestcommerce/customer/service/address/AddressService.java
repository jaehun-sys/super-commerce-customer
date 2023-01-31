package com.bestcommerce.customer.service.address;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.repository.domain.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public void saveAddress(Address address){


        addressRepository.save(address);
    }

    public void saveAddressByCustomerId(Long cu_id, Address address){
        address.setCustomerId(cu_id);
        addressRepository.save(address);
    }

    public List<Address> getAllAddressByCustomerId(Long id){
        return addressRepository.getAddressesByCustomerId(id);
    }
}
