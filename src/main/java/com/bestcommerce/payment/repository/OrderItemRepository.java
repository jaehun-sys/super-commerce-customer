package com.bestcommerce.payment.repository;

import com.bestcommerce.payment.dto.OrderItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bestcommerce.payment.entity.QPayment.payment;
import static com.bestcommerce.payment.entity.QPaymentLog.paymentLog;
import static com.bestcommerce.size.entity.QSize.size;
import static com.bestcommerce.product.entity.QProduct.product;

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
                        payment.productPrice.as("productPrice"),
                        size.sizeId.as("sizeId"),
                        size.contentName.as("sizeName"),
                        size.sizeValue.as("sizeValue"),
                        product.productId.as("productId"),
                        product.productName.as("productName"),
                        product.sellerId.as("sellerId"),
                        product.thumbPath.as("thumbNail")
                ))
                .from(payment)
                .innerJoin(payment.paymentLog, paymentLog)
                .innerJoin(payment.size, size)
                .innerJoin(payment.product, product)
                .where(paymentLog.payNo.eq(orderNo)).fetch();
    }
}
