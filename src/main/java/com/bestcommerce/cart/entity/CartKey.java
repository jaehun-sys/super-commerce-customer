package com.bestcommerce.cart.entity;

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

    @Column(name = "quantity_id")
    private Long quantityId;

    public CartKey(Long customerId, Long quantityId) {
        this.customerId = customerId;
        this.quantityId = quantityId;
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
        return Objects.equals(customerId, other.customerId) && Objects.equals(quantityId, other.quantityId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(customerId,quantityId);
    }
}
