package com.bestcommerce.address.controller;

import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.address.service.AddressService;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.util.converter.DtoConverter;
import com.bestcommerce.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    private final CustomerService customerService;

    private final DtoConverter dtoConverter;

    private final EntityConverter entityConverter;

    @PostMapping("/save")
    public void saveAddress(@RequestBody AddressDto addressDto){

        log.info("address put method");
        addressService.saveAddress(entityConverter.toAddress(addressDto, customerService.getOneCustomerInfo(addressDto.getCustomerId())));
    }

    @PostMapping("/get")
    public List<AddressDto> getAllAddress(@RequestBody CustomerDto customerDto){
        return dtoConverter.toAddressDtoList(addressService.getAllAddressesByCustomer(customerService.getOneCustomerInfo(customerDto.getCustomerEmail())));
    }

    @PostMapping("/update")
    public void updateAddress(@RequestBody AddressDto addressDto){
        addressService.updateAddress(addressDto.getAddressId(), addressDto.getAddr(), addressDto.getZipcode());
    }
}
