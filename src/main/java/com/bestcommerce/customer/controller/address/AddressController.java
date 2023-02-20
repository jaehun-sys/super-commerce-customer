package com.bestcommerce.customer.controller.address;

import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.address.AddressService;
import com.bestcommerce.customer.util.DtoConverter;
import com.bestcommerce.customer.util.EntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    private final AccountService accountService;

    private final DtoConverter dtoConverter;

    private final EntityConverter entityConverter;

    public AddressController(AddressService addressService, AccountService accountService, DtoConverter dtoConverter, EntityConverter entityConverter){
        this.addressService = addressService;
        this.accountService = accountService;
        this.dtoConverter = dtoConverter;
        this.entityConverter = entityConverter;
    }

    @PostMapping("/save")
    public void saveAddress(@RequestBody AddressDto addressDto){
        log.info("address put method");
        addressService.saveAddressByCustomerId(entityConverter.toAddress(addressDto, accountService.getOneCustomerInfo(addressDto.getCustomerId())));
    }

    @PostMapping("/get")
    public List<AddressDto> getAllAddress(@RequestBody CustomerDto customerDto){
        return dtoConverter.toAddressDtoList(addressService.getAllAddressesByCustomer(accountService.getOneCustomerInfo(customerDto.getCustomerId())));
    }
}
