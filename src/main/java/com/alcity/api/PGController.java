package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PGService;
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

@Tag(name = "Al City Object used in different puzzle groups", description = "this part get actions and objects in a puzzle groups api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pg")

public class PGController {
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PGService pgService;

    @Operation( summary = "Fetch all AL City Object for that define in a puzzle group ",  description = "Fetch all Al city object for an puzzle group")
    @RequestMapping(value = "/id/{id}/objects/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<ALCityObjectInPGDTO> getObjectsForAPG(@PathVariable Long id) {
        Collection<ALCityObjectInPGDTO> alCityObjectInPGDTOS = new ArrayList<ALCityObjectInPGDTO>();
        Collection<ALCityObjectInPG> alCityObjectInPGS = new ArrayList<ALCityObjectInPG>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            alCityObjectInPGS = puzzleGroup.get().getAlCityObjectInPGS();
            alCityObjectInPGDTOS = DTOUtil.getALCityObjectInPGDTOS(alCityObjectInPGS);
        }
        return  alCityObjectInPGDTOS;
    }

    @Operation( summary = "Fetch all Puzzle Levels that define in a puzzle group ",  description = "Fetch all puzzle level for a puzzle group")
    @RequestMapping(value = "/id/{id}/pl", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleLevelLDTO> getPuzzleLevelForAPG(@PathVariable Long id) {
        Collection<PuzzleLevelLDTO> puzzleLevelLDTOS = new ArrayList<PuzzleLevelLDTO>();
        Collection<PuzzleLevel> puzzleLevels = new ArrayList<PuzzleLevel>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            puzzleLevels = puzzleGroup.get().getPuzzleLevels();
            puzzleLevelLDTOS = DTOUtil.getPuzzleLevelDTOS(puzzleLevels);
        }
        return  puzzleLevelLDTOS;
    }


    @Operation( summary = "get all Puzzle Groups ",  description = "get all Puzzle Groups")
    @GetMapping("/all")
    @CrossOrigin
    public Collection<PGDTO> getPuzzleGroups(Model model) {
        Collection<PuzzleGroup> puzzleGroupCollection = pgService.findAll();
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();
        puzzleGroupDTOCollection = DTOUtil.getPuzzleGroupDTOS(puzzleGroupCollection);
        return puzzleGroupDTOCollection;
    }

    @Operation( summary = "get all learning skill for  a Puzzle Group ",  description = "get all learning skill for  a Puzzle Group")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PGDTO getPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        PGDTO puzzleGroupDTO = new PGDTO();
        if(puzzleGroup.isPresent())  puzzleGroupDTO = DTOUtil.getPuzzleGroupDTO(puzzleGroup.get());
        return  puzzleGroupDTO;
    }

    @Operation( summary = "get all learning skill for  a Puzzle Group ",  description = "get all learning skill for  a Puzzle Group")
    @RequestMapping(value = "/id/{id}/skills/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<LearningSkillLContentDTO> getLearningSKillsForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);

        Collection<LearningSkillLContentDTO> learningSkillLContentDTOS = new ArrayList<LearningSkillLContentDTO>();
        if(puzzleGroup.isPresent())  learningSkillLContentDTOS = DTOUtil.getLearningSkillContentDTOS(puzzleGroup.get().getLearningSkillContents());
        return  learningSkillLContentDTOS;
    }

    @Operation( summary = "Save a Puzzle Group ",  description = "save a Puzzle Group entity to database")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleGroup(@RequestBody PGDTO dto) {
        PuzzleGroup savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = pgService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getTitle(), dto.getId(), PuzzleCategory.class.toString());
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = pgService.save(dto, "Edit");
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
