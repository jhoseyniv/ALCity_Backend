package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PuzzleGroupService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
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
    private PuzzleGroupService puzzleGroupService;



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
            BinaryContent binaryContent_icon = puzzleGroup.getIcon();
            BinaryContent binaryContent_pic = puzzleGroup.getPic();

            BinaryContentDTO binaryContentDTO_icon = new BinaryContentDTO(binaryContent_icon.getFileName(),binaryContent_icon.getSize(),binaryContent_icon.getContent(),binaryContent_icon.getId(),binaryContent_icon.getVersion()
                    ,DateUtils.getDatatimeFromLong(binaryContent_icon.getCreated()),DateUtils.getDatatimeFromLong(binaryContent_icon.getUpdated()));
            BinaryContentDTO binaryContentDTO_pic = new BinaryContentDTO(binaryContent_pic.getFileName(),binaryContent_pic.getSize(),binaryContent_pic.getContent(),binaryContent_pic.getId(),binaryContent_pic.getVersion()
                    ,DateUtils.getDatatimeFromLong(binaryContent_pic.getCreated()),DateUtils.getDatatimeFromLong(binaryContent_pic.getUpdated()));
            puzzleGroupDTO.setIcon(binaryContentDTO_icon);
            puzzleGroupDTO.setPic(binaryContentDTO_pic);
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
           Collection<JourneyStep> journeyStepCollection = puzzleGroup.get().getJourneyStepSet();
           Collection<JourneyStepDTO> journeyStepDTOCollection = new ArrayList<JourneyStepDTO>();
           journeyStepDTOCollection = DTOUtil.getJorenyStepsDTOS(journeyStepCollection);

            Collection<PuzzleLevel> puzzleLevelCollection = puzzleGroup.get().getPuzzleLevelSet();
            Collection<PLDTO> puzzleLevelDTOCollection = new ArrayList<PLDTO>();
            puzzleLevelDTOCollection = DTOUtil.getPuzzleLevelDTOS(puzzleLevelCollection);

            Collection<PuzzleSkillLearningContent> puzzleSkillLearningContentCollection = puzzleGroup.get().getPuzzleSkillLearningContentSet();
            Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection = new ArrayList<PuzzleSkillLearningContentDTO>();
            puzzleSkillLearningContentDTOCollection = DTOUtil.getPuzzleSkillLearningContentDTOS(puzzleSkillLearningContentCollection);

            Collection<PuzzleGroup_PuzzleObject>  puzzleGroup_puzzleObjectCollection = puzzleGroup.get().getPuzzleGroup_puzzleObjectCollection();
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
