package com.alcity.api;


import com.alcity.dto.puzzle.PLEventDTO;
import com.alcity.dto.puzzle.PLRuleEventDTO;
import com.alcity.dto.puzzle.PLRulePostActionDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLRuleEventService;
import com.alcity.service.puzzle.PLRuleService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Level Rule Event API's ", description = "Puzzle Levels Rule Event API's")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plr-event")

public class PLRuleEventController {
    @Autowired
    private PLRuleEventService plRuleEventService;
    @Operation( summary = "Fetch all PLR Events",  description = "Fetch all PLR Events")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public Collection<PLRuleEventDTO> getALLPLRuleEvents(Model model) {
        Collection<PLRuleEvent> plRuleEvents = plRuleEventService.findAll();
        Collection<PLRuleEventDTO> dtos = new ArrayList<>();
        dtos = DTOUtil.getPLRuleEventDTOS(plRuleEvents);
        return dtos;
    }
    @Operation( summary = "Save a puzzle level  Rule Event  ",  description = "Save a puzzle level  Rule Event ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePLRuleEvent(@RequestBody PLRuleEventDTO dto)  {
        PLRuleEvent savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plRuleEventService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLRuleEvent.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plRuleEventService.save(dto, "Edit");
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

    @Operation( summary = "Delete a puzzle level  Rule Event ",  description = "Delete a puzzle level  Rule Event ")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePLRuleEventById(@PathVariable Long id) {
        Optional<PLRuleEvent> existingRecord = plRuleEventService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plRuleEventService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLRuleEvent.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


}
