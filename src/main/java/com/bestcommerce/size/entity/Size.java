package com.bestcommerce.size.entity;

import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.util.exception.QuantityException;
import com.bestcommerce.util.exception.QuantityInsufficientException;
import com.bestcommerce.util.exception.QuantitySoldOutException;
import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.product.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "size")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;

    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "measure_nm")
    private String measureName;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "content_nm")
    private String contentName;

    @Column(name = "size_value")
    private int sizeValue;

    @Column(name = "remain")
    private int sizeRemainQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "size")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "size")
    private List<Payment> paymentList = new ArrayList<>();

    public void orderSize(int sizeRemainQuantity) throws QuantityException {
        if(this.sizeRemainQuantity == 0){
            throw new QuantitySoldOutException();
        }
        else if(this.sizeRemainQuantity - sizeRemainQuantity < 0){
            throw new QuantityInsufficientException();
        }
        this.sizeRemainQuantity -= sizeRemainQuantity;
    }

    public Size(Long sizeId, Long measureId, String measureName, Long contentId, String contentName, int sizeValue, Product product, int sizeRemainQuantity) {
        this.sizeId = sizeId;
        this.measureId = measureId;
        this.measureName = measureName;
        this.contentId = contentId;
        this.contentName = contentName;
        this.sizeValue = sizeValue;
        this.product = product;
        this.sizeRemainQuantity = sizeRemainQuantity;
    }

}
