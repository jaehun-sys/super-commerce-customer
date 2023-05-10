package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.member.dto.MemberLoginDto;
import com.bestcommerce.payment.dto.PaymentDto;
import com.bestcommerce.payment.dto.PaymentLogDto;
import com.bestcommerce.util.DtoList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PaymentControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PaymentControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void initial() throws Exception {

        MemberLoginDto memberLoginDto = new MemberLoginDto("test01","1234");

        String content = objectMapper.writeValueAsString(memberLoginDto);

        String result = mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andReturn().getResponse().getContentAsString();

        String token = new JSONObject(result).getString("accessToken");

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .defaultRequest(post("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .build();
    }

    @Test
    @DisplayName("대량 주문 테스트")
    void saveBigOrderListTest() throws Exception {

        Long[] customerIdArray = {38L,39L,40L};
        List<PaymentDto> orderList = new ArrayList<>();

        for(long i = 1L; i <= 6L; i++){
            orderList.add(new PaymentDto(0L,0L,
                    customerIdArray[0],
                    1L,
                    i,
                    (int)(Math.random()*10 + 1),
                    0));
        }

        DtoList oneDtoList = new DtoList(orderList);

        String content = objectMapper.writeValueAsString(oneDtoList);

        mockMvc.perform(post("/pay/save").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("가장 최근 주문 내역 조회")
    void getRecentPayListTest() throws Exception {
        log.info("테스트 시작");

        PaymentLogDto paymentLogDto = new PaymentLogDto(36L,38L,0L, "");

        String content = objectMapper.writeValueAsString(paymentLogDto);

        mockMvc.perform(post("/pay/get/recent").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
