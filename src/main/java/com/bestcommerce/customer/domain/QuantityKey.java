package com.bestcommerce.customer.domain;

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
public class QuantityKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "size_id")
    private Long sizeId;

    public QuantityKey(Long productId, Long sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }
        else if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        QuantityKey other = (QuantityKey) obj;
        return Objects.equals(productId, other.productId) && Objects.equals(sizeId, other.sizeId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(productId, sizeId);
    }
}
