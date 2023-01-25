package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "customer")
@Data
public class Customer {

    @Id
    private long cu_id;

    @Column(name = "cu_email")
    private String cuEmail;

    private String password;
    private String cu_name;

    private String cu_telno;

    private String birthdate;

    private Character auth_yn;
}
