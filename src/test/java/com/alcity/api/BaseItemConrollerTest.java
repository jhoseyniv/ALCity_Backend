package com.alcity.api;

import com.alcity.entity.base.UserGender;
import com.alcity.service.base.UserGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest

public class BaseItemConrollerTest {

    @Autowired
    private BaseItemSetConroller userGenderConroller;

    @Autowired
    Optional<UserGender> female;
    @MockBean
    private UserGenderService userGenderService;

    String exampleGenderJson = "{\"id\":\"1\",\"label\":\"F\",\"value\":\"Female\",\"version\":\"1\",\"created\":\"1711360835820\",\"updated\":\"1711360835820\"}";

//    @Test
//    public void retrieveDetailsForCourse() throws Exception {
//
//        Mockito.when(userGenderService.findById(1L)).thenReturn(female);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/genders/ids/1").accept(
//                MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        String expected = "{\"id\":\"1\",\"label\":\"F\",\"value\":\"Female\",\"version\":\"1\",\"created\":\"1711360835820\",\"updated\":\"1711360835820\"}";
//
//
//        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
//
//        JSONAssert.assertEquals(expected, result.getResponse()
//                .getContentAsString(), false);
//    }

    @org.junit.Test
    public void BaseItemsTestUnit() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseGenders = restTemplate.getForEntity("http://127.0.0.1:8080/base/genders/id/1", String.class);
        //ResponseEntity resp2 = restTemplate.getForEntity("http://localhost:8080/"+name2 , String.class);
        String expectedGenderJson = "{\"id\":\"1\",\"label\":\"F\",\"value\":\"Female\",\"version\":\"1\",\"created\":\"1711360835820\",\"updated\":\"1711360835820\"}";

        assertEquals(responseGenders.getBody(), expectedGenderJson );
        //assertEquals(resp2.getBody(), "Hello, World "+ name );
    }

}
