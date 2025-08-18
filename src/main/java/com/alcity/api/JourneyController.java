package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.journey.*;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.service.Journey.RoadMapService;
import com.alcity.customexception.ResponseObject;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.Journey.JourneyStepService;
import com.alcity.service.puzzle.PGService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Tag(name = "Journey APIs ", description = "Get Journey data for ....")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    private JourneyService service;
    @Autowired
    private JourneyStepService journeyStepService;
    @Autowired
    private RoadMapService roadMapService;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private PGService pgService;

    @GetMapping("/all")
    public Collection<JourneyDTO> getJourneis(Model model) {
        Collection<JourneyDTO> journeyDTOCollection = DTOUtil.getJourneyDTOS(service.findAll());
        return journeyDTOCollection;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JourneyDTO getJourneyById(@PathVariable Long id) {
        Optional<Journey> journey = service.findById(id);
        JourneyDTO journeyDTO = DTOUtil.getJourneyDTO(journey.get());
        return journeyDTO;
    }

    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<JourneyDTO> getJourneyByCriteria(@PathVariable String criteria) {
        Collection<Journey> journeyCollection = service.findByTitleContains(criteria);
        Collection<JourneyDTO>  journeyDTOCollection = new ArrayList<JourneyDTO>();
        Iterator<Journey> itr = journeyCollection.iterator();

        while(itr.hasNext()){
            Journey journey = itr.next();
            JourneyDTO journeyDTO= DTOUtil.getJourneyDTO(journey);
            journeyDTOCollection.add(journeyDTO);
        }
        return journeyDTOCollection;
    }

    @Operation( summary = "Save a  Journey ",  description = "save a Journey entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage save(@RequestBody JourneyDTO dto) throws ResponseObject {
        Journey savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<Journey> journeyOptional = service.findById(dto.getId());
        try{
            if (journeyOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation , Status.error.name() , Journey.class.getSimpleName() ,-1L ,e.getCause().getMessage());
        }

        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), Journey.class.getSimpleName() , savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(),  Journey.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }

    @Operation( summary = "Save a  Journey Step",  description = "save a Journey Step and their data to data base")
    @PostMapping("/save/step")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveOrEditJourneyStep(@RequestBody JourneyStepRecord dto) throws ResponseObject {
        JourneyStep savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<JourneyStep> journeyStepOptional = journeyStepService.findById(dto.getId());
        try{
            if (journeyStepOptional.isEmpty())
                savedRecord = journeyStepService.save(dto,"Save");
            else
                savedRecord = journeyStepService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,JourneyStep.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),JourneyStep.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(), JourneyStep.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);
     return response;
    }

    @Operation( summary = "Update all Road Map positions",  description = "update all Road Map position ")
    @PostMapping("/update/all/road-maps")
    @CrossOrigin(origins = "*")
    public ResponseMessage updateRoadMaps(@RequestBody Collection<RoadMapUpdatePos> dtos) {
        ResponseMessage response = new ResponseMessage();
       Collection<RoadMap> roadMaps = roadMapService.updateAll(dtos);
       if(roadMaps == null || roadMaps.size() != dtos.size()) {
           response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), JourneyStep.class.getSimpleName() ,  1L, SystemMessage.SaveOrEditMessage_Fail);
       }else {
           response = new ResponseMessage(ErrorType.SaveSuccess,  Status.ok.name(),JourneyStep.class.getSimpleName() , 1L, SystemMessage.SaveOrEditMessage_Success);
       }
        return response;
    }

    @Operation( summary = "Save a  Road Map",  description = "save a Road Map and their data to data base")
    @PostMapping("/save/road-map")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveOrEditRoadMap(@RequestBody RoadMapDTO dto) throws ResponseObject {
        RoadMap savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<RoadMap> journeyStepOptional = roadMapService.findById(dto.getId());
        try{
            if (journeyStepOptional.isEmpty())
                savedRecord = roadMapService.save(dto,"Save");
            else
                savedRecord = roadMapService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , RoadMap.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),RoadMap.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), RoadMap.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }

    @DeleteMapping("/del/road-map/id/{id}")
    public ResponseMessage deleteJourneyRoadMapById(@PathVariable Long id) {
        Optional<RoadMap> existingRecord = roadMapService.findById(id);
        if(existingRecord.isPresent()){
            try {
                roadMapService.delete(existingRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,  Status.error.name(),RoadMap.class.getSimpleName(), id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), RoadMap.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),RoadMap.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @DeleteMapping("/del/step/id/{id}")
    public ResponseMessage deleteJourneyStepById(@PathVariable Long id) {
        Optional<JourneyStep> existingRecord = journeyStepService.findById(id);
        if(existingRecord.isPresent()){
            try {
                journeyStepService.delete(existingRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), JourneyStep.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),JourneyStep.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),JourneyStep.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @DeleteMapping("/del/{id}")
    public ResponseMessage deleteJourneyById(@PathVariable Long id) {
        Optional<Journey> requestedRecord = service.findById(id);
         if(requestedRecord.isPresent()){
             try {
                 service.delete(requestedRecord.get());
             }
             catch (Exception e) {
                 throw  new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(),Journey.class.getSimpleName(),  id,e.getCause().getMessage());
             }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),Journey.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),Journey.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Get all steps for a Journey ",  description = "Get all steps")
    @RequestMapping(value = "/id/{id}/step/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<JourneyStepDTO> getJourneyStepsById(@PathVariable Long id) {
        Optional<Journey> journey = service.findById(id);
        Collection<JourneyStep> steps = journey.get().getJourneyStepCollection();
        Collection<JourneyStepDTO> dtos = DTOUtil.getJorneyStepsDTOS(steps);
        return dtos;
    }

    @Operation( summary = "Get all road maps for a Journey ",  description = "Get all steps")
    @RequestMapping(value = "/id/{id}/road-map/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<RoadMapDTO> getJourneyRoadMapsById(@PathVariable Long id) {
        Optional<Journey> journeyOptional = service.findById(id);
        Collection<RoadMap> roadMaps = journeyOptional.get().getRoadMaps();
        Collection<RoadMapDTO> dtos = DTOUtil.getJourneyRoadMapsDTOS(roadMaps);
        return dtos;
    }


}
