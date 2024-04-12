package com.alcity.api;

import com.alcity.entity.base.UserGender;
import com.alcity.service.base.UserGenderService;
import org.assertj.core.api.UrlAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    public String address="http://127.0.0.1:8080";
    public String port="8080";

    String exampleGenderJson = "{\"id\":\"1\",\"label\":\"F\",\"value\":\"Female\",\"version\":\"1\",\"created\":\"1711360835820\",\"updated\":\"1711360835820\"}";

    @org.junit.Test
    public void BaseItemsTestUnit() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        //Gender Object API
        ResponseEntity allGenders = restTemplate.getForEntity(address +"/base/gender/all" , String.class);
        ResponseEntity genderById = restTemplate.getForEntity(address +"/base/gender/id/1" , String.class);

        //Client Type Object API
        ResponseEntity allClientTypes = restTemplate.getForEntity(address +"/base/client-type/all" , String.class);
        ResponseEntity clientTypeById = restTemplate.getForEntity(address +"/base/client-type/id/42" , String.class);

        //Data Type Object API
        ResponseEntity allDataTypes = restTemplate.getForEntity(address +"/base/data-type/all" , String.class);
        ResponseEntity dataTypeById = restTemplate.getForEntity(address +"/base/data-type/id/7" , String.class);

        //Member Type Object API
        ResponseEntity memberDataTypes = restTemplate.getForEntity(address +"/base/member-type/all" , String.class);
        ResponseEntity memberTypeById = restTemplate.getForEntity(address +"/base/member-type/id/3" , String.class);


        //Puzzle Level difficulty  Object API
        ResponseEntity puzzleLevelDifficulties = restTemplate.getForEntity(address +"/base/pl-difficulty/all" , String.class);
        ResponseEntity puzzleLevelDifficultyById = restTemplate.getForEntity(address +"/base/pl-difficulty/id/48" , String.class);

        //Puzzle Level Category  Object API
        ResponseEntity puzzleLevelCategories = restTemplate.getForEntity(address +"/base/pl-category/all" , String.class);
        ResponseEntity puzzleLevelCategoryById = restTemplate.getForEntity(address +"/base/pl-category/id/30" , String.class);

        //Puzzle Level Status Object API
        ResponseEntity puzzleLevelStatuses = restTemplate.getForEntity(address +"/base/pl-status/all" , String.class);
        ResponseEntity puzzleLevelStatusById = restTemplate.getForEntity(address +"/base/pl-status/id/50" , String.class);

        //Game Status Object API
        ResponseEntity gameStatuses = restTemplate.getForEntity(address +"/base/game-status/all" , String.class);
        ResponseEntity gameStatusById = restTemplate.getForEntity(address +"/base/game-status/id/75" , String.class);

        //Puzzle Privacy Object API
        ResponseEntity puzzlePrivacies = restTemplate.getForEntity(address +"/base/game-privacy/all" , String.class);
        ResponseEntity puzzlePrivacyById = restTemplate.getForEntity(address +"/base/game-privacy/id/75" , String.class);

        //Wallet Item Type Object API
        ResponseEntity walletItemTypes = restTemplate.getForEntity(address +"/base/wallet-type/all" , String.class);
        ResponseEntity walletItemTypeById = restTemplate.getForEntity(address +"/base/wallet-type/id/17" , String.class);

        //Binary Content Type Object API
        ResponseEntity binaryContentTypes = restTemplate.getForEntity(address +"/base/binary-type/all" , String.class);
        ResponseEntity binaryContentTypeById = restTemplate.getForEntity(address +"/base/binary-type/id/12" , String.class);

        //Learning Skill  Object API
        ResponseEntity learningSkills = restTemplate.getForEntity(address +"/base/learning-skill/all" , String.class);
        ResponseEntity learningSkillById = restTemplate.getForEntity(address +"/base/learning-skill/id/56" , String.class);

        //Learning Topic  Object API
        ResponseEntity learningTopics = restTemplate.getForEntity(address +"/base/learning-topic/all" , String.class);
        ResponseEntity learningTopicById = restTemplate.getForEntity(address +"/base/learning-topic/id/60" , String.class);

        //Learning Content  Object API
        ResponseEntity learningContents = restTemplate.getForEntity(address +"/base/learning-content/all" , String.class);
        ResponseEntity learningContentById = restTemplate.getForEntity(address +"/base/learning-content/id/60" , String.class);


        //Binary Content  Object API
        ResponseEntity binaryContentById = restTemplate.getForEntity(address +"/base/binary-content/id/60" , String.class);

        //Puzzle Level Rule Event Type  Object API
        ResponseEntity puzzleLevelRuleEventTypes = restTemplate.getForEntity(address +"/base/pl-rule-event-type/all" , String.class);
        ResponseEntity puzzleLevelRuleEventTypeById = restTemplate.getForEntity(address +"/base/pl-rule-event-type/id/60" , String.class);

        //Journey Object API
        ResponseEntity Journeies = restTemplate.getForEntity(address +"/journey/all" , String.class);
        ResponseEntity JourneyById = restTemplate.getForEntity(address +"/journey/id/70" , String.class);


        ResponseEntity responsePuzzleStatus = restTemplate.getForEntity("http://127.0.0.1:8080" + "/base/pl-status/", String.class);
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

        assertEquals(genderById.getBody(), expectedGenderJson );
        //assertEquals(resp2.getBody(), "Hello, World "+ name );
    }

}
