package com.bestcommerce.payment.repository;

import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.payment.entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> getPaymentsByPaymentLog(PaymentLog paymentLog);
}
