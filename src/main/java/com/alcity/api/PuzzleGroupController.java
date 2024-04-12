package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PuzzleGroupService;
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

public class PuzzleGroupController {
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PuzzleGroupService puzzleGroupService;



    @GetMapping("/all")
    public Collection<PuzzleGroupDTO> getPuzzleGroups(Model model) {
        Collection<PuzzleGroup> puzzleGroupCollection = puzzleGroupService.findAll();
        Collection<PuzzleGroupDTO> puzzleGroupDTOCollection = new ArrayList<PuzzleGroupDTO>();
        Iterator<PuzzleGroup> puzzleGroupIterator = puzzleGroupCollection.iterator();
        while (puzzleGroupIterator.hasNext()) {
            PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
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
    public PuzzleGroupDTO getPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = puzzleGroupService.findById(id);
        Collection<JourneyStep> journeyStepCollection = puzzleGroup.get().getJourneyStepSet();
        Collection<PuzzleLevel> puzzleLevelCollection = puzzleGroup.get().getPuzzleLevelSet();
        Collection<PuzzleSkillLearningContent> puzzleSkillLearningContentCollection = puzzleGroup.get().getPuzzleSkillLearningContentSet();

        PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
        if(puzzleGroup.isPresent()) {
            puzzleGroupDTO.setId(puzzleGroup.get().getId());
            puzzleGroupDTO.setVersion(puzzleGroup.get().getVersion());
            puzzleGroupDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleGroup.get().getCreated()));
            puzzleGroupDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleGroup.get().getUpdated()));
            puzzleGroupDTO.setTitle(puzzleGroup.get().getTitle());
            BinaryContent binaryContent_icon = puzzleGroup.get().getIcon();
            BinaryContent binaryContent_pic = puzzleGroup.get().getPic();

            BinaryContentDTO binaryContentDTO_icon = new BinaryContentDTO(binaryContent_icon.getFileName(), binaryContent_icon.getSize(), binaryContent_icon.getContent(), binaryContent_icon.getId(), binaryContent_icon.getVersion()
                    , DateUtils.getDatatimeFromLong(binaryContent_icon.getCreated()), DateUtils.getDatatimeFromLong(binaryContent_icon.getUpdated()));
            BinaryContentDTO binaryContentDTO_pic = new BinaryContentDTO(binaryContent_pic.getFileName(), binaryContent_pic.getSize(), binaryContent_pic.getContent(), binaryContent_pic.getId(), binaryContent_pic.getVersion()
                    , DateUtils.getDatatimeFromLong(binaryContent_pic.getCreated()), DateUtils.getDatatimeFromLong(binaryContent_pic.getUpdated()));
            puzzleGroupDTO.setIcon(binaryContentDTO_icon);
            puzzleGroupDTO.setPic(binaryContentDTO_pic);

            Collection<JourneyStepDTO> journeyStepDTOCollection = new ArrayList<JourneyStepDTO>();
            Iterator<JourneyStep> itr = journeyStepCollection.iterator();
            while(itr.hasNext()){
                JourneyStep journeyStep = itr.next();
                JourneyStepDTO journeyStepDTO = new JourneyStepDTO();
                journeyStepDTO.setId(journeyStep.getId());
                journeyStepDTO.setVersion(journeyStep.getVersion());
                journeyStepDTO.setOrdering(journeyStep.getOrdering());
                journeyStepDTO.setXpos(journeyStep.getXpos());
                journeyStepDTO.setYpos(journeyStep.getYpos());
                journeyStepDTO.setTitle(journeyStep.getTitle());
                journeyStepDTO.setCreated(DateUtils.getDatatimeFromLong(journeyStep.getCreated()));
                journeyStepDTO.setUpdated(DateUtils.getDatatimeFromLong(journeyStep.getUpdated()));
                //.....
                journeyStepDTOCollection.add(journeyStepDTO);
            }
            Collection<PuzzleLevelDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelDTO>();
            Iterator<PuzzleLevel> itrPuzzleLevel = puzzleLevelCollection.iterator();
            while(itrPuzzleLevel.hasNext()){
                PuzzleLevel puzzleLevel = itrPuzzleLevel.next();
                PuzzleLevelDTO puzzleLevelDTO = new PuzzleLevelDTO();
                puzzleLevelDTO.setId(puzzleLevel.getId());
                puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
                puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
                puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
                puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));
                puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
                puzzleLevelDTO.setCode(puzzleLevel.getCode());
                puzzleLevelDTO.setTitle(puzzleLevel.getTitle());
                puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
                puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
                puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
                puzzleLevelDTOCollection.add(puzzleLevelDTO);
            }
            Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection = new ArrayList<PuzzleSkillLearningContentDTO>();
            Iterator<PuzzleSkillLearningContent> itrPuzzleSkills = puzzleSkillLearningContentCollection.iterator();
            while(itrPuzzleSkills.hasNext()) {
                PuzzleSkillLearningContentDTO puzzleSkillLearningContentDTO = new PuzzleSkillLearningContentDTO();
                PuzzleSkillLearningContent puzzleSkillLearningContent = itrPuzzleSkills.next();

                puzzleSkillLearningContentDTO.setId(puzzleSkillLearningContent.getId());
                puzzleSkillLearningContentDTO.setVersion(puzzleSkillLearningContent.getVersion());
                puzzleSkillLearningContentDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleSkillLearningContent.getCreated()));
                puzzleSkillLearningContentDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleSkillLearningContent.getUpdated()));

                LearningSkill learningSkill = puzzleSkillLearningContent.getLearningSkill();
                LearningSkillDTO learningSkillDTO = new LearningSkillDTO(learningSkill.getId(),learningSkill.getLabel(), learningSkill.getValue(), learningSkill.getVersion(),
                        DateUtils.getDatatimeFromLong(learningSkill.getCreated()),DateUtils.getDatatimeFromLong(learningSkill.getUpdated()));
                puzzleSkillLearningContentDTO.setLearningSkillDTO(learningSkillDTO);

                LearningContent learningContent = puzzleSkillLearningContent.getLearningContent();
                BinaryContent binaryContent = learningContent.getBinaryContent();
                BinaryContentDTO binaryContentDTO = new BinaryContentDTO(binaryContent.getFileName(),binaryContent.getSize(),binaryContent.getContent(), binaryContent.getId(),binaryContent.getVersion(),
                        DateUtils.getDatatimeFromLong(binaryContent.getCreated()),DateUtils.getDatatimeFromLong(binaryContent.getUpdated()));

                LearningContentDTO learningContentDTO = new LearningContentDTO();
                learningContentDTO.setId(learningContent.getId());
                learningContentDTO.setVersion(learningContent.getVersion());
                learningContentDTO.setCreated(DateUtils.getDatatimeFromLong(learningContent.getCreated()));
                learningContentDTO.setUpdated(DateUtils.getDatatimeFromLong(learningContent.getUpdated()));
                learningContentDTO.setBinaryContentDTO(binaryContentDTO);
                learningContentDTO.setDescBrief(learningContent.getDescBrief());
                learningContentDTO.setDescText(learningContent.getDescText());
                puzzleSkillLearningContentDTO.setLearningContentDTO(learningContentDTO);

                puzzleSkillLearningContentDTOCollection.add(puzzleSkillLearningContentDTO);
            }
            puzzleGroupDTO.setJourneyStepDTOCollection(journeyStepDTOCollection);
            puzzleGroupDTO.setPuzzleLevelDTOCollection(puzzleLevelDTOCollection);
            puzzleGroupDTO.setPuzzleSkillLearningContentDTOCollection(puzzleSkillLearningContentDTOCollection);
        }

        return  puzzleGroupDTO;
    }
