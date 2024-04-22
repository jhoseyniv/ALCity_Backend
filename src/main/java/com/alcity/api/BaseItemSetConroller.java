package com.alcity.api;

import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.base.*;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/base")
public class BaseItemSetConroller {

    @GetMapping("/gender/all")
    public UserGender[] getGenders(Model model) {
        return UserGender.values();
    }

    @RequestMapping(value = "/gender/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserGender getGenderById(@PathVariable Long id) {
       return UserGender.getById(id);
    }

    @Autowired
    private ClientTypeService clientTypeService;
    @GetMapping("/client-type/all")
    public Collection<ClientTypeDTO> getClientTypes(Model model) {
        Collection<ClientTypeDTO> clientTypeDTOCollection = new ArrayList<>();
        Collection<ClientType> clientTypes = clientTypeService.findAll();

        return clientTypeDTOCollection;
    }

    @RequestMapping(value = "/client-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ClientTypeDTO getClientTypeById(@PathVariable Long id) {
        ClientTypeDTO clientTypeDTO = new ClientTypeDTO();
        Optional<ClientType> clientTypeOptional = clientTypeService.findById(id);
         if(clientTypeOptional.isPresent()){
             ClientType clientType = clientTypeOptional.get();
             clientTypeDTO.setId(clientType.getId());
             clientTypeDTO.setLabel(clientType.getLabel());
             clientTypeDTO.setValue(clientType.getValue());
             clientTypeDTO.setVersion(clientType.getVersion());
             clientTypeDTO.setCreated(DateUtils.getDatatimeFromLong(clientType.getCreated()));
             clientTypeDTO.setUpdated(DateUtils.getDatatimeFromLong(clientType.getUpdated()));

         }else clientTypeDTO=null;

        return clientTypeDTO;
    }

    @Autowired
    private DataTypeService dataTypeService;
    @GetMapping("/data-type/all")
    public Collection<DataType> getDataTypes(Model model) {
        Collection<DataType> dataTypes = dataTypeService.findAll();
        return dataTypes;
    }
    @RequestMapping(value = "/data-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<DataType> getDataTypeById(@PathVariable Long id) {
        Optional<DataType> dataType = dataTypeService.findById(id);
        return dataType;
    }

    @PostMapping("/data-type/save")
    public Optional<DataType> saveDataType(@RequestBody DataType dataType)  {
        DataType savedDataType = null;
        try {
            savedDataType = dataTypeService.save(dataType);
        }catch (Exception e )
        {
            throw new UniqueConstraintException();
        }
        Optional<DataType> output = dataTypeService.findById(savedDataType.getId());
        return output;
    }



    @Autowired
    private MemberTypeService memberTypeService;
    @GetMapping("/member-types")
    public Collection<MemberType> getMemberType(Model model) {
        Collection<MemberType> memberTypes = memberTypeService.findAll();
        return memberTypes;
    }
    @RequestMapping(value = "/member-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<MemberType> getMemberTypeById(@PathVariable Long id) {
        Optional<MemberType> memberType = memberTypeService.findById(id);
        return memberType;
    }



    @GetMapping("/pl-difficulty/all")
    public PLDifficulty[] getPuzzleLevelDiffculty(Model model) {

        return PLDifficulty.values();
    }

//    @RequestMapping(value = "/pl-difficulty/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Optional<PLDifficulty> getPuzzleLevelDifficultyById(@PathVariable Long id) {
//        Optional<PLDifficulty> puzzleLevelDifficulty = puzzleDifficultyService.findById(id);
//        return puzzleLevelDifficulty;
//    }



    @Autowired
    private PuzzleCategoryService puzzleCategoryService;
    @GetMapping("/pl-category/all")
    public Collection<PuzzleCategory> getPuzzleCategories(Model model) {
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        return puzzleCategoryCollection;
    }
    @RequestMapping(value = "/pl-category/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<PuzzleCategory> getPuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> puzzleCategory = puzzleCategoryService.findById(id);
        return puzzleCategory;
    }



    @GetMapping("/pl-status/all")
    public PLStatus[] getPuzzleLevelStatus(Model model) {
        return PLStatus.values();
    }
//    @RequestMapping(value = "/pl-status/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Optional<PLStatus> getPuzzleLevelStatusById(@PathVariable Long id) {
//        Optional<PLStatus> puzzleLevelStatus = puzzleLevelStatusService.findById(id);
//        return puzzleLevelStatus;
//    }

