package com.bestcommerce.payment.repository;

import com.bestcommerce.payment.dto.OrderItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bestcommerce.payment.entity.QPayment.payment;
import static com.bestcommerce.payment.entity.QPaymentLog.paymentLog;
import static com.bestcommerce.size.entity.QQuantity.quantity;
import static com.bestcommerce.product.entity.QProduct.product;
import static com.bestcommerce.product.entity.QBrand.brand;

@Repository
public class OrderItemRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public OrderItemRepository(JPAQueryFactory queryFactory) {
        super(OrderItemDto.class);
        this.queryFactory = queryFactory;
    }

    public List<OrderItemDto> getOrderItemList(Long orderNo){
        return queryFactory.select(Projections.constructor(OrderItemDto.class,
                        paymentLog.payNo.as("orderNo"),
                        paymentLog.totalPrice.as("totalPrice"),
                        paymentLog.payDate.as("orderDate"),
                        payment.productCount.as("productCount"),
                        payment.paymentPrice.as("productPrice"),
                        quantity.id.as("quantityId"),
                        quantity.name.as("quantityName"),
                        brand.id.as("brandId"),
                        brand.name.as("brandName"),
                        product.productId.as("productId"),
                        product.productName.as("productName"),
                        product.thumbPath.as("thumbNail")
                ))
                .from(payment)
                .innerJoin(payment.paymentLog, paymentLog)
                .innerJoin(payment.quantity, quantity)
                .innerJoin(quantity.product, product)
                .innerJoin(product.brand, brand)
                .where(paymentLog.payNo.eq(orderNo)).fetch();
    }
}
