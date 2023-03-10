package com.bestcommerce.customer.service;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.service.account.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;


    @Test
    @DisplayName("계정 정보 저장이 가능해야 한다.")
    public void joinTestCase01() throws Exception{
        Customer expected = new Customer("dudtkd0219@gmail.com","dudtkd0219","박영상","010-0000-0000","19960826",'Y');

        accountService.save(expected);

        Customer actual = accountService.getOneCustomerInfo(expected.getCuId());

        assertEquals(expected,actual);
    }
}
