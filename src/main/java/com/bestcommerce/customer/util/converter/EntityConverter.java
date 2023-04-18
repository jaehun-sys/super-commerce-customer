package com.bestcommerce.customer.util.converter;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.CartKey;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.dto.CartKeyDto;
import com.bestcommerce.customer.dto.CustomerDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntityConverter {

    public Customer toCustomer(CustomerDto customerDto){
        if(!StringUtils.hasText(customerDto.getRegisterDate())){
            return new Customer(customerDto.getCustomerEmail(), customerDto.getCustomerPassword(), customerDto.getCustomerName(), customerDto.getCustomerTelNumber(), customerDto.getCustomerBirthDate(), customerDto.getAuthYn(), LocalDate.now().toString(), customerDto.getModifyDate());
        }
        return new Customer(customerDto.getCustomerEmail(), customerDto.getCustomerPassword(), customerDto.getCustomerName(), customerDto.getCustomerTelNumber(), customerDto.getCustomerBirthDate(), customerDto.getAuthYn(), customerDto.getRegisterDate(), customerDto.getModifyDate());
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

    public CartKey toCartKey(CartKeyDto cartKeyDto){
        return new CartKey(cartKeyDto.getCustomerId(), cartKeyDto.getProductId(), cartKeyDto.getSizeId());
    }

    public List<CartKey> toCartKeyList(List<CartKeyDto> cartKeyDtoList){
        List<CartKey> cartKeyList = new ArrayList<>();
        for(CartKeyDto cartKeyDto : cartKeyDtoList){
            cartKeyList.add(toCartKey(cartKeyDto));
        }
        return cartKeyList;
    }
}
