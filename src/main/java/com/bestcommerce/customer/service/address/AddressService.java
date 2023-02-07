package com.bestcommerce.customer.service.address;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.repository.domain.AddressRepository;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import com.bestcommerce.customer.service.account.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;


    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository){
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public void saveAddress(Address address){

        addressRepository.save(address);
    }

    public void saveAddressByCustomerId(AddressDto addressDto){
        AccountService accountService = new AccountService(customerRepository);
        Customer customer = accountService.getOneCustomerInfo(addressDto.getCustomerId());
        Address address = new Address(addressDto.getAddr(), addressDto.getRepresent(), addressDto.getZipcode(), customer);
        addressRepository.save(address);
    }

    public List<Address> getAllAddressesByCustomer(Customer customer){
        return addressRepository.getAddressesByCustomer(customer);
    }
}
