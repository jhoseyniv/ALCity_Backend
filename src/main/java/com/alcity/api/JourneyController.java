package com.alcity.api;

import com.alcity.cruddto.journey.JourneyStepRecord;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.journey.JourneyStepDTO;
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
                throw new UniqueConstraintException(dto.getTitle(), dto.getId(), "Value and Lable Must be Unique");
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
                throw new UniqueConstraintException(dto.getTitle(), dto.getId(), "Value and Lable Must be Unique");
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

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteJourneyById(@PathVariable Long id) {
        Optional<Journey> existingRecord = journeyService.findById(id);
        if(existingRecord.isPresent()){
            try {
                journeyService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getTitle(), existingRecord.get().getId(), Journey.class.toString());
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



}
