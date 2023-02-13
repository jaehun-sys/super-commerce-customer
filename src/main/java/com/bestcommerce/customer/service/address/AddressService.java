package com.bestcommerce.customer.service.address;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.AddressDto;
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

    public void saveAddressByCustomerId(Customer customer, AddressDto addressDto){
        Address address = new Address(addressDto.getAddr(), addressDto.getRepresent(), addressDto.getZipcode(), customer);
        addressRepository.save(address);
    }

    public List<Address> getAllAddressesByCustomer(Customer customer){
        return addressRepository.getAddressesByCustomer(customer);
    }
}
