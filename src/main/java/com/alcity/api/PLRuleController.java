package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.service.puzzle.PLRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Puzzle Level Rule API's ", description = "Puzzle Levels Rules API's")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plr")
public class PLRuleController {
    @Autowired
    private PLRuleService plRuleService;
    @Operation( summary = "Save a puzzle level  Rule  ",  description = "Save a puzzle level  Rule entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePLRule(@RequestBody PLRuleDTO dto)  {
        PLRule savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plRuleService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getTitle(), dto.getId(), PLRule.class.toString());
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plRuleService.save(dto, "Edit");
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
