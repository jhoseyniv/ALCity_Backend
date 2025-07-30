package com.alcity.api;

import com.alcity.dto.pgimport.PGImportDTO;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PLTemplateService;
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

@Tag(name = "Puzzle Group API list", description = "puzzle groups api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pg")

public class PGController {

    @Autowired
    private PGService pgService;
    @Autowired
    private PLTemplateService plTemplateService;

    @Operation( summary = "Fetch all AL City Object for that define in a puzzle group ",  description = "Fetch all Al city object for an puzzle group")
    @RequestMapping(value = "/id/{id}/objects/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<CityObjectInPGDTO> getObjectsForAPG(@PathVariable Long id) {
        Collection<CityObjectInPGDTO> alCityObjectInPGDTOS = new ArrayList<CityObjectInPGDTO>();
        Collection<ALCityObjectInPG> alCityObjectInPGS = new ArrayList<ALCityObjectInPG>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            alCityObjectInPGS = puzzleGroup.get().getAlCityObjectInPGS();
            alCityObjectInPGDTOS = DTOUtil.getALCityObjectInPGDTOS(alCityObjectInPGS);
        }
        return  alCityObjectInPGDTOS;
    }
    @Operation( summary = "Fetch all Journey Step that define for a puzzle group ",  description = "Fetch all Journey Step that define for a puzzle group")
    @RequestMapping(value = "/id/{id}/journey-step/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<JourneyStepDTO> getJourneyStepsForAPG(@PathVariable Long id) {
        Collection<JourneyStepDTO> journeyStepDTOS = new ArrayList<JourneyStepDTO>();
        Collection<JourneyStep> journeySteps = new ArrayList<JourneyStep>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            journeySteps = puzzleGroup.get().getJourneyStepCollection();
            journeyStepDTOS = DTOUtil.getJorneyStepsDTOS(journeySteps);
        }
        return  journeyStepDTOS;
    }

    @Operation( summary = "Fetch all Puzzle Levels that define in a puzzle group ",  description = "Fetch all puzzle level for a puzzle group")
    @RequestMapping(value = "/id/{id}/pl/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PLDTO> getPuzzleLevelForAPG(@PathVariable Long id) {
        Collection<PLDTO> puzzleLevelLDTOS = new ArrayList<PLDTO>();
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

    @Operation( summary = "get all learning skill content for  a Puzzle Group ",  description = "get all learning skill for  a Puzzle Group")
    @RequestMapping(value = "/id/{id}/skills/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PGLearningSkillContentDTO> getLearningSKillsForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        Collection<PGLearningSkillContentDTO> dtos = new ArrayList<PGLearningSkillContentDTO>();
        if(puzzleGroup.isPresent())  dtos = DTOUtil.getPGLearningSkillContentDTOS(puzzleGroup.get().getLearningSkillContents());
        return  dtos;
    }

    @Operation( summary = "Import a Puzzle Group ",  description = "Import a Puzzle Group entity to database")
    @PostMapping("/import")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject importPuzzleGroup(@RequestBody PGImportDTO dto) throws UniqueConstraintException {
        PuzzleGroup savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = pgService.importPG(dto);
                Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(dto.getPuzzleTemplateId());
                PLTemplate plTemplate = plTemplateOptional.get();
                plTemplate.setPuzzleGroupId(savedRecord.getId());
                plTemplateService.save(plTemplate);

            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PuzzleGroup.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        }
        return responseObject;
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
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PuzzleCategory.class , "Error",savedRecord.getId() );
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

    @Operation( summary = "Delete a  Puzzle Group ",  description = "Delete a Puzzle group entity and their data to data base")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleGroupById(@PathVariable Long id) throws Exception {
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(id);
        ALCityResponseObject responseObject=null;
        if(puzzleGroupOptional.isPresent()) {
            Collection<PuzzleLevel> puzzleLevels = puzzleGroupOptional.get().getPuzzleLevels();
            if (puzzleLevels.isEmpty()) {
                pgService.delete(puzzleGroupOptional.get());
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", id, "Record deleted Successfully!");
            } else {
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "Warning", id, "Puzzle Group has Puzzle Level , you can delete that");
            }
        }
        else {
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
            }
        return  responseObject;
    }


}
