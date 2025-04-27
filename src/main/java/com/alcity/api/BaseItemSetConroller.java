package com.alcity.api;

import com.alcity.dto.alobject.PLRulePostActionTypeDTO;
import com.alcity.entity.alobject.PLRulePostActionType;
import com.alcity.service.alobject.PLRulePostActionTypeService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.alenum.EnumDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.base.PLPrivacyDTO;
import com.alcity.dto.appmember.MemberTypeDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.base.*;
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
    @Operation( summary = "Fetch all Genders ",  description = "fetches all Gender entities and their data from data source")
    @GetMapping("/gender/all")
    @CrossOrigin(origins = "*")
    public Collection<EnumDTO> getGenders(Model model) {

        return DTOUtil.getEnumByClass(UserGender.class);
    }
    @Operation( summary = "Fetch all PL Rule Post Action Owner Type ",  description = "fetches all PL Rule Post Action Owner Type entities and their data from data source")
    @GetMapping("/pl-rule-post-action/all")
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
    public Collection<EnumDTO> getWalletItemType(Model model) {

        return DTOUtil.getEnumByClass(WalletItemCategory.class);
    }

    @Autowired
    private PLRulePostActionTypeService plRulePostActionTypeService;
    @Operation( summary = "Fetch all pl rule post action Types ",  description = "fetches all pl rule post action type their data from data source")
    @GetMapping("/pl-rule/post-action-type/all")
    @CrossOrigin(origins = "*")
    public Collection<PLRulePostActionTypeDTO> getPLRulePostActionTypes(Model model) {
        Collection<PLRulePostActionTypeDTO> dtos = new ArrayList<>();
        Collection<PLRulePostActionType> rulePostActionTypes = plRulePostActionTypeService.findAll();
        dtos = DTOUtil.getPLRulePostActionTypeDTOS(rulePostActionTypes);
        return dtos;
    }

    @Operation( summary = "Fetch a pl rule post action Types ",  description = "fetch a pl rule post action type their data from data source")
    @RequestMapping(value = "/pl-rule/post-action-type/id/{id}" , method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public PLRulePostActionTypeDTO getPLRulePostActionType(@PathVariable Long id) {
        Optional<PLRulePostActionType> rulePostActionType = plRulePostActionTypeService.findById(id);
        if(rulePostActionType.isEmpty()) return null;
        return DTOUtil.getPLRulePostActionTypeDTO(rulePostActionType.get());
    }



    @Autowired
    private ClientTypeService clientTypeService;

    @Operation( summary = "Fetch all client Types ",  description = "fetches all Client Types entities and their data from data source")
    @GetMapping("/client-type/all")
    @CrossOrigin(origins = "*")
    public Collection<ClientType> getClientTypes(Model model) {
        Collection<ClientTypeDTO> clientTypeDTOCollection = new ArrayList<>();
        Collection<ClientType> clientTypes = clientTypeService.findAll();
        return clientTypes;
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
    public ALCityResponseObject saveClientType(@RequestBody ClientTypeDTO dto)  {
        ClientType savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = clientTypeService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getValue(), dto.getId(), "Value and Label Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = clientTypeService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
    }
    @Operation( summary = "Save a puzzle level privacy ",  description = "Save a puzzle level privacy entity and their data to data base")
    @PostMapping("/pl-privacy/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleLevelPrivacy(@RequestBody PLPrivacyDTO dto)  {
        PLPrivacy savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plPrivacyService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getValue(), dto.getId(), "Value and Label Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plPrivacyService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
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
    public ALCityResponseObject saveMemberType(@RequestBody MemberTypeDTO dto)  {
        MemberType savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = memberTypeService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getValue(), dto.getId(), "Value and Lable Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = memberTypeService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
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
    public ResponseEntity<String> deletePLPrivacyById(@PathVariable Long id) {
        Optional<PLPrivacy> existingRecord = plPrivacyService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plPrivacyService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getValue(), existingRecord.get().getId(), PLPrivacy.class.toString());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/client-type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteclientTypeById(@PathVariable Long id) {
        Optional<ClientType> existingRecord = clientTypeService.findById(id);
        if(existingRecord.isPresent()){
            try {
                clientTypeService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getValue(), existingRecord.get().getId(), ClientType.class.toString());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/member-type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteMemberTypeById(@PathVariable Long id) {
        Optional<MemberType> existingRecord = memberTypeService.findById(id);
        if(existingRecord.isPresent()){
            try {
                memberTypeService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getValue(), existingRecord.get().getId(), MemberType.class.toString());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/binary-type/all")
    @CrossOrigin(origins = "*")
    public BinaryContentType[] getBinaryTypes(Model model) {  return BinaryContentType.values();   }

    @RequestMapping(value = "/binary-type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public BinaryContentType getBinaryTypeById(@PathVariable Long id) {   return BinaryContentType.getById(id);   }


}