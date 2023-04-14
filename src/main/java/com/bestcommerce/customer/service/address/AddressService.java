package com.bestcommerce.customer.service.address;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.repository.domain.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private static final Logger log = LoggerFactory.getLogger(AddressService.class);

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
