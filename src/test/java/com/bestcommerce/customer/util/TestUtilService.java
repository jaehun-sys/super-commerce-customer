package com.bestcommerce.customer.util;

import com.bestcommerce.member.dto.MemberLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class TestUtilService {
    @Value("${testUtil.login.id}")
    private String testId;

    @Value("${testUtil.login.pw}")
    private String testPwd;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public MockMvc loginWithJwtToken(MockMvc mockMvc, ObjectMapper objectMapper) throws Exception{

        String result = mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new MemberLoginDto(testId,testPwd))))
                .andReturn().getResponse().getContentAsString();

        String token = new JSONObject(result).getString("accessToken");

        return MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .defaultRequest(post("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .build();
    }
}
