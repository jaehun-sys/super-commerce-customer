package com.bestcommerce.customer.controller;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.repository.domain.AddressRepository;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.address.AddressService;
import com.bestcommerce.customer.util.DtoConverter;
import org.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    private AddressRepository addressRepository;


    @DisplayName("주소 저장 테스트")
    @Test
    public void saveAddressTest() throws Exception{

        Long customerId = 1L;
        String addr = "대구광역시 서초구 남천동 네컷아파트 403호";
        Character represent = 'Y';
        String zipcode = "23897";

        AddressDto addressDto = new AddressDto(1L,customerId, addr, represent, zipcode);

        String testUrl = "http://localhost:"+port+"/address/save";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, addressDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Address> addressList = addressService.getAllAddressesByCustomer(accountService.getOneCustomerInfo(customerId));

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

        CustomerDto customerDto = new CustomerDto(customerEmail,"","","","",'0',"","");

        String testUrl = "http://localhost:"+port+"/address/get";

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, customerDto, String.class);
        List<AddressDto> addressDtoList = dtoConverter.toAddressDtoList(addressService.getAllAddressesByCustomer(accountService.getOneCustomerInfo(customerEmail)));

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

        Address address = addressRepository.findById(addressDto.getAddressId()).orElseGet(Address::new);

        assertThat(address.getAddr()).isEqualTo(addressDto.getAddr());
        assertThat(address.getZipCode()).isEqualTo(addressDto.getZipcode());

    }
}
