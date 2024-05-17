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
    @RequestMapping(value = "/id/{id}/obj/all", method = RequestMethod.GET)
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
        if(puzzleGroup.isPresent()){

           puzzleGroupDTO = DTOUtil.getPuzzleGroupDTO(puzzleGroup.get());
           Collection<JourneyStep> journeyStepCollection = puzzleGroup.get().getJourneyStepCollection();
           Collection<JourneyStepDTO> journeyStepDTOCollection = new ArrayList<JourneyStepDTO>();
           journeyStepDTOCollection = DTOUtil.getJorenyStepsDTOS(journeyStepCollection);

            Collection<PuzzleLevel> puzzleLevelCollection = puzzleGroup.get().getPuzzleLevelSet();
            Collection<PuzzleLevelLDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelLDTO>();
            puzzleLevelDTOCollection = DTOUtil.getPuzzleLevelDTOS(puzzleLevelCollection);

            Collection<PuzzleSkillLearningContent> puzzleSkillLearningContentCollection = puzzleGroup.get().getPuzzleSkillLearningContentSet();
            Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection = new ArrayList<PuzzleSkillLearningContentDTO>();
            puzzleSkillLearningContentDTOCollection = DTOUtil.getPuzzleSkillLearningContentDTOS(puzzleSkillLearningContentCollection);

            Collection<ALCityObjectInPG>  puzzleGroup_puzzleObjectCollection = puzzleGroup.get().getAlCityObjectInPGS();
            Collection<ALCityObjectInPGDTO> puzzleGroup_puzzleObjectDTOCollection = new ArrayList<ALCityObjectInPGDTO>();
            puzzleGroup_puzzleObjectDTOCollection = DTOUtil.getALCityObjectInPGDTOS(puzzleGroup_puzzleObjectCollection);


            puzzleGroupDTO.setJourneyStepDTOCollection(journeyStepDTOCollection);
            puzzleGroupDTO.setPuzzleLevelDTOCollection(puzzleLevelDTOCollection);
            puzzleGroupDTO.setPuzzleSkillLearningContentDTOCollection(puzzleSkillLearningContentDTOCollection);
            puzzleGroupDTO.setPuzzleGroup_puzzleObjectDTOCollection(puzzleGroup_puzzleObjectDTOCollection);
         }

        return  puzzleGroupDTO;
    }




}
