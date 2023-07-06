package com.bestcommerce.address.controller;

import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.address.service.AddressService;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.member.service.MemberService;
import com.bestcommerce.util.converter.DtoConverter;
import com.bestcommerce.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    private final CustomerService customerService;

    private final MemberService memberService;

    private final DtoConverter dtoConverter;

    private final EntityConverter entityConverter;

    @PostMapping("/save")
    public void saveAddress(@RequestBody AddressDto addressDto){

        log.info("address put method");
        addressService.saveAddress(entityConverter.toAddress(addressDto, customerService.getOneCustomerInfo(addressDto.getCustomerId())));
    }

    @PostMapping("/get")
    public List<AddressDto> getAllAddress(@RequestBody CustomerDto customerDto){
        return dtoConverter.toAddressDtoList(addressService.getAllAddressesByCustomer(customerService.getOneCustomerInfo(memberService.findMember(customerDto.getCustomerEmail()))));
    }

    @PostMapping("/update")
    public void updateAddress(@RequestBody AddressDto addressDto){
        addressService.updateAddress(addressDto.getAddressId(), addressDto.getAddr(), addressDto.getZipcode());
    }
}
