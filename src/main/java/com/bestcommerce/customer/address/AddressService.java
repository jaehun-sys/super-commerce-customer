package com.bestcommerce.customer.address;

import com.bestcommerce.customer.account.Customer;
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