/*
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleObjectDTO> getPuzzleObjectsByPuzzleGroupId(@PathVariable Long id) {
        Collection<PuzzleObjectDTO> puzzleObjectDTOCollection = new ArrayList<>();
        Collection<PuzzleGroup_PuzzleObject> puzzleGroup_puzzleObjectCollection = new ArrayList<>();
        Optional<PuzzleGroup> puzzleGroup = puzzleGroupService.findById(id);
        if(puzzleGroup.isPresent()){
            PuzzleGroup pg = puzzleGroup.get();
            puzzleGroup_puzzleObjectCollection = pg.getPuzzleGroup_puzzleObjectCollection();
            Iterator<PuzzleGroup_PuzzleObject> iterator = puzzleGroup_puzzleObjectCollection.iterator();
            while(iterator.hasNext()){
                PuzzleGroup_PuzzleObject puzzleGroup_puzzleObject = iterator.next();
                PuzzleObjectDTO puzzleObjectDTO = new PuzzleObjectDTO();
                puzzleObjectDTO.setId(puzzleGroup_puzzleObject.getPuzzleObject().getId());
                puzzleObjectDTO.setObjectCategory(puzzleGroup_puzzleObject.getPuzzleObject().getObjectCategory().getLabel());
                puzzleObjectDTO.setTitle(puzzleGroup_puzzleObject.getTitle());
                puzzleObjectDTO.setCode(puzzleGroup_puzzleObject.getCode());
                puzzleObjectDTO.setVersion(puzzleGroup_puzzleObject.getVersion());
                puzzleObjectDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleGroup_puzzleObject.getCreated()));
                puzzleObjectDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleGroup_puzzleObject.getUpdated()));
                BinaryContent picture = puzzleGroup_puzzleObject.getPuzzleObject().getPicture();
                BinaryContentDTO pictureDTO = new BinaryContentDTO(picture.getFileName(),picture.getSize(),picture.getContent(),picture.getId(),picture.getVersion(),
                        DateUtils.getDatatimeFromLong(picture.getCreated()),
                        DateUtils.getDatatimeFromLong(picture.getUpdated()));
                puzzleObjectDTO.setPicture(pictureDTO);

                BinaryContent icon = puzzleGroup_puzzleObject.getPuzzleObject().getIcon();
                BinaryContentDTO iconDTO = new BinaryContentDTO(icon.getFileName(),icon.getSize(),icon.getContent(),icon.getId(),icon.getVersion(),
                        DateUtils.getDatatimeFromLong(icon.getCreated()),
                        DateUtils.getDatatimeFromLong(icon.getUpdated()));
                puzzleObjectDTO.setIcon(iconDTO);

                puzzleObjectDTOCollection.add(puzzleObjectDTO);
            }
        }

        return puzzleObjectDTOCollection;
    }*/
}
