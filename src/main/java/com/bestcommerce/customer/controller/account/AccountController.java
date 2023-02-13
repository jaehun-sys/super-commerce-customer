package com.bestcommerce.customer.controller.account;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.account.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/check/email")
    public Boolean checkEmail(@RequestParam("customerEmail") String customerEmail){
        return accountService.isUsableEmail(customerEmail);
    }

    @PostMapping("/register")
    public void register(@RequestBody CustomerDto customer){
        accountService.save(customer);
    }
}
