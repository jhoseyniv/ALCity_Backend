package com.alcity.api;

import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PGService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Al City Object used in different puzzle groups", description = "this part get actions and objects in a puzzle groups api")
@RestController
@RequestMapping("/pg")

public class PGController {
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PGService pgService;

    @Operation( summary = "Fetch all AL City Object for that define in a puzzle group ",  description = "Fetch all Al city object for an puzzle group")
    @RequestMapping(value = "/id/{id}/objects", method = RequestMethod.GET)
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


    @GetMapping("/all")
    public Collection<PGDTO> getPuzzleGroups(Model model) {
        Collection<PuzzleGroup> puzzleGroupCollection = pgService.findAll();
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();
        puzzleGroupDTOCollection = DTOUtil.getPuzzleGroupDTOS(puzzleGroupCollection);
        return puzzleGroupDTOCollection;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PGDTO getPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        PGDTO puzzleGroupDTO = new PGDTO();
        if(puzzleGroup.isPresent())  puzzleGroupDTO = DTOUtil.getPuzzleGroupDTO(puzzleGroup.get());
        return  puzzleGroupDTO;
    }

    @RequestMapping(value = "/id/{id}/skills/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<LearningSkillLContentDTO> getLearningSKillsForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);

        Collection<LearningSkillLContentDTO> learningSkillLContentDTOS = new ArrayList<LearningSkillLContentDTO>();
        if(puzzleGroup.isPresent())  learningSkillLContentDTOS = DTOUtil.getLearningSkillContentDTOS(puzzleGroup.get().getLearningSkillContents());
        return  learningSkillLContentDTOS;
    }




}
