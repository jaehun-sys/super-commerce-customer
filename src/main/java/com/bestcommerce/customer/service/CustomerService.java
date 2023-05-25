package com.bestcommerce.customer.service;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.repository.CustomerRepository;
import com.bestcommerce.customer.repository.CustomerRepositorySupport;
import com.bestcommerce.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerRepositorySupport customerRepositorySupport;

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public Customer getOneCustomerInfo(long id){
        return customerRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Customer getOneCustomerInfo(Member member){
        return customerRepository.findByMember(member).orElseThrow(()-> new NullPointerException("고객이 없습니다."));
    }

    public void updateCustomer(CustomerDto customerDto){
        if(customerRepository.existsById(customerDto.getCustomerId())){
            customerRepositorySupport.updateCustomer(customerDto);
            return;
        }
        log.error("Not exists Customer Id");
        throw new RuntimeException("Not exists Customer Id !");
    }
}
