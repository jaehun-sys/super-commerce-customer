package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.member.dto.MemberLoginDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
public class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getToken(){
        String testUrl = "http://localhost:"+port+"/member/login";

        MemberLoginDto memberLoginDto = new MemberLoginDto("test01","1234");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(testUrl, memberLoginDto, HashMap.class);

        return Objects.requireNonNull(response.getBody()).get("accessToken").toString();
    }

    @DisplayName("login 테스트")
    @Test
    void loginTest(){
        String testUrl = "http://localhost:"+port+"/member/login";

        MemberLoginDto memberLoginDto = new MemberLoginDto("test01","1234");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(testUrl, memberLoginDto, HashMap.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(StringUtils.hasText(response.getBody().get("accessToken").toString())).isTrue();
        assertThat(StringUtils.hasText(response.getBody().get("refreshToken").toString())).isTrue();
    }

    @DisplayName("로그인 하고 발급된 bearer token으로 다른 api 접속 가능한지 테스트")
    @Test
    void LoginAndAccessAuthenticatedAPITest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken());
        String testUrl = "http://localhost:"+port+"/member/test";

        ResponseEntity<String> response = restTemplate.exchange(testUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("success");
    }
}
