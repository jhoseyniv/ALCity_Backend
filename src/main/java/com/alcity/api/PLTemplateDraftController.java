package com.alcity.api;


import com.alcity.dto.puzzle.PLTemplateDraftDTO;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PLTemplateDraft;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLTemplateDraftService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Puzzle Level Template Draft ", description = "Puzzle Level Template Draft APIs...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pltd")

public class PLTemplateDraftController {
    @Autowired
    private PLTemplateDraftService plTemplateDraftService;


    @Operation( summary = "Fetch puzzle level template draft by a Id ",  description = "fetches all data for a puzzle level template draft ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLTemplateDraftDTO getPLTemplateDraftById(@PathVariable Long id) {
        PLTemplateDraftDTO dto= new PLTemplateDraftDTO();
        Optional<PLTemplateDraft> plTemplateDraftOptional = plTemplateDraftService.findById(id);
        if(plTemplateDraftOptional.isEmpty()) return null;
        dto = DTOUtil.getPLTemplateDraftDTO(plTemplateDraftOptional.get());
        return dto;
    }

    @Operation( summary = "Save a puzzle level Template Draft  ",  description = "Save a puzzle level Template Draft entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleLevelTemplateDraft(@RequestBody PLTemplateDraftDTO dto)  {
        PLTemplateDraft savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plTemplateDraftService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLTemplateDraft.class , "Error",dto.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plTemplateDraftService.save(dto, "Edit");
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

    @Operation( summary = "Delete a  Puzzle Level Template Draft ",  description = "Delete a  Puzzle Level Template Draft ")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleLevelTemplateDraftById(@PathVariable Long id) {
        Optional<PLTemplateDraft> existingRecord = plTemplateDraftService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plTemplateDraftService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLRulePostAction.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }



}



