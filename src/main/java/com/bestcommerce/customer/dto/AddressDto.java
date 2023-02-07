package com.bestcommerce.customer.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long customerId;

    private String addr;

    private Character represent;
    private String zipcode;

}
