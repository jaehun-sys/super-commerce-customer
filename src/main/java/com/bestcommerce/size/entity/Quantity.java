package com.bestcommerce.size.entity;

import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.util.exception.QuantityException;
import com.bestcommerce.util.exception.QuantityInsufficientException;
import com.bestcommerce.util.exception.QuantitySoldOutException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "quantity")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quantity {

    @Id
    @Column(name = "quantity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "remain")
    private int remain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "quantity")
    private List<Payment> paymentList = new ArrayList<>();

    @OneToMany(mappedBy = "quantity")
    private List<Cart> cartList = new ArrayList<>();

    public void orderSize(int orderCount) throws QuantityException {
        if(this.remain == 0){
            throw new QuantitySoldOutException();
        }
        else if(this.remain - orderCount < 0){
            throw new QuantityInsufficientException();
        }
        this.remain -= orderCount;
    }
}
