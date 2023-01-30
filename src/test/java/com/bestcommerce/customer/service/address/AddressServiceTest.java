package com.bestcommerce.customer.service.address;


import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.service.account.AccountService;
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
    private AccountService accountService;

    @Test
    public void putAddressTest() throws Exception{
        Customer account = accountService.getOneCustomerInfo(1L);
        Address expected = new Address(account.getCuId(), "대구광역시",'Y',"이게뭐지",account);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(expected.getAddrId());
        System.out.println(expected.getCustomerId());
        System.out.println(expected.getAddr());
        addressService.saveAddress(expected);

        List<Address> actual = addressService.getAllAddressByCustomerId(1L);
        System.out.println(actual);

        assertEquals(expected, actual.get(0));
    }
}
