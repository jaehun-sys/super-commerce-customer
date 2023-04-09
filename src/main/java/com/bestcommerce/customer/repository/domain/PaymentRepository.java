package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.Payment;
import com.bestcommerce.customer.domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> getPaymentsByPaymentLog(PaymentLog paymentLog);
}
