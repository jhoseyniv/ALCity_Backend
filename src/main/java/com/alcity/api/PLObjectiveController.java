package com.alcity.api;

import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
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
        Collection<PLObjectiveDTO> plObjectiveDTOCollection = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> plObjectiveCollection = plObjectiveService.findAll();
        Iterator<PLObjective> itr = plObjectiveCollection.iterator();
        while(itr.hasNext()){
            PLObjectiveDTO plObjectiveDTO = DTOUtil.getPuzzleLevelObjectiveDTO(itr.next());
            plObjectiveDTOCollection.add(plObjectiveDTO);
        }
        return plObjectiveDTOCollection;
    }
    @Operation( summary = "delete a  Puzzle Level Objective",  description = "delete a Puzzle Level Objective")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deletePuzzleLevelObjectiveById(@PathVariable Long id) {
        Optional<PLObjective> existingRecord = plObjectiveService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plObjectiveService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLObjective.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, id, SystemMessage.RecordNotFound);
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
    public ResponseObject savePLObjective(@RequestBody PLObjectiveDTO dto)  {
        PLObjective savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plObjectiveService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLObjective.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plObjectiveService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }

}
