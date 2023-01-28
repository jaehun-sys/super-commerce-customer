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

    public Customer() {

    }

    public Customer(long cu_id, String cuEmail, String password, String cu_name, String cu_telno, String birthdate, Character auth_yn){
        this.cu_id = cu_id;
        this.cuEmail = cuEmail;
        this.password = password;
        this.cu_name = cu_name;
        this.cu_telno = cu_telno;
        this.birthdate = birthdate;
        this.auth_yn = auth_yn;
    }


}
