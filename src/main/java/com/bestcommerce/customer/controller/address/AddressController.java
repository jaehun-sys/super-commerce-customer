package com.bestcommerce.customer.controller.address;

import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.customer.CustomerService;
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

    private final CustomerService customerService;

    private final DtoConverter dtoConverter;

    private final EntityConverter entityConverter;

    public AddressController(AddressService addressService, CustomerService customerService, DtoConverter dtoConverter, EntityConverter entityConverter){
        this.addressService = addressService;
        this.customerService = customerService;
        this.dtoConverter = dtoConverter;
        this.entityConverter = entityConverter;
    }

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
