package com.alcity.api;

import com.alcity.dto.journey.*;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.service.Journey.RoadMapService;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseObject save(@RequestBody JourneyDTO dto)  {
        Journey savedRecord = null;
        ResponseObject response = new ResponseObject();
        Optional<Journey> journeyOptional = service.findById(dto.getId());
        try{
            if (journeyOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Journey.class.getSimpleName() ,ActionStatus.Error , -1L ,e.getCause().getMessage());
        }

        if(savedRecord !=null)
            response = new ResponseObject(ErrorType.SaveSuccess, Journey.class.getSimpleName() ,ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseObject(ErrorType.SaveFail, Journey.class.getSimpleName() ,ActionStatus.Error, -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }

    @Operation( summary = "Save a  Journey Step",  description = "save a Journey Step and their data to data base")
    @PostMapping("/save/step")
    @CrossOrigin(origins = "*")
    public ResponseObject saveOrEditJourneyStep(@RequestBody JourneyStepRecord dto)  {
        JourneyStep savedRecord = null;
        ResponseObject response = new ResponseObject();
        Optional<JourneyStep> journeyStepOptional = journeyStepService.findById(dto.getId());
        try{
            if (journeyStepOptional.isEmpty())
                savedRecord = journeyStepService.save(dto,"Save");
            else
                savedRecord = journeyStepService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, JourneyStep.class.getSimpleName() ,ActionStatus.Error , -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseObject(ErrorType.SaveSuccess, JourneyStep.class.getSimpleName() ,ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseObject(ErrorType.SaveFail, JourneyStep.class.getSimpleName() ,ActionStatus.Error, -1L, SystemMessage.SaveOrEditMessage_Fail);
     return response;
    }

    @Operation( summary = "Update all Road Map positions",  description = "update all Road Map position ")
    @PostMapping("/update/all/road-maps")
    @CrossOrigin(origins = "*")
    public ResponseObject updateRoadMaps(@RequestBody Collection<RoadMapUpdatePos> dtos) {
        ResponseObject response = new ResponseObject();
       Collection<RoadMap> roadMaps = roadMapService.updateAll(dtos);
       if(roadMaps == null || roadMaps.size() != dtos.size()) {
           response = new ResponseObject(ErrorType.SaveFail, JourneyStep.class.getSimpleName() , ActionStatus.OK, 1L, SystemMessage.SaveOrEditMessage_Fail);
       }else {
           response = new ResponseObject(ErrorType.SaveSuccess, JourneyStep.class.getSimpleName() , ActionStatus.OK, 1L, SystemMessage.SaveOrEditMessage_Success);
       }
        return response;
    }

    @Operation( summary = "Save a  Road Map",  description = "save a Road Map and their data to data base")
    @PostMapping("/save/road-map")
    @CrossOrigin(origins = "*")
    public ResponseObject saveOrEditRoadMap(@RequestBody RoadMapDTO dto)  {
        RoadMap savedRecord = null;
        ResponseObject response = new ResponseObject();
        Optional<RoadMap> journeyStepOptional = roadMapService.findById(dto.getId());
        try{
            if (journeyStepOptional.isEmpty())
                savedRecord = roadMapService.save(dto,"Save");
            else
                savedRecord = roadMapService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, RoadMap.class.getSimpleName() ,ActionStatus.Error , -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseObject(ErrorType.SaveSuccess, RoadMap.class.getSimpleName() ,ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseObject(ErrorType.SaveFail, RoadMap.class.getSimpleName() ,ActionStatus.Error, -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }

    @DeleteMapping("/del/road-map/id/{id}")
    public ResponseObject deleteJourneyRoadMapById(@PathVariable Long id) {
        Optional<RoadMap> existingRecord = roadMapService.findById(id);
        if(existingRecord.isPresent()){
            try {
                roadMapService.delete(existingRecord.get());
            }
            catch (Exception e) {
                return new ResponseObject(ErrorType.ForeignKeyViolation, RoadMap.class.getSimpleName(),ActionStatus.Error, id,e.getCause().getMessage());
            }
            return new ResponseObject(ErrorType.SaveSuccess, BaseObject.class.getSimpleName(),ActionStatus.OK, id,SystemMessage.DeleteMessage);
        }
        return  new ResponseObject(ErrorType.RecordNotFound,BaseObject.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);
    }

    @DeleteMapping("/del/step/id/{id}")
    public ResponseObject deleteJourneyStepById(@PathVariable Long id) {
        Optional<JourneyStep> existingRecord = journeyStepService.findById(id);
        if(existingRecord.isPresent()){
            try {
                journeyStepService.delete(existingRecord.get());
            }
            catch (Exception e) {
                return new ResponseObject(ErrorType.ForeignKeyViolation, JourneyStep.class.getSimpleName(),ActionStatus.Error, id,e.getCause().getMessage());
            }
            return new ResponseObject(ErrorType.SaveSuccess, BaseObject.class.getSimpleName(),ActionStatus.OK, id,SystemMessage.DeleteMessage);
        }
        return  new ResponseObject(ErrorType.RecordNotFound,BaseObject.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);
    }

    @DeleteMapping("/del/{id}")
    public ResponseObject deleteJourneyById(@PathVariable Long id) {
        Optional<Journey> requestedRecord = service.findById(id);
         if(requestedRecord.isPresent()){
             try {
                 service.delete(requestedRecord.get());
             }
             catch (Exception e) {
                 return new ResponseObject(ErrorType.ForeignKeyViolation, Journey.class.getSimpleName(),ActionStatus.Error, id,e.getCause().getMessage());
             }
            return new ResponseObject(ErrorType.SaveSuccess, BaseObject.class.getSimpleName(),ActionStatus.OK, id,SystemMessage.DeleteMessage);
        }
        return  new ResponseObject(ErrorType.RecordNotFound,BaseObject.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);
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
