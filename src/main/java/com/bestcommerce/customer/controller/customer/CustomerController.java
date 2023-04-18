package com.bestcommerce.customer.controller.customer;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.customer.CustomerService;
import com.bestcommerce.customer.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class CustomerController {

    private final CustomerService customerService;

    private final EntityConverter entityConverter;

    @PostMapping("/check/email")
    public Boolean checkEmail(@RequestBody CustomerDto customerDto){
        return customerService.isUsableEmail(customerDto.getCustomerEmail());
    }

    @PostMapping("/register")
    public void register(@RequestBody CustomerDto customerDto){
        customerService.save(entityConverter.toCustomer(customerDto));
    }

    @PostMapping("/update")
    public void update(@RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
    }
}
