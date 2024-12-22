package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.service.puzzle.PLRuleService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @Operation( summary = "Fetch a Rule by a Id ",  description = "fetch a rule")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLRuleDTO getRuleById(@PathVariable Long id) {
        PLRuleDTO ruleDTO= new PLRuleDTO();
        Optional<PLRule> plRuleOptional = plRuleService.findById(id);
        if(plRuleOptional.isPresent())
            ruleDTO = DTOUtil.getPLRuleDTO(plRuleOptional.get());
        return ruleDTO;
    }


    @Operation( summary = "delete a  Puzzle Level Rule",  description = "delete a Puzzle Level Rule")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleLevelRuleById(@PathVariable Long id) {
        Optional<PLRule> existingRecord = plRuleService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plRuleService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getTitle(), existingRecord.get().getId(), PLRule.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


}
