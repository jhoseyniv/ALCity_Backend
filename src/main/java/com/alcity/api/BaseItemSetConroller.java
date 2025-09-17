package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.alenum.EnumDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.base.PLPrivacyDTO;
import com.alcity.dto.appmember.MemberTypeDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.*;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.service.base.*;
import com.alcity.service.puzzle.PLRuleEventService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Basic Data Type APIs", description = "All APIs for basic data types... ")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/base")
public class BaseItemSetConroller {

    @Autowired
    private WalletItemTypeService walletItemTypeService;

    @Operation( summary = "Fetch all Genders ",  description = "fetches all Gender entities and their data from data source")
    @GetMapping("/gender/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getGenders(Model model) {

        return DTOUtil.getEnumByClass(UserGender.class);
    }
    @Operation( summary = "Fetch all Skill Types ",  description = "fetch all Skill types and their data from data source")
    @GetMapping("/skill-type/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getSkillTypes(Model model) {
        return DTOUtil.getEnumByClass(SkillType.class);
    }

    @Operation( summary = "Fetch all PL Rule Post Action Owner Types ",  description = "fetches all PL Rule Post Action Owner Types entities and their data from data source")
    @GetMapping("/pl-rule-post-action-type/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getPLRulePostActionOwnerType(Model model) {
        return DTOUtil.getEnumByClass(PLRulePostActionOwnerType.class);
    }

    @RequestMapping(value = "/gender/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public UserGender getGenderById(@PathVariable Long id) {
        return UserGender.getById(id);
    }

    @Operation( summary = "Fetch all Wallet Item Types ",  description = "fetches all Wallet Item Types  entities and their data from data source")
    @GetMapping("/wallet/type/all")
    @CrossOrigin(origins = "*")
    public Collection<WalletItemTypeDTO> getWalletItemType(Model model) {
            Collection<WalletItemType> walletItemTypes = walletItemTypeService.findAll();
            Collection<WalletItemTypeDTO> dtos = new ArrayList<>();
            dtos = DTOUtil.getWalletItemTypeDTOS(walletItemTypes);
        return dtos;
    }

    @Operation( summary = "Fetch all pl rule post action Types ",  description = "fetches all pl rule post action type their data from data source")
    @GetMapping("/pl-rule/post-action-type/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getPLRulePostActionTypes(Model model) {
        return DTOUtil.getEnumByClass(PLRulePostActionType.class);
    }

//    @Operation( summary = "Fetch a pl rule post action Types ",  description = "fetch a pl rule post action type their data from data source")
//    @RequestMapping(value = "/pl-rule/post-action-type/id/{id}" , method = RequestMethod.GET)
//    @CrossOrigin(origins = "*")
//    public PLRulePostActionTypeDTO getPLRulePostActionType(@PathVariable Long id) {
//        Optional<PLRulePostActionType> rulePostActionType = plRulePostActionTypeService.findById(id);
//        if(rulePostActionType.isEmpty()) return null;
//        return DTOUtil.getPLRulePostActionTypeDTO(rulePostActionType.get());
//    }



    @Autowired
    private ClientTypeService clientTypeService;

    @Operation( summary = "Fetch all client Types ",  description = "fetches all Client Types entities and their data from data source")
    @GetMapping("/client-type/all")
    @CrossOrigin(origins = "*")
    public Collection<ClientTypeDTO> getClientTypes(Model model) {
        Collection<ClientTypeDTO> clientTypeDTOS = new ArrayList<>();
        Collection<ClientType> clientTypes = clientTypeService.findAll();
        clientTypeDTOS = DTOUtil.getClientTypeDTOS(clientTypes);
        return clientTypeDTOS;
    }


