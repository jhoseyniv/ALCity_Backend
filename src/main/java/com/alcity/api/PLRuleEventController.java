package com.alcity.api;


import com.alcity.dto.puzzle.PLRuleEventDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLRuleEventService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseObject savePLRuleEvent(@RequestBody PLRuleEventDTO dto)  {
        PLRuleEvent savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plRuleEventService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLRuleEvent.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plRuleEventService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }

    @Operation( summary = "Delete a puzzle level  Rule Event ",  description = "Delete a puzzle level  Rule Event ")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deletePLRuleEventById(@PathVariable Long id) {
        Optional<PLRuleEvent> existingRecord = plRuleEventService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plRuleEventService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLRuleEvent.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.error.name(), existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }


}
