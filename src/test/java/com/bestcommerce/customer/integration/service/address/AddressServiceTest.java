package com.bestcommerce.customer.integration.service.address;


import com.bestcommerce.address.entity.Address;
import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.address.service.AddressService;
import com.bestcommerce.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @Test
    public void putAddressTest() throws Exception{
        Customer account = customerService.getOneCustomerInfo(1L);
        Address expected = new Address( "서울특별시",'Y',"????",account);
        addressService.saveAddress(expected);

        List<Address> actual = addressService.getAllAddressesByCustomer(account);

        assertEquals(expected, actual.get(1));
    }
}
