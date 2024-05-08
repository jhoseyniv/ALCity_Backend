package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PGService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/pg")

public class PGController {
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PGService puzzleGroupService;



    @GetMapping("/all")
    public Collection<PGDTO> getPuzzleGroups(Model model) {
        Collection<PuzzleGroup> puzzleGroupCollection = puzzleGroupService.findAll();
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();
        Iterator<PuzzleGroup> puzzleGroupIterator = puzzleGroupCollection.iterator();
        while (puzzleGroupIterator.hasNext()) {
            PGDTO puzzleGroupDTO = new PGDTO();
            PuzzleGroup puzzleGroup = puzzleGroupIterator.next();
            puzzleGroupDTO.setId(puzzleGroup.getId());
            puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
            BinaryContent icon = puzzleGroup.getIcon();
            BinaryContent pic = puzzleGroup.getPic();

            BinaryContentDTO iconDTO =DTOUtil.getBinaryContentDTO(icon);
            BinaryContentDTO picDTO =DTOUtil.getBinaryContentDTO(pic);

            puzzleGroupDTO.setIcon(iconDTO);
            puzzleGroupDTO.setPic(picDTO);
            puzzleGroupDTOCollection.add(puzzleGroupDTO);
        }

        return puzzleGroupDTOCollection;
    }
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PGDTO getPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = puzzleGroupService.findById(id);
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

            Collection<ALCityObjectInPG>  puzzleGroup_puzzleObjectCollection = puzzleGroup.get().getPuzzleGroup_puzzleObjectCollection();
            Collection<PuzzleGroup_PuzzleObjectDTO> puzzleGroup_puzzleObjectDTOCollection = new ArrayList<PuzzleGroup_PuzzleObjectDTO>();
            puzzleGroup_puzzleObjectDTOCollection = DTOUtil.getPuzzleGroup_PuzzleObjectDTOS(puzzleGroup_puzzleObjectCollection);


            puzzleGroupDTO.setJourneyStepDTOCollection(journeyStepDTOCollection);
            puzzleGroupDTO.setPuzzleLevelDTOCollection(puzzleLevelDTOCollection);
            puzzleGroupDTO.setPuzzleSkillLearningContentDTOCollection(puzzleSkillLearningContentDTOCollection);
            puzzleGroupDTO.setPuzzleGroup_puzzleObjectDTOCollection(puzzleGroup_puzzleObjectDTOCollection);
         }

        return  puzzleGroupDTO;
    }




}
