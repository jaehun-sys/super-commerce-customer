package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.member.dto.MemberLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MemberControllerTest {

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


    @DisplayName("로그인 하고 발급된 bearer token으로 다른 api 접속 가능한지 테스트")
    @Test
    void LoginAndAccessAuthenticatedAPITest() throws Exception {

        mockMvc.perform(get("/member/test"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
