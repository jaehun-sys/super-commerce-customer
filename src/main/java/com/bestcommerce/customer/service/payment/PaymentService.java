package com.bestcommerce.customer.service.payment;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.domain.Payment;
import com.bestcommerce.customer.domain.PaymentLog;
import com.bestcommerce.customer.repository.bulk.PaymentBulkInsertRepository;
import com.bestcommerce.customer.repository.domain.PaymentLogRepository;
import com.bestcommerce.customer.repository.domain.PaymentRepository;
import com.bestcommerce.customer.util.TimeFormat;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    private final PaymentLogRepository paymentLogRepository;

    private final PaymentBulkInsertRepository paymentBulkInsertRepository;

    public String save(List<Payment> paymentList, Customer customer, Long[] totalPrice){
        PaymentLog paymentLog = new PaymentLog(totalPrice[0], LocalDateTime.now().format(TimeFormat.orderLogDateFormat), customer);
        paymentLogRepository.save(paymentLog);
        paymentBulkInsertRepository.saveAll(paymentList, paymentLog);
        log.info("Payment Success");
        return "Payment Success";
    }

    public List<Payment> getPaymentList(PaymentLog paymentLog){
        return paymentRepository.getPaymentsByPaymentLog(paymentLog);
    }

    public List<Payment> findAllPaymentList(){
        return paymentRepository.findAll();
    }
}
