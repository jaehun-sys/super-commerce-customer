package com.bestcommerce.product.entity;

import com.bestcommerce.customer.entity.Customer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity(name = "brand_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cu_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "date")
    private String likeDate;

    public BrandLike(Long id, Customer customer, Brand brand, String likeDate) {
        this.id = id;
        this.customer = customer;
        this.brand = brand;
        this.likeDate = likeDate;
    }
}
