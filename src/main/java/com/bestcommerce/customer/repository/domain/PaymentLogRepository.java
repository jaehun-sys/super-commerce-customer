package com.bestcommerce.customer.repository.domain;

import com.bestcommerce.customer.domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
}
