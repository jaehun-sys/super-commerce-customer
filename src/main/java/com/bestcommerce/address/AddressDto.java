package com.bestcommerce.address;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressDto {

    private Long addressId;

    private Long customerId;

    private String addr;

    private Character represent;
    private String zipcode;

    public AddressDto(Long addressId, Long customerId, String addr, Character represent, String zipcode) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.addr = addr;
        this.represent = represent;
        this.zipcode = zipcode;
    }
}
