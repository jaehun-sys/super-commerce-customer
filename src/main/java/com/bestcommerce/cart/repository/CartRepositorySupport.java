package com.bestcommerce.cart.repository;

import com.bestcommerce.cart.dto.CartItemDto;
import com.bestcommerce.cart.entity.CartKey;
import com.bestcommerce.cart.entity.QCartKey;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

import static com.bestcommerce.cart.entity.QCart.cart;
import static com.bestcommerce.customer.entity.QCustomer.customer;
import static com.bestcommerce.product.entity.QBrand.brand;
import static com.bestcommerce.product.entity.QProduct.product;
import static com.bestcommerce.size.entity.QQuantity.quantity;

@Repository
public class CartRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CartRepositorySupport(JPAQueryFactory queryFactory) {
        super(CartItemDto.class);
        this.queryFactory = queryFactory;
    }

    public List<CartItemDto> getCartItemDtoList(Long id) {

        return queryFactory.select(Projections.constructor(CartItemDto.class,
                        customer.cuId.as("customerId"),
                        customer.cuName.as("customerName"),
                        product.productId.as("productId"),
                        product.productName.as("productName"),
                        product.productCost.as("productCost"),
                        brand.id.as("brandId"),
                        brand.name.as("brandName"),
                        product.thumbPath.as("thumbnailPath"),
                        product.deliveryCost.as("deliveryCost"),
                        quantity.id.as("quantityId"),
                        quantity.name.as("quantityName")))
                .from(cart)
                .innerJoin(cart.customer, customer)
                .innerJoin(cart.quantity, quantity)
                .innerJoin(quantity.product, product)
                .innerJoin(product.brand, brand)
                .where(customer.cuId.eq(id)).fetch();
    }

    @Transactional
    public void deleteCartList(List<CartKey> cartKeyList){
        BooleanBuilder builder = new BooleanBuilder();
        QCartKey qCartKey = QCartKey.cartKey;
        for(CartKey cartKey : cartKeyList){
            builder.or(qCartKey.eq(cartKey));
        }
        queryFactory.delete(cart).where(builder).execute();
    }
}
