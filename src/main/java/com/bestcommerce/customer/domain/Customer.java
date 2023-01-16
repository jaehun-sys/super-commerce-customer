package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "customer")
@Data
public class Customer {

    @Id
    private long cu_id;
    private String cu_email;

    private String password;
    private String cu_name;

    private String cu_telno;

    private String birthdate;

    private Character auth_yn;
}
