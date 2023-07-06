package com.bestcommerce.customer.entity;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.member.entity.Member;
import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.product.entity.BrandLike;
import com.bestcommerce.product.entity.ProductLike;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @Column(name = "cu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cuId;

    @Column(name = "cu_name")
    private String cuName;

    @Column(name = "cu_telno")
    private String cuTelNumber;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "regdate")
    private String registerDate;

    @Column(name = "modify_date")
    private String modifyDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "customer")
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Cart> cartsList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Payment> paymentList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<ProductLike> productLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<BrandLike> brandLikeList = new ArrayList<>();

    public Customer(Member member, String cuName, String cuTelNumber, String birthdate, String registerDate, String modifyDate){
        this.member = member;
        this.cuName = cuName;
        this.cuTelNumber = cuTelNumber;
        this.birthdate = birthdate;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
    }
}
