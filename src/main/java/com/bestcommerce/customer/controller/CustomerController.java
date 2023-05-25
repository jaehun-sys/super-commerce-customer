package com.bestcommerce.customer.controller;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.member.entity.Member;
import com.bestcommerce.member.service.MemberService;
import com.bestcommerce.util.converter.DtoConverter;
import com.bestcommerce.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class CustomerController {

    private final CustomerService customerService;

    private final MemberService memberService;

    private final EntityConverter entityConverter;

    private final DtoConverter dtoConverter;

    @PostMapping("/register")
    public void register(@RequestBody CustomerDto customerDto){
        memberService.saveMember(customerDto.getCustomerEmail(), customerDto.getCustomerPassword());
        Member member = memberService.findMember(customerDto.getCustomerEmail());
        customerService.save(entityConverter.toCustomer(customerDto, member));
    }

    @PostMapping("/update")
    public void update(@RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
    }

    @PostMapping("/get")
    public CustomerDto get(@RequestBody CustomerDto customerDto){
        Member member = memberService.findMember(customerDto.getCustomerEmail());
        return  dtoConverter.toCustomerDto(customerService.getOneCustomerInfo(member), member.getMemberEmail());
    }
}
