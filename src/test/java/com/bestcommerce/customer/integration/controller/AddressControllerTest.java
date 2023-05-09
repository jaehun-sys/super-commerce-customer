package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.address.repository.AddressRepository;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.address.service.AddressService;
import com.bestcommerce.member.dto.MemberLoginDto;
import com.bestcommerce.util.converter.DtoConverter;
import org.json.JSONArray;
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

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    private AddressRepository addressRepository;

    private String getToken(){
        String testUrl = "http://localhost:"+port+"/member/login";

        MemberLoginDto memberLoginDto = new MemberLoginDto("test01","1234");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(testUrl, memberLoginDto, HashMap.class);

        return Objects.requireNonNull(response.getBody()).get("accessToken").toString();
    }


    @DisplayName("주소 저장 테스트")
    @Test
    public void saveAddressTest() throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerId","38");
        jsonObject.put("addr","대구광역시 서초구 남천동 네고컷아파트 403호");
        jsonObject.put("represent","Y");
        jsonObject.put("zipcode","23427");


        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String testUrl = "http://localhost:"+port+"/address/save";

        ResponseEntity<Object> response = restTemplate.exchange(testUrl, HttpMethod.POST, request, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Long customerId = 38L;
        String addr = "대구광역시 서초구 남천동 네고컷아파트 403호";
        Character represent = 'Y';
        String zipcode = "23427";

        List<Address> addressList = addressService.getAllAddressesByCustomer(customerService.getOneCustomerInfo(customerId));

        for(Address address : addressList){
            if(address.getAddr().equals(addr)){
                assertThat(address.getCustomer().getCuId()).isEqualTo(customerId);
                assertThat(address.getRepYn()).isEqualTo(represent);
                assertThat(address.getZipCode()).isEqualTo(zipcode);
//                addressService.deleteAddressByAddrId(address.getAddrId());
                break;
            }
        }
    }

    @DisplayName("고객 정보로 주소 가져오는 테스트")
    @Test
    public void getAllAddressByCustomer() throws Exception{

        String customerEmail = "dudtkd0219@gmail.com";

        CustomerDto customerDto = new CustomerDto(1L, customerEmail,"","","","",'0',"","");

        String testUrl = "http://localhost:"+port+"/address/get";

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, customerDto, String.class);
        List<AddressDto> addressDtoList = dtoConverter.toAddressDtoList(addressService.getAllAddressesByCustomer(customerService.getOneCustomerInfo(customerEmail)));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONArray jsonArray = new JSONArray(response.getBody());
        assertThat(jsonArray.length()).isEqualTo(addressDtoList.size());

        for(int i = 0; i < jsonArray.length(); i++){
            assertThat(jsonArray.getJSONObject(i).getLong("customerId")).isEqualTo(addressDtoList.get(i).getCustomerId());
            assertThat(jsonArray.getJSONObject(i).getString("addr")).isEqualTo(addressDtoList.get(i).getAddr());
            assertThat(jsonArray.getJSONObject(i).getString("represent")).isEqualTo(String.valueOf(addressDtoList.get(i).getRepresent()));
            assertThat(jsonArray.getJSONObject(i).getString("zipcode")).isEqualTo(addressDtoList.get(i).getZipcode());
        }
    }

    @DisplayName("주소 업데이트 테스트")
    @Test
    public void updateAddressTest() throws Exception{
        AddressDto addressDto = new AddressDto(2L, 1L, "서울턱별시 관악구 봉천동",'0', "3237");

        String testUrl = "http://localhost:"+port+"/address/update";

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, addressDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Address address = addressRepository.findById(addressDto.getAddressId()).orElseThrow(NullPointerException::new);

        assertThat(address.getAddr()).isEqualTo(addressDto.getAddr());
        assertThat(address.getZipCode()).isEqualTo(addressDto.getZipcode());

    }
}