    @Operation( summary = "Fetch a client Type by id ",  description = "fetch a  Client Types entity and their data from data source")
    @RequestMapping(value = "/client-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ClientTypeDTO getClientTypeById(@PathVariable Long id) {
        Optional<ClientType> clientTypeOptional = clientTypeService.findById(id);
         if(clientTypeOptional.isPresent())
             return DTOUtil.getClientTypeDTO(clientTypeOptional.get());

        return null;
    }
    @Operation( summary = "Save a Client Type ",  description = "save a  Client Types entity and their data to data base")
    @PostMapping("/client-type/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveClientType(@RequestBody ClientTypeDTO dto)  {
        ClientType savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<ClientType> clientTypeOptional = clientTypeService.findById(dto.getId());
        try{
            if (clientTypeOptional.isEmpty())
                savedRecord = clientTypeService.save(dto,"Save");
            else
                savedRecord = clientTypeService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,ClientType.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), ClientType.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), ClientType.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

    }
    @Operation( summary = "Save a puzzle level privacy ",  description = "Save a puzzle level privacy entity and their data to data base")
    @PostMapping("/pl-privacy/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePuzzleLevelPrivacy(@RequestBody PLPrivacyDTO dto)  {
        PLPrivacy savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PLPrivacy> plPrivacyOptional = plPrivacyService.findById(dto.getId());
        try{
            if (plPrivacyOptional.isEmpty())
                savedRecord = plPrivacyService.save(dto,"Save");
            else
                savedRecord = plPrivacyService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,PLPrivacy.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), PLPrivacy.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), PLPrivacy.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

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
    @Operation( summary = "Fetch all member Types ",  description = "fetches all member Types entities and their data from data base")
    @GetMapping("/member-type/all")
    @CrossOrigin(origins = "*")
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
    public ResponseMessage saveMemberType(@RequestBody MemberTypeDTO dto)  {
        MemberType savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<MemberType> memberTypeOptional = memberTypeService.findById(dto.getId());
        try{
            if (memberTypeOptional.isEmpty())
                savedRecord = memberTypeService.save(dto,"Save");
            else
                savedRecord = memberTypeService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,MemberType.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), MemberType.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), MemberType.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

    }

    @Operation( summary = "Fetch all puzzle level  difficulty  ",  description = "fetches all puzzle level  difficulty entities and their data from data base")
    @GetMapping("/pl-difficulty/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getPuzzleLevelDiffculty(Model model) {
        return DTOUtil.getEnumByClass(PLDifficulty.class);
    }

    @GetMapping("/pl-status/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getPuzzleLevelStatus(Model model) {
        return DTOUtil.getEnumByClass(PLStatus.class);
    }

    @GetMapping("/pl-rule-event-type/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getPuzzleLevelRuleEventTypes(Model model) {
        return DTOUtil.getEnumByClass(PLRuleEventType.class);
    }


    @GetMapping("/game-status/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getGameStatus(Model model) {
        return DTOUtil.getEnumByClass(GameStatus.class);
    }
    @GetMapping("/wallet-transaction-type/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getWalletTransactionType(Model model) {
        return DTOUtil.getEnumByClass(WalletTransactionType.class);
    }

    @Autowired
    private PLPrivacyService plPrivacyService;
    @Autowired
    private PLRuleEventService plRuleEventService;

    @GetMapping("/pl-privacy/all")
    @CrossOrigin(origins = "*")
    public Collection<PLPrivacy> getPLPrivacy(Model model) {
        Collection<PLPrivacy> plPrivacyCollection = plPrivacyService.findAll();
        return plPrivacyCollection;
    }
    @Operation( summary = "Get all puzzle level rule event ",  description = "Get all puzzle level rule event ")
    @GetMapping("/pl-rule-event/all")
    @CrossOrigin(origins = "*")
    public Collection<PLRuleEvent> getPLRuleEvent(Model model) {
        Collection<PLRuleEvent> plRuleEvents = plRuleEventService.findAll();
        return plRuleEvents;
    }

    @RequestMapping(value = "/pl-privacy/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Optional<PLPrivacy> getPuzzleLevelPrivacyById(@PathVariable Long id) {
        Optional<PLPrivacy> puzzleLevelPrivacy = plPrivacyService.findById(id);
        return puzzleLevelPrivacy;
    }
    @DeleteMapping("/pl-privacy/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deletePLPrivacyById(@PathVariable Long id) {
        Optional<PLPrivacy> requestedRecord = plPrivacyService.findById(id);
       // ResponseMessage response = new ResponseMessage();
        if(requestedRecord.isPresent()){
            try {
                plPrivacyService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,  Status.error.name(),PLPrivacy.class.getSimpleName(), id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess, Status.ok.name(),PLPrivacy.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
            return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),PLPrivacy.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @DeleteMapping("/client-type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteClientTypeById(@PathVariable Long id) {
        Optional<ClientType> requestedRecord = clientTypeService.findById(id);
        if (requestedRecord.isPresent()) {
            try {
                clientTypeService.deleteById(requestedRecord.get().getId());
            }
            catch (Exception e) {
                    throw  new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(),ClientType.class.getSimpleName(),  id,e.getCause().getMessage());
                }
                return new ResponseMessage(ErrorType.DeleteSuccess, Status.ok.name(),ClientType.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
            }
            return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),ClientType.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }


    @DeleteMapping("/member-type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteMemberTypeById(@PathVariable Long id) {
        Optional<MemberType> requestedRecord = memberTypeService.findById(id);
        if (requestedRecord.isPresent()) {
            try {
                memberTypeService.deleteById(requestedRecord.get().getId());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), MemberType.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess,Status.ok.name(), MemberType.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),MemberType.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @GetMapping("/binary-type/all")
    @CrossOrigin(origins = "*")
    public BinaryContentType[] getBinaryTypes(Model model) {  return BinaryContentType.values();   }

    @RequestMapping(value = "/binary-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public BinaryContentType getBinaryTypeById(@PathVariable Long id) {   return BinaryContentType.getById(id);   }


}