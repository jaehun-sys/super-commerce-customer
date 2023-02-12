package com.bestcommerce.customer.controller.address;

import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.address.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    private final AccountService accountService;

    public AddressController(AddressService addressService, AccountService accountService){
        this.addressService = addressService;
        this.accountService = accountService;
    }

    @PostMapping("/save")
    public void saveAddress(@RequestBody AddressDto address){
        log.info("address put method");
        addressService.saveAddressByCustomerId(accountService.getOneCustomerInfo(address.getCustomerId()),address);
    }
}
