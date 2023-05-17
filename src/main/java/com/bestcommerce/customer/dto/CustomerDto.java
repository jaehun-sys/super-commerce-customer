package com.bestcommerce.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDto {

    private long customerId;

    private String customerName;

    private String customerEmail;

    private String customerPassword;

    private String customerTelNumber;

    private String customerBirthDate;

    private String registerDate;

    private String modifyDate;

    public CustomerDto(long customerId, String cuName, String customerEmail, String customerPassword, String cuTelNumber, String birthdate, String registerDate, String modifyDate){
        this.customerId = customerId;
        this.customerName = cuName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerTelNumber = cuTelNumber;
        this.customerBirthDate = birthdate;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
    }
}