    @GetMapping("/pl-rule-event-type/all")
    public PLRuleEventType[] getPuzzleLevelRuleEventTypes(Model model) {
        return PLRuleEventType.values();
    }
//    @RequestMapping(value = "/pl-rule-event-type/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Optional<PLRuleEventType> getPuzzleLevelRuleEventTypesById(@PathVariable Long id) {
//        Optional<PLRuleEventType> puzzleLevelRuleEventType = puzzleLevelRuleEventTypeService.findById(id);
//        return puzzleLevelRuleEventType;
//    }


    @GetMapping("/game-status/all")
    public GameStatus[] getGameStatus(Model model) {
        return GameStatus.values();
    }

//    @RequestMapping(value = "/game-status/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Optional<GameStatus> getGameStatusById(@PathVariable Long id) {
//        Optional<GameStatus> gameStatus = gameStatusService.findById(id);
//        return gameStatus;
//    }


    @Autowired
    private PuzzleLevelPrivacyService puzzleLevelPrivacyService;

    @GetMapping("/pl-privacy/all")
    public Collection<PLPrivacy> getPuzzleLevelPrivacy(Model model) {
        Collection<PLPrivacy> puzzleLevelPrivacyCollection = puzzleLevelPrivacyService.findAll();
        return puzzleLevelPrivacyCollection;
    }

    @RequestMapping(value = "/pl-privacy/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<PLPrivacy> getPuzzleLevelPrivacyById(@PathVariable Long id) {
        Optional<PLPrivacy> puzzleLevelPrivacy = puzzleLevelPrivacyService.findById(id);
        return puzzleLevelPrivacy;
    }



    @Autowired
    private WalletItemTypeService walletItemTypeService;
    @GetMapping("/wallet-type/all")
    public Collection<WalletItemType> getWalletItemTypes(Model model) {
        Collection<WalletItemType> walletItemTypeCollection = walletItemTypeService.findAll();
        return walletItemTypeCollection;
    }
    @RequestMapping(value = "/wallet-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<WalletItemType> getWalletItemTypeById(@PathVariable Long id) {
        Optional<WalletItemType> walletItemType = walletItemTypeService.findById(id);
        return walletItemType;
    }


    @Autowired
    private BinaryContentTypeService binaryContentTypeService;

    @GetMapping("/binary-type/all")
    public Collection<BinaryContentType> getBinaryTypes(Model model) {
        Collection<BinaryContentType> binaryContentTypeCollection = binaryContentTypeService.findAll();
        return binaryContentTypeCollection;
    }
    @RequestMapping(value = "/binary-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<BinaryContentType> getBinaryTypeById(@PathVariable Long id) {
        Optional<BinaryContentType> binaryContentType = binaryContentTypeService.findById(id);
        return binaryContentType;
    }


    @Autowired
    private LearningSkillService learningSkillService;
    @PostMapping("/learning-skill/save")
    public Optional<LearningSkill> saveLearningSkills(@RequestBody LearningSkill learningSkill) {
        LearningSkill savedSkill  = learningSkillService.save(learningSkill);
        Optional<LearningSkill> output = learningSkillService.findById(savedSkill.getId());
        return output;
    }
    @GetMapping("/learning-skill/all")
    public Collection<LearningSkill> getLearningSkills(Model model) {
        Collection<LearningSkill> learningSkillCollection = learningSkillService.findAll();
        return learningSkillCollection;
    }

    @RequestMapping(value = "/learning-skill/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<LearningSkill> getLearningSkillById(@PathVariable Long id) {
        Optional<LearningSkill> learningSkill = learningSkillService.findById(id);
        return learningSkill;
    }


    @Autowired
    private LearningTopicService learningTopicService;

    @GetMapping("/learning-topic/all")
    public Collection<LearningTopic> getLearningTopics(Model model) {
        Collection<LearningTopic> learningTopicCollection = learningTopicService.findAll();
        return learningTopicCollection;
    }
    @RequestMapping(value = "/learning-topic/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<LearningTopic> getLearningTopicById(@PathVariable Long id) {
        Optional<LearningTopic> learningTopic = learningTopicService.findById(id);
        return learningTopic;
    }


    @Autowired
    private LearningContentService learningContentService;
    @GetMapping("/learning-content/all")
    public Collection<LearningContent> getLearningContents(Model model) {
        Collection<LearningContent> learningTopicCollection = learningContentService.findAll();
        return learningTopicCollection;
    }

    @RequestMapping(value="/learning-content/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<LearningContent> getLearningContentById(@PathVariable Long id) {
        Optional<LearningContent> learningContentOptional = learningContentService.findById(id);
        return learningContentOptional;
    }

    @Autowired
    private BinaryContentService binaryContentService;

    @RequestMapping(value="/binary-content/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<BinaryContent> getBinaryContentById(@PathVariable Long id) {
        Optional<BinaryContent> binaryContentCollection = binaryContentService.findById(id);
        return binaryContentCollection;
    }



}