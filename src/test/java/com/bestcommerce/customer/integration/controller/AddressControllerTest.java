package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.address.repository.AddressRepository;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.address.service.AddressService;
import com.bestcommerce.customer.util.TestUtilService;
import com.bestcommerce.member.service.MemberService;
import com.bestcommerce.util.converter.DtoConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TestUtilService testUtilService;


    @BeforeEach
    void initial() throws Exception {
        mockMvc = testUtilService.loginWithJwtToken(mockMvc,objectMapper);
    }


    @DisplayName("주소 저장 테스트")
    @Test
    void saveAddressTest() throws Exception {

        Long customerId = 40L;
        String addr = "나는 낭만 고양이";
        Character represent = 'N';
        String zipcode = "29427";

        AddressDto addressDto = new AddressDto(0L, customerId, addr, represent, zipcode);

        String content = objectMapper.writeValueAsString(addressDto);

        mockMvc.perform(post("/address/save").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<Address> addressList = addressService.getAllAddressesByCustomer(customerService.getOneCustomerInfo(customerId));

        for(Address address : addressList){
            if(address.getAddr().equals(addr)){
                assertThat(address.getCustomer().getCuId()).isEqualTo(customerId);
                assertThat(address.getRepYn()).isEqualTo(represent);
                assertThat(address.getZipCode()).isEqualTo(zipcode);
                addressService.deleteAddressByAddrId(address.getAddrId());
                break;
            }
        }

    }

    @DisplayName("고객 정보로 주소 가져오는 테스트")
    @Test
    public void getAllAddressByCustomer() throws Exception{

        String customerEmail = "dudtkd0219@gmail.com";

        CustomerDto customerDto = new CustomerDto(0L, "",customerEmail,"","","",'0',"","");

        String content = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(post("/address/get").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<AddressDto> addressDtoList = dtoConverter.toAddressDtoList(addressService.getAllAddressesByCustomer(customerService.getOneCustomerInfo(memberService.findMember(customerEmail))));

        assertThat(addressDtoList.size()).isEqualTo(2);

        for (AddressDto addressDto : addressDtoList) {
            assertThat(38L).isEqualTo(addressDto.getCustomerId());
        }
    }

    @DisplayName("주소 업데이트 테스트")
    @Test
    public void updateAddressTest() throws Exception{
        AddressDto addressDto = new AddressDto(34L, 38L, "서울턱별시 관악구 봉천동",'0', "3237");

        String content = objectMapper.writeValueAsString(addressDto);

        mockMvc.perform(post("/address/update").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Address address = addressRepository.findById(addressDto.getAddressId()).orElseThrow(() -> new NullPointerException("주소지가 없습니다."));

        assertThat(address.getAddr()).isEqualTo(addressDto.getAddr());
        assertThat(address.getZipCode()).isEqualTo(addressDto.getZipcode());
    }
}
