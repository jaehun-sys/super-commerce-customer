package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customer")
@Data
public class Customer {

    @Id
    @Column(name = "cu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cuId;

    @Column(name = "cu_email")
    private String cuEmail;

    @Column(name = "password")
    private String password;

    @Column(name = "cu_name")
    private String cuName;

    @Column(name = "cu_telno")
    private String cuTelNumber;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "auth_yn")
    private Character authYn;

    @OneToMany(mappedBy = "customer")
    private List<Address> addressList = new ArrayList<>();

    public Customer() {

    }

    public Customer(String cuEmail, String password, String cuName, String cuTelNumber, String birthdate, Character authYn){
        this.cuEmail = cuEmail;
        this.password = password;
        this.cuName = cuName;
        this.cuTelNumber = cuTelNumber;
        this.birthdate = birthdate;
        this.authYn = authYn;
    }
}
