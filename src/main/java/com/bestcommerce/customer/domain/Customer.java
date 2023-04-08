package com.bestcommerce.customer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customer")
@Getter
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

    @Column(name = "regdate")
    private String registerDate;

    @Column(name = "modify_date")
    private String modifyDate;

    @OneToMany(mappedBy = "customer")
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Cart> cartsList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Order> orderList = new ArrayList<>();

    public Customer() {

    }

    public Customer(String cuEmail, String password, String cuName, String cuTelNumber, String birthdate, Character authYn, String registerDate, String modifyDate){
        this.cuEmail = cuEmail;
        this.password = password;
        this.cuName = cuName;
        this.cuTelNumber = cuTelNumber;
        this.birthdate = birthdate;
        this.authYn = authYn;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
    }
}
