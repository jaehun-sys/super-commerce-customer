package com.bestcommerce.customer.controller.account;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.util.EntityConverter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final EntityConverter entityConverter;

    public AccountController(AccountService accountService, EntityConverter entityConverter){
        this.accountService = accountService;
        this.entityConverter = entityConverter;
    }

    @PostMapping("/check/email")
    public Boolean checkEmail(@RequestParam("customerEmail") String customerEmail){
        return accountService.isUsableEmail(customerEmail);
    }

    @PostMapping("/register")
    public void register(@RequestBody CustomerDto customerDto){
        accountService.save(entityConverter.toCustomer(customerDto));
    }
}
