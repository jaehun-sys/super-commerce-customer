package com.bestcommerce.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "address")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Address {

    @Id
    @Column(name = "addr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addrId;

    @Column(name = "addr")
    private String addr;

    @Column(name = "rep_yn")
    private Character repYn;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "cu_id")
    private Customer customer;

    public Address(String addr, Character repYn, String zipCode, Customer customer){
        this.addr = addr;
        this.repYn = repYn;
        this.zipCode = zipCode;
        this.customer = customer;
    }

}
