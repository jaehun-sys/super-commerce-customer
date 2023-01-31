package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "address")
@Data
public class Address {

    @Id
    @Column(name = "addr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addrId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "addr")
    private String addr;

    @Column(name = "rep_yn")
    private Character repYn;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_address_cu_id_foreign_key")
    private Customer customer;

    public Address() {

    }

    public Address(Long customerId, String addr, Character repYn, String zipCode, Customer customer){
        this.customerId = customerId;
        this.addr = addr;
        this.repYn = repYn;
        this.zipCode = zipCode;
        this.customer = customer;
    }

}
