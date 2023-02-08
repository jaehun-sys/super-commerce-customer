package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class CustomerDto {

    private long customerId;

    private String customerName;

    private String customerEmail;

    private String customerPassword;

    private String customerTelNumber;

    private String customerBirthDate;

    private Character authYn;

    public CustomerDto(String cuEmail, String password, String cuName, String cuTelNumber, String birthdate, Character authYn){
        this.customerEmail = cuEmail;
        this.customerPassword = password;
        this.customerName = cuName;
        this.customerTelNumber = cuTelNumber;
        this.customerBirthDate = birthdate;
        this.authYn = authYn;
    }
}
