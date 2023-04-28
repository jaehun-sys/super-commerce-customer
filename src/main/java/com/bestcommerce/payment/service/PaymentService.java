package com.bestcommerce.payment.service;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.payment.dto.OrderItemDto;
import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.payment.entity.PaymentLog;
import com.bestcommerce.payment.repository.OrderItemRepository;
import com.bestcommerce.payment.repository.PaymentBulkInsertRepository;
import com.bestcommerce.payment.repository.PaymentLogRepository;
import com.bestcommerce.payment.repository.PaymentRepository;
import com.bestcommerce.util.TimeFormat;
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

    private final OrderItemRepository orderItemRepository;

    public String save(List<Payment> paymentList, Customer customer, Long[] totalPrice){
        PaymentLog paymentLog = new PaymentLog(0L, totalPrice[0], LocalDateTime.now().format(TimeFormat.orderLogDateFormat), customer);
        Long id = paymentLogRepository.save(paymentLog).getPayNo();
        paymentBulkInsertRepository.saveAll(paymentList, id);
        log.info("Payment Success");
        return "Payment Success";
    }

    public List<OrderItemDto> getOrderList(PaymentLog paymentLog){
        return orderItemRepository.getOrderItemList(paymentLog.getPayNo());
    }

    public List<Payment> findAllPaymentList(){
        return paymentRepository.findAll();
    }
}
