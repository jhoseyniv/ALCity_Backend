package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.challenge.ChallengeInitiatorDTO;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.challenge.Challenge;
import com.alcity.entity.challenge.ChallengeInitiator;
import com.alcity.entity.journey.RoadMap;
import com.alcity.service.challenge.ChallengeInitiatorService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Challenge Initiator Entity  API's ", description = "Get Challenge  Initiator API for ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/challenge-initiator")

public class ChallengeInitiatorController {
    @Autowired
    private ChallengeInitiatorService challengeInitiatorService;


    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    @Operation( summary = "Get All Challenge initiator ",  description = "Get All Challenge initiator ...")
    public Collection<ChallengeInitiatorDTO> getAllChallengesInitiator() {
        Collection<ChallengeInitiator> challenges = challengeInitiatorService.findAll();
        Collection<ChallengeInitiatorDTO> dtos = new ArrayList<>();
        dtos = DTOUtil.getChallengeInitiatorDTOS(challenges);
        return dtos;
    }

    @Operation( summary = "Get Challenge Initiator by Id ",  description = "Get Challenge Initiator by Id ...")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ChallengeInitiatorDTO getChallengeInitiatorById(@PathVariable Long id) {
        Optional<ChallengeInitiator> challengeInitiatorOptional = challengeInitiatorService.findById(id);
        return challengeInitiatorOptional.map(DTOUtil::getChallengeInitiatorDTO).orElse(null);
    }

    @Operation( summary = "Save a Challenge Initiator  ",  description = "Save a Challenge Initiator ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveChallengeInitiator(@RequestBody ChallengeInitiatorDTO dto)  {
        ChallengeInitiator savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<ChallengeInitiator> challengeInitiatorOptional = challengeInitiatorService.findById(dto.getId());
        try{
            if (challengeInitiatorOptional.isEmpty())
                savedRecord = challengeInitiatorService.save(dto,"Save");
            else
                savedRecord = challengeInitiatorService.save(dto, "Edit");
            if(savedRecord !=null)
                response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),Challenge.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), Challenge.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , Challenge.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        return response;
    }

    @DeleteMapping("/del/id/{id}")
    public ResponseMessage deleteChallengeInitiatorById(@PathVariable Long id) {
        Optional<ChallengeInitiator> existingRecord = challengeInitiatorService.findById(id);
        if(existingRecord.isPresent()){
            try {
                challengeInitiatorService.delete(existingRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,  Status.error.name(), RoadMap.class.getSimpleName(), id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), RoadMap.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),RoadMap.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

}
