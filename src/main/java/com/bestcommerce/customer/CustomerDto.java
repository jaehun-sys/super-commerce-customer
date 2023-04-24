package com.bestcommerce.customer;

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

    private Character authYn;

    private String registerDate;

    private String modifyDate;

    public CustomerDto(long customerId, String cuEmail, String password, String cuName, String cuTelNumber, String birthdate, Character authYn, String registerDate, String modifyDate){
        this.customerId = customerId;
        this.customerEmail = cuEmail;
        this.customerPassword = password;
        this.customerName = cuName;
        this.customerTelNumber = cuTelNumber;
        this.customerBirthDate = birthdate;
        this.authYn = authYn;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
    }
}
