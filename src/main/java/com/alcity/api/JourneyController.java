package com.alcity.api;

import com.alcity.dto.journey.*;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.service.Journey.RoadMapService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
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
    private JourneyService journeyService;
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
        Collection<JourneyDTO> journeyDTOCollection = DTOUtil.getJourneyDTOS(journeyService.findAll());
        return journeyDTOCollection;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JourneyDTO getJourneyById(@PathVariable Long id) {
        Optional<Journey> journey = journeyService.findById(id);
        JourneyDTO journeyDTO = DTOUtil.getJourneyDTO(journey.get());
        return journeyDTO;
    }

    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<JourneyDTO> getJourneyByCriteria(@PathVariable String criteria) {
        Collection<Journey> journeyCollection = journeyService.findByTitleContains(criteria);
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
    public ALCityResponseObject saveOrEditJourney(@RequestBody JourneyDTO dto)  {
        Journey savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = journeyService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + Journey.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = journeyService.save(dto, "Edit");
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

    @Operation( summary = "Save a  Journey Step",  description = "save a Journey Step and their data to data base")
    @PostMapping("/save/step")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveOrEditJourneyStep(@RequestBody JourneyStepRecord dto)  {
        JourneyStep savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = journeyStepService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + JourneyStep.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = journeyStepService.save(dto, "Edit");
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

    @Operation( summary = "Update all Road Map positions",  description = "update all Road Map position ")
    @PostMapping("/update/all/road-maps")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject updateRoadMaps(@RequestBody Collection<RoadMapUpdatePos> dtos) {
        ALCityResponseObject responseObject = new ALCityResponseObject();
       Collection<RoadMap> roadMaps = roadMapService.updateAll(dtos);
       if(roadMaps == null || roadMaps.size() != dtos.size()) {
           responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok",-1L , "Records Not Update try again!");
       }else {
           responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok",1L , "All Records Update Successfully!");

       }
        return responseObject;
    }

    @Operation( summary = "Save a  Road Map",  description = "save a Road Map and their data to data base")
    @PostMapping("/save/road-map")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveOrEditRoadMap(@RequestBody RoadMapDTO dto)  {
        RoadMap savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = roadMapService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + RoadMap.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = roadMapService.save(dto, "Edit");
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

    @DeleteMapping("/del/road-map/id/{id}")
    public ResponseEntity<String> deleteJourneyRoadMapById(@PathVariable Long id) {
        Optional<RoadMap> existingRecord = roadMapService.findById(id);
        if(existingRecord.isPresent()){
            try {
                roadMapService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", RoadMap.class.toString(),existingRecord.get().getId());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/del/step/id/{id}")
    public ResponseEntity<String> deleteJourneyStepById(@PathVariable Long id) {
        Optional<JourneyStep> existingRecord = journeyStepService.findById(id);
        if(existingRecord.isPresent()){
            try {
                journeyStepService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", JourneyStep.class.toString(),existingRecord.get().getId());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteJourneyById(@PathVariable Long id) {
        Optional<Journey> existingRecord = journeyService.findById(id);
        if(existingRecord.isPresent()){
            try {
                journeyService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", Journey.class.toString(),existingRecord.get().getId());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation( summary = "Get all steps for a Journey ",  description = "Get all steps")
    @RequestMapping(value = "/id/{id}/step/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<JourneyStepDTO> getJourneyStepsById(@PathVariable Long id) {
        Optional<Journey> journey = journeyService.findById(id);
        Collection<JourneyStep> steps = journey.get().getJourneyStepCollection();
        Collection<JourneyStepDTO> dtos = DTOUtil.getJorneyStepsDTOS(steps);
        return dtos;
    }

    @Operation( summary = "Get all road maps for a Journey ",  description = "Get all steps")
    @RequestMapping(value = "/id/{id}/road-map/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<RoadMapDTO> getJourneyRoadMapsById(@PathVariable Long id) {
        Optional<Journey> journeyOptional = journeyService.findById(id);
        Collection<RoadMap> roadMaps = journeyOptional.get().getRoadMaps();
        Collection<RoadMapDTO> dtos = DTOUtil.getJourneyRoadMapsDTOS(roadMaps);
        return dtos;
    }


}
