package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.alenum.EnumDTO;
import com.alcity.dto.base.BinaryContentDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Basic Data Type APIs", description = "All APIs for basic data types... ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/base")
public class BaseItemSetConroller {

    @Operation( summary = "Fetch all Genders ",  description = "fetches all Gender entities and their data from data source")

    @GetMapping("/gender/all")
    public Collection<EnumDTO> getGenders(Model model) {

        return DTOUtil.getEnumByClass(UserGender.class);
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
    @CrossOrigin
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
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getDataTypes(Model model) {
        return DTOUtil.getEnumByClass(DataType.class);
    }

    @RequestMapping(value = "/data-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public DataType getDataTypeById(@PathVariable Long id) {
        return DataType.getById(id); }

    @Autowired
    private MemberTypeService memberTypeService;
    @CrossOrigin(origins = "*")
    @Operation( summary = "Fetch all member Types ",  description = "fetches all member Types entities and their data from data base")
    @GetMapping("/member-type/all")
    public Collection<MemberTypeDTO> getMemberType(Model model) {
        Collection<MemberType> memberTypes = memberTypeService.findAll();
        Collection<MemberTypeDTO> memberTypeDTOCollection = DTOUtil.getMemberTypeDTOS(memberTypes);
        return memberTypeDTOCollection;
    }
    @RequestMapping(value = "/member-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public MemberTypeDTO getMemberTypeById(@PathVariable Long id) {
        Optional<MemberType> memberTypeOptional = memberTypeService.findById(id);
        if(memberTypeOptional.isPresent())
            return DTOUtil.getMemberTypeDTO(memberTypeOptional.get());
        return null;
    }

    @Operation( summary = "Save a Member Type ",  description = "Save a  Member Types entity and their data to data base")
    @PostMapping("/member-type/save")
    @CrossOrigin(origins = "*")
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

    @Operation( summary = "Fetch all puzzle level  difficulty  ",  description = "fetches all puzzle level  difficulty entities and their data from data base")
    @GetMapping("/pl-difficulty/all")
    public Collection<EnumDTO> getPuzzleLevelDiffculty(Model model) {
        return DTOUtil.getEnumByClass(PLDifficulty.class);
    }

    @GetMapping("/pl-status/all")
    public Collection<EnumDTO> getPuzzleLevelStatus(Model model) {
        return DTOUtil.getEnumByClass(PLStatus.class);
    }

    @GetMapping("/pl-rule-event-type/all")
    public Collection<EnumDTO> getPuzzleLevelRuleEventTypes(Model model) {
        return DTOUtil.getEnumByClass(PLRuleEventType.class);
    }


    @GetMapping("/game-status/all")
    public Collection<EnumDTO> getGameStatus(Model model) {
        return DTOUtil.getEnumByClass(GameStatus.class);
    }

    @Autowired
    private PLPrivacyService plPrivacyService;

    @GetMapping("/pl-privacy/all")
    public Collection<PLPrivacy> getPuzzleLevelPrivacy(Model model) {
        Collection<PLPrivacy> plPrivacyCollection = plPrivacyService.findAll();
        return plPrivacyCollection;
    }

    @RequestMapping(value = "/pl-privacy/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<PLPrivacy> getPuzzleLevelPrivacyById(@PathVariable Long id) {
        Optional<PLPrivacy> puzzleLevelPrivacy = plPrivacyService.findById(id);
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





}