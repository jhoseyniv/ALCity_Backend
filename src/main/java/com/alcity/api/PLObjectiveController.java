package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.service.puzzle.PLObjectiveService;
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

@Tag(name = "Puzzle Level Objective API's ", description = "Get Puzzle Levels Objectives for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plo")
public class PLObjectiveController {

    @Autowired
    private PLObjectiveService plObjectiveService;

    @Operation( summary = "Fetch all puzzle Level Objectives ",  description = "fetches all data for all puzzle level Objectives ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<PLObjectiveDTO> getPuzzleLevelObjectives(Model model) {
        Collection<PLObjectiveDTO> objectiveDTOS = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> objectives = plObjectiveService.findAll();
        for (PLObjective plObjective : objectives) {
            PLObjectiveDTO plObjectiveDTO = DTOUtil.getPuzzleLevelObjectiveDTO(plObjective);
            objectiveDTOS.add(plObjectiveDTO);
        }
        return objectiveDTOS;
    }
    @Operation( summary = "Delete a  Puzzle Level Objective",  description = "Delete a Puzzle Level Objective")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deletePuzzleLevelObjectiveById(@PathVariable Long id) {
        Optional<PLObjective> requestedRecord = plObjectiveService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                plObjectiveService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), PLObjective.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PLObjective.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), PLObjective.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Fetch a objective by a Id ",  description = "fetches all data for a objectives")
    @RequestMapping(value = "/objective/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLObjectiveDTO getObjectivesById(@PathVariable Long id) {
        PLObjectiveDTO plObjectiveDT= new PLObjectiveDTO();
        Optional<PLObjective> plObjectiveOptional = plObjectiveService.findById(id);
        if(plObjectiveOptional.isPresent())
            plObjectiveDT = DTOUtil.getPuzzleLevelObjectiveDTO(plObjectiveOptional.get());
        return plObjectiveDT;
    }

    @Operation( summary = "Save a puzzle level  Objective  ",  description = "Save a puzzle level  objective entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePLObjective(@RequestBody PLObjectiveDTO dto)  {
        PLObjective savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PLObjective> plObjectiveOptional = plObjectiveService.findById(dto.getId());
        try{
            if (plObjectiveOptional.isEmpty())
                savedRecord = plObjectiveService.save(dto,"Save");
            else
                savedRecord = plObjectiveService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,PLObjective.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PLObjective.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),PLObjective.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

    }

}
