package com.alcity.api;

import com.alcity.customexception.RecordNotFoundException;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.user.MemberTypeDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.base.*;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Basic Data Type", description = "Aall APIS for basic data types... ")
@RestController
@RequestMapping("/base")
public class BaseItemSetConroller {

    @Operation( summary = "Fetch all Genders ",  description = "fetches all Gender entities and their data from data source")
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
    @Operation( summary = "Fetch all client Types ",  description = "fetches all Client Types entities and their data from data source")
    @GetMapping("/client-type/all")
    public Collection<ClientType> getClientTypes(Model model) {
        Collection<ClientTypeDTO> clientTypeDTOCollection = new ArrayList<>();
        Collection<ClientType> clientTypes = clientTypeService.findAll();
        return clientTypes;
    }
    @Operation( summary = "Fetch a client Type by id ",  description = "fetch a  Client Types entity and their data from data source")
    @RequestMapping(value = "/client-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ClientTypeDTO getClientTypeById(@PathVariable Long id) {
        Optional<ClientType> clientTypeOptional = clientTypeService.findById(id);
         if(clientTypeOptional.isPresent())
             return DTOUtil.getClientTypeDTO(clientTypeOptional.get());

        return null;
    }
    @Operation( summary = "Save a Client Type ",  description = "save a  Client Types entity and their data to data base")
    @PostMapping("/client-type/save")
    public ResponseEntity<ClientType> saveClientType(@RequestBody ClientType clientType)  {
        ClientType savedRecord = null;
        try {
            savedRecord = clientTypeService.save(clientType);
        }catch (RuntimeException e )
        {
            throw new UniqueConstraintException(clientType.getLabel(), clientType.getId(), ClientType.class.toString());
        }
        Optional<ClientType> output = clientTypeService.findById(savedRecord.getId());
        return ResponseEntity.ok(clientTypeService.save(clientType));
    }

    @Operation( summary = "Fetch all data Types ",  description = "fetches all data Types entities and their data from data base")
    @GetMapping("/data-type/all")
    public DataType[] getDataTypes(Model model) { return DataType.values();   }

    @RequestMapping(value = "/data-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DataType getDataTypeById(@PathVariable Long id) { return DataType.getById(id); }

    @Autowired
    private MemberTypeService memberTypeService;
    @GetMapping("/member-type/all")
    public Collection<MemberTypeDTO> getMemberType(Model model) {
        Collection<MemberType> memberTypes = memberTypeService.findAll();
        Collection<MemberTypeDTO> memberTypeDTOCollection = DTOUtil.getMemberTypeDTOS(memberTypes);
        return memberTypeDTOCollection;
    }
    @RequestMapping(value = "/member-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MemberTypeDTO getMemberTypeById(@PathVariable Long id) {
        Optional<MemberType> memberTypeOptional = memberTypeService.findById(id);
        if(memberTypeOptional.isPresent())
            return DTOUtil.getMemberTypeDTO(memberTypeOptional.get());
        return null;
    }

    @Operation( summary = "Save a Member Type ",  description = "Save a  Member Types entity and their data to data base")
    @PostMapping("/member-type/save")
    public MemberType saveMemberType(@RequestBody MemberTypeDTO memberTypeDTO)  {
        MemberType savedRecord = null;
        try {
            savedRecord = memberTypeService.save(memberTypeDTO);
        }catch (RuntimeException e )
        {
            throw new UniqueConstraintException(memberTypeDTO.getLabel(), memberTypeDTO.getId(), MemberType.class.toString());
        }
        Optional<MemberType> output = memberTypeService.findById(savedRecord.getId());
        return output.get();
    }


    @GetMapping("/pl-difficulty/all")
    public PLDifficulty[] getPuzzleLevelDiffculty(Model model) {  return PLDifficulty.values();   }


    @GetMapping("/pl-status/all")
    public PLStatus[] getPuzzleLevelStatus(Model model) {
        return PLStatus.values();
    }

    @GetMapping("/pl-rule-event-type/all")
    public PLRuleEventType[] getPuzzleLevelRuleEventTypes(Model model) {
        return PLRuleEventType.values();
    }


    @GetMapping("/game-status/all")
    public GameStatus[] getGameStatus(Model model) {
        return GameStatus.values();
    }




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



    @GetMapping("/binary-type/all")
    public BinaryContentType[] getBinaryTypes(Model model) {  return BinaryContentType.values();   }
    @RequestMapping(value = "/binary-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BinaryContentType getBinaryTypeById(@PathVariable Long id) {   return BinaryContentType.getById(id);   }

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