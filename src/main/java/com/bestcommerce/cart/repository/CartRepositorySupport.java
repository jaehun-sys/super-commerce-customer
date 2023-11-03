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
                        customer.cuName.as("customerName")))
                .from(cart)
                .innerJoin(cart.customer, customer)
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
