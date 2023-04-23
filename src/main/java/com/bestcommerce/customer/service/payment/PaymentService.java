package com.bestcommerce.customer.service.payment;

import com.bestcommerce.customer.domain.Payment;
import com.bestcommerce.customer.domain.PaymentLog;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.exception.QuantityInsufficientException;
import com.bestcommerce.customer.exception.QuantitySoldOutException;
import com.bestcommerce.customer.repository.bulk.PaymentBulkInsertRepository;
import com.bestcommerce.customer.repository.domain.PaymentLogRepository;
import com.bestcommerce.customer.repository.domain.PaymentRepository;
import com.bestcommerce.customer.util.TimeFormat;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    private final PaymentLogRepository paymentLogRepository;

    private final PaymentBulkInsertRepository paymentBulkInsertRepository;

    private final PaymentInsertDtoConvert paymentInsertDtoConvert;

    @Transactional
    public String save(List<PaymentDto> paymentDtoList){
        List<Payment> paymentList = new ArrayList<>();
        PaymentLog paymentLog = new PaymentLog(0L, "", null);
        try {
            paymentInsertDtoConvert.paymentConverterForInsert(paymentDtoList,paymentList,paymentLog);
        } catch(NullPointerException | IndexOutOfBoundsException e){
            log.error("orderDtoList is Null Or Empty .. {}", e.getMessage());
            paymentLog.setCustomer(null);
            log.info("set order Log customer_id null");
            return e.getMessage();
        } catch (QuantitySoldOutException e){
            log.error(e.getMessage());
            return e.getMessage();
        } catch (QuantityInsufficientException e){
            log.error(e.getMessage());
            return e.getMessage();
        }
        paymentLog.setPayDateForNowDate(LocalDateTime.now().format(TimeFormat.orderLogDateFormat));
        paymentLogRepository.save(paymentLog);
        paymentBulkInsertRepository.saveAll(paymentList, paymentLog);
        return "OK";
    }

    public List<Payment> getPaymentList(PaymentLog paymentLog){
        return paymentRepository.getPaymentsByPaymentLog(paymentLog);
    }

    public List<Payment> findAllPaymentList(){
        return paymentRepository.findAll();
    }
}
