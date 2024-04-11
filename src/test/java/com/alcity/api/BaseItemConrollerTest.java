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
        ResponseEntity responseGendersById = restTemplate.getForEntity("http://127.0.0.1:8080/base/genders/id/1", String.class);
        ResponseEntity responseUserGenders = restTemplate.getForEntity("http://127.0.0.1:8080/base/genders/", String.class);
        ResponseEntity responsePuzzleStatus = restTemplate.getForEntity("http://127.0.0.1:8080/base/pl-status/", String.class);
        ResponseEntity responsePuzzleCategory = restTemplate.getForEntity("http://127.0.0.1:8080/base/pl-category/", String.class);
        ResponseEntity responsePuzzleDifficulty = restTemplate.getForEntity("http://127.0.0.1:8080/base/pl-difficulties/", String.class);
        ResponseEntity responseMemberTypes = restTemplate.getForEntity("http://127.0.0.1:8080/base/member-types/", String.class);
        ResponseEntity responseDataTypes = restTemplate.getForEntity("http://127.0.0.1:8080/base/data-types/", String.class);
        ResponseEntity responseClientTypes = restTemplate.getForEntity("http://127.0.0.1:8080/base/client-types/", String.class);
        ResponseEntity responseGameStatus = restTemplate.getForEntity("http://127.0.0.1:8080/base/game-status/", String.class);
        ResponseEntity responsePuzzlePrivacyStatus = restTemplate.getForEntity("http://127.0.0.1:8080/base/pl-privacy/", String.class);
        ResponseEntity responseWalletItemTypes = restTemplate.getForEntity("http://127.0.0.1:8080/base/wallet-types/", String.class);
        ResponseEntity responseBinaryContentTypes = restTemplate.getForEntity("http://127.0.0.1:8080/base/binary-types/", String.class);
        ResponseEntity responseContentById = restTemplate.getForEntity("http://127.0.0.1:8080/base/binary-content/id/60", String.class);
        ResponseEntity responseLearningSkills = restTemplate.getForEntity("http://127.0.0.1:8080/base/learning-skills", String.class);
        ResponseEntity responseLearningTopics = restTemplate.getForEntity("http://127.0.0.1:8080/base/learning-topics", String.class);


        ResponseEntity responseJourneis = restTemplate.getForEntity("http://127.0.0.1:8080/journey/", String.class);
        ResponseEntity responseJourneyById= restTemplate.getForEntity("http://127.0.0.1:8080/journey/63", String.class);


        ResponseEntity responseWalletItems = restTemplate.getForEntity("http://127.0.0.1:8080/user/wallet-items", String.class);
        ResponseEntity responseWalletItemById = restTemplate.getForEntity("http://127.0.0.1:8080/wallet-items/id/53", String.class);

        ResponseEntity responseApplicationMembers = restTemplate.getForEntity(" http://127.0.0.1:8080/user/members/", String.class);
        ResponseEntity responseApplicationMemberById = restTemplate.getForEntity(" http://127.0.0.1:8080/user/member/10", String.class);
        ResponseEntity responseApplicationMemberWalletDataById = restTemplate.getForEntity(" http://127.0.0.1:8080/user/member/55/wallet/", String.class);

        ResponseEntity responseAttributes = restTemplate.getForEntity("http://127.0.0.1:8080/object/attributes/", String.class);
        ResponseEntity responseAttributeById = restTemplate.getForEntity("http://127.0.0.1:8080/object/attribute/id/153", String.class);

        ResponseEntity responsePuzzleLevelById = restTemplate.getForEntity("http://127.0.0.1:8080/p-level/id/83", String.class);
        ResponseEntity responsePuzzleLevels = restTemplate.getForEntity("http://127.0.0.1:8080/p-level/all", String.class);


        ResponseEntity responsePuzzleGroupById = restTemplate.getForEntity("http://127.0.0.1:8080/p-group/id/{id}", String.class);
        ResponseEntity responsePuzzleGroups = restTemplate.getForEntity("http://127.0.0.1:8080/p-group/all", String.class);

        ResponseEntity responsePpuzzleGroupById = restTemplate.getForEntity("http://127.0.0.1:8080/p-group/id/{id}", String.class);
        ResponseEntity responsePuzzleCategories = restTemplate.getForEntity("http://127.0.0.1:8080/p-group/category/all", String.class);

        ResponseEntity responseObjectCategories = restTemplate.getForEntity("http://127.0.0.1:8080/object/categories/all", String.class);

        //ResponseEntity resp2 = restTemplate.getForEntity("http://localhost:8080/"+name2 , String.class);
        String expectedGenderJson = "{\"id\":\"1\",\"label\":\"F\",\"value\":\"Female\",\"version\":\"1\",\"created\":\"1711360835820\",\"updated\":\"1711360835820\"}";

        assertEquals(responseGendersById.getBody(), expectedGenderJson );
        //assertEquals(resp2.getBody(), "Hello, World "+ name );
    }

}
