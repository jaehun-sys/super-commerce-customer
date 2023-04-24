package com.bestcommerce.customer.integration.service.address;


import com.bestcommerce.customer.address.Address;
import com.bestcommerce.customer.account.Customer;
import com.bestcommerce.customer.address.AddressService;
import com.bestcommerce.customer.account.CustomerService;
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
