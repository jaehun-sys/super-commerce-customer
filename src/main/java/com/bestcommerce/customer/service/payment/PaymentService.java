package com.bestcommerce.customer.service.payment;

import com.bestcommerce.customer.domain.Payment;
import com.bestcommerce.customer.domain.PaymentLog;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.repository.bulk.PaymentBulkInsertRepository;
import com.bestcommerce.customer.repository.domain.PaymentLogRepository;
import com.bestcommerce.customer.repository.domain.PaymentRepository;
import com.bestcommerce.customer.util.PaymentInsertDtoConverter;
import com.bestcommerce.customer.util.TimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentLogRepository paymentLogRepository;

    private final PaymentBulkInsertRepository paymentBulkInsertRepository;

    private final PaymentInsertDtoConverter paymentInsertDtoConverter;

    public PaymentService(PaymentRepository paymentRepository, PaymentLogRepository paymentLogRepository, PaymentBulkInsertRepository paymentBulkInsertRepository, PaymentInsertDtoConverter paymentInsertDtoConverter) {
        this.paymentRepository = paymentRepository;
        this.paymentLogRepository = paymentLogRepository;
        this.paymentBulkInsertRepository = paymentBulkInsertRepository;
        this.paymentInsertDtoConverter = paymentInsertDtoConverter;
    }

    public void save(List<PaymentDto> paymentDtoList){
        List<Payment> paymentList = new ArrayList<>();
        PaymentLog paymentLog = new PaymentLog(0L, "", null);
        paymentInsertDtoConverter.paymentConverterForInsert(paymentDtoList,paymentList,paymentLog);
        paymentLog.setPayDateForNowDate(LocalDateTime.now().format(TimeFormat.orderLogDateFormat));
        paymentLogRepository.save(paymentLog);
        paymentBulkInsertRepository.saveAll(paymentList, paymentLog);
    }

    public List<Payment> getPaymentList(PaymentLog paymentLog){
        return paymentRepository.getPaymentsByPaymentLog(paymentLog);
    }
}
