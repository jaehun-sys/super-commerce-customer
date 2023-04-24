package com.bestcommerce.customer.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentBulkInsertRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Payment> paymentList, PaymentLog paymentLog){

        String sql = "INSERT INTO payment (pay_no, cu_id, product_id, size_id, product_cnt, product_price) " +
                     "VALUES (?, ?, ?, ?, ?, ?) ";

        jdbcTemplate.batchUpdate(sql,
                                paymentList,
                                paymentList.size(),
                                (PreparedStatement ps, Payment payment) -> {
                                    ps.setLong(1, paymentLog.getPayNo());
                                    ps.setLong(2, payment.getCustomer().getCuId());
                                    ps.setLong(3, payment.getProduct().getProductId());
                                    ps.setLong(4, payment.getSize().getSizeId());
                                    ps.setInt(5, payment.getProductCount());
                                    ps.setInt(6, payment.getProductPrice());
                                });

    }
}
