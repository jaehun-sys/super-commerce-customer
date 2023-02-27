package com.bestcommerce.customer.service.account;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;

    public AccountService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Boolean isUsableEmail(String cu_email){
        return customerRepository.findByCuEmail(cu_email) == null;
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public Customer getOneCustomerInfo(long id){
        return customerRepository.findById(id).orElseGet(Customer::new);
    }

    public Customer getOneCustomerInfo(String cu_email){
        return customerRepository.findByCuEmail(cu_email);
    }

    public void deleteOneCustomer(String cu_email){
        customerRepository.deleteCustomerByCuEmail(cu_email);
    }
}
