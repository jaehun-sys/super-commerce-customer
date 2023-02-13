package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class AddressDto {

    private Long customerId;

    private String addr;

    private Character represent;
    private String zipcode;

}
