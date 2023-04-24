package com.bestcommerce.customer.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> getPaymentsByPaymentLog(PaymentLog paymentLog);
}
