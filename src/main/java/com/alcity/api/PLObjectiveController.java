package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Puzzle Level Objective API's ", description = "Get Puzzle Levels Objectives for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plo")
public class PLObjectiveController {

    @Autowired
    private PLObjectiveService plObjectiveService;



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
    @PostMapping("/objective/id/{id}/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePLObjective(@RequestBody PLObjectiveDTO dto)  {
        PLObjective savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plObjectiveService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getTitle(), dto.getId(), PuzzleLevel.class.toString());
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plObjectiveService.save(dto, "Edit");
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

}
