package com.alcity.api;

import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLTemplateDTO;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLTemplateService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Tag(name = "Puzzle Level Template ", description = "Puzzle Level Template APIs...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plt")
public class PLTemplateController {
    @Autowired
    private PLTemplateService plTemplateService;

    @Operation( summary = "Fetch all puzzle level templates data ",  description = "fetches all data for all puzzle level templates structure ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<PLTemplateDTO> getPLTemplates(Model model) {
        Collection<PLTemplateDTO> templateDTOS = new ArrayList<PLTemplateDTO>();
        Collection<PLTemplate> templates = plTemplateService.findAll();
        templateDTOS = DTOUtil.getPLTemplateDTOS(templates);

        return templateDTOS;
    }

    @Operation( summary = "Fetch puzzle level template data by a Id ",  description = "fetches all data for a puzzle level template ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLTemplateDTO getPLTemplateById(@PathVariable Long id) {
        PLTemplateDTO   dto= new PLTemplateDTO();
        Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(id);
        if(plTemplateOptional.isEmpty()) return null;
        dto = DTOUtil.getPLTemplateDTO(plTemplateOptional.get());
        return dto;
    }


    @Operation( summary = "Save a puzzle level Template  ",  description = "Save a puzzle level Template  entity and their data to data base")
    @PostMapping("/new")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveNewPuzzleLevelTemplate(@RequestBody PLTemplateDTO dto)  {
        PLTemplate savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plTemplateService.saveNew(dto);
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLTemplate.class , "Error",dto.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        }

        if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "OK", savedRecord.getId(), "Record Saved Successfully!");

        return responseObject;
    }
    @Operation( summary = "edit a puzzle level Template content only  ",  description = "edit a puzzle level Template content  only")
    @PostMapping("/edit-content")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject editContentOnPuzzleLevelTemplate(@RequestBody PLTemplateDTO dto)  {
        PLTemplate savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

       if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plTemplateService.editContent(dto);
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Template content only Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
    }
    @Operation( summary = "edit a puzzle level Template except content   ",  description = "edit a puzzle level Template except content  only")
    @PostMapping("/no-edit-content")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject noEditContentOnPuzzleLevelTemplate(@RequestBody PLTemplateDTO dto)  {
        PLTemplate savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plTemplateService.noEditContent(dto);
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Template updated Successfully except content!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
    }
    @Operation( summary = "delete a  Puzzle Level  Template",  description = "delete a Puzzle Level template")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleLevelTemplateById(@PathVariable Long id) {
        Optional<PLTemplate> existingRecord = plTemplateService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plTemplateService.delete(existingRecord.get());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PuzzleLevel.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Puzzle Level template deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


}
