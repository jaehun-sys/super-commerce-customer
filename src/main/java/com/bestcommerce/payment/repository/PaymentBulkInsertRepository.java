package com.bestcommerce.payment.repository;

import com.bestcommerce.payment.entity.Payment;
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
    public void saveAll(List<Payment> paymentList, Long id){

        String sql = "INSERT INTO payment (pay_no, cu_id, quantity_id, product_cnt, payment_price) " +
                     "VALUES (?, ?, ?, ?, ?) ";

        jdbcTemplate.batchUpdate(sql,
                                paymentList,
                                paymentList.size(),
                                (PreparedStatement ps, Payment payment) -> {
                                    ps.setLong(1, id);
                                    ps.setLong(2, payment.getCustomer().getCuId());
                                    ps.setLong(3, payment.getQuantity().getId());
                                    ps.setLong(4, payment.getProductCount());
                                    ps.setInt(5, payment.getPaymentPrice());
                                });

    }
}
