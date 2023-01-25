package com.bestcommerce.customer.controller.account;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.service.account.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/check/email")
    public Boolean checkEmail(@RequestParam("cu_email") String cu_email){
        return accountService.isUsableEmail(cu_email);
    }

    @PostMapping("/register")
    public void register(Customer customer){
        accountService.save(customer);
    }
}
