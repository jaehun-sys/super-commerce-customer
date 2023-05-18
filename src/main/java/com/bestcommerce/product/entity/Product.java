package com.bestcommerce.product.entity;

import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.size.entity.Size;
import com.bestcommerce.payment.entity.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_nm")
    private String productName;

    @Column(name = "product_price")
    private int productCost;

    @Column(name = "info")
    private String info;

    @Column(name = "thumb_path")
    private String thumbPath;

    @Column(name = "delivery_cost")
    private int deliveryCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartsList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Size> sizeList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Payment> paymentList = new ArrayList<>();

}
