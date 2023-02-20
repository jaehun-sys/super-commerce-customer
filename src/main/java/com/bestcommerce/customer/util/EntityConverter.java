package com.bestcommerce.customer.util;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityConverter {
    public Customer toCustomer(CustomerDto customerDto){
        return new Customer(customerDto.getCustomerEmail(), customerDto.getCustomerPassword(), customerDto.getCustomerName(), customerDto.getCustomerTelNumber(), customerDto.getCustomerBirthDate(), customerDto.getAuthYn());
    }

    public List<Customer> toCustomerList(List<CustomerDto> customerDtoList){
        List<Customer> customerList = new ArrayList<>();
        for(CustomerDto customerDto : customerDtoList){
            customerList.add(toCustomer(customerDto));
        }
        return customerList;
    }

    public Address toAddress(AddressDto addressDto, Customer customer){
        return new Address(addressDto.getAddr(), addressDto.getRepresent(), addressDto.getZipcode(), customer);
    }
}
