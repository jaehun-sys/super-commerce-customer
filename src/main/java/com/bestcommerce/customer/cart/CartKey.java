package com.bestcommerce.customer.cart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartKey implements Serializable {

    @Column(name = "cu_id")
    private Long customerId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "size_id")
    private Long sizeId;

    public CartKey(Long customerId, Long productId, Long sizeId) {
        this.customerId = customerId;
        this.productId = productId;
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        else if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        CartKey other = (CartKey) obj;
        return Objects.equals(customerId, other.customerId) && Objects.equals(productId, other.productId) && Objects.equals(sizeId, other.sizeId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(customerId,productId,sizeId);
    }
}
