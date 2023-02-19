package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class AddressDto {

    private Long customerId;

    private String addr;

    private Character represent;
    private String zipcode;

    public AddressDto(Long customerId, String addr, Character represent, String zipcode) {
        this.customerId = customerId;
        this.addr = addr;
        this.represent = represent;
        this.zipcode = zipcode;
    }
}
