package com.bestcommerce.util.converter;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.address.entity.Address;
import com.bestcommerce.cart.entity.CartKey;
import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.cart.dto.CartKeyDto;
import com.bestcommerce.payment.dto.PaymentLogDto;
import com.bestcommerce.payment.entity.PaymentLog;
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

    public PaymentLog toPaymentLog(PaymentLogDto paymentLogDto, Customer customer){
        return new PaymentLog(paymentLogDto.getPayNo(), paymentLogDto.getTotalPrice(), paymentLogDto.getOrderDate(), customer);
    }
}
