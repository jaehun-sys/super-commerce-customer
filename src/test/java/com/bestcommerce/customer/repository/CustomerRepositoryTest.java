package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.util.converter.EntityConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private EntityConverter entityConverter;

    @BeforeEach
    public void setup(){
        entityConverter = new EntityConverter();
    }

    @Test
    @DisplayName("회원 가입 repository Test")
    void findByCuEmailTest(){

    }

    @Test
    @DisplayName("이메일 중복 테스트")
    void existsByCuEmailTest(){


    }
}
