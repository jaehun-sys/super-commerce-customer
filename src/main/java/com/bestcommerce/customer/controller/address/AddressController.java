package com.bestcommerce.customer.controller.address;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.service.address.AddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping("/save")
    public void saveAddress(@RequestParam("cu_id") Long cu_id, Address address){
        addressService.saveAddressByCustomerId(cu_id,address);
    }
}
