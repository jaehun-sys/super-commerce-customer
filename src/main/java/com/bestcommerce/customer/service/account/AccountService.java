package com.bestcommerce.customer.service.account;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;

    public AccountService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Boolean isUsableEmail(String cu_email){
        Customer customer = customerRepository.findByCuEmail(cu_email);
        return customer != null;
    }

    public void save(CustomerDto customerDto){
        Customer customer = new Customer(customerDto.getCustomerEmail()
                                        ,customerDto.getCustomerPassword()
                                        ,customerDto.getCustomerName()
                                        ,customerDto.getCustomerTelNumber()
                                        ,customerDto.getCustomerBirthDate()
                                        ,customerDto.getAuthYn());
        customerRepository.save(customer);
    }

    public Customer getOneCustomerInfo(long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseGet(Customer::new);
    }

    public Customer getOneCustomerInfo(String cu_email){
        return customerRepository.findByCuEmail(cu_email);
    }
}
