package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.dto.puzzle.PuzzleGroupDTO;
import com.alcity.dto.puzzle.PuzzleLevelDTO;
import com.alcity.dto.puzzle.PuzzleSkillLearningContentDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleSkillLearningContent;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PuzzleGroupService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PuzzleGroupService puzzleGroupService;

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @GetMapping("/categories")
    public Collection<PuzzleCategoryDTO> getPuzzleCategories(Model model) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOCollection = new ArrayList<PuzzleCategoryDTO>();

        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        Iterator<PuzzleCategory> itr = puzzleCategoryCollection.iterator();
        while(itr.hasNext()) {
            Collection<PuzzleGroupDTO> puzzleGroupDTOCollection = new ArrayList<PuzzleGroupDTO>();
            PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO.setId(puzzleCategory.getId());
            puzzleCategoryDTO.setLabel(puzzleCategory.getLabel());
            puzzleCategoryDTO.setValue(puzzleCategory.getValue());
            puzzleCategoryDTO.setVersion(puzzleCategory.getVersion());
            puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.getCreated()));
            puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.getUpdated()));
            Collection<PuzzleGroup>  puzzleGroupSet = puzzleCategory.getPuzzleGroupSet();

            Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
            while(itrPuzzleGroupSet.hasNext()){
                PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
                PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                puzzleGroupDTO.setId(puzzleGroup.getId());
                puzzleGroupDTOCollection.add(puzzleGroupDTO);
            }
            puzzleCategoryDTO.setPuzzleGroupDTOSet(puzzleGroupDTOCollection);
            puzzleCategoryDTOCollection.add(puzzleCategoryDTO);
        }

     return puzzleCategoryDTOCollection;
    }


    @RequestMapping(value = "/category/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleCategoryDTO getPuzzleCategoryById(@PathVariable Long id) {
            Optional<PuzzleCategory> puzzleCategory = puzzleCategoryService.findById(id);
            Collection<PuzzleGroupDTO> puzzleGroupDTOCollection = new ArrayList<>();
            PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();

            if(puzzleCategory.isPresent()) {
                puzzleCategoryDTO.setId(puzzleCategory.get().getId());
                puzzleCategoryDTO.setLabel(puzzleCategory.get().getLabel());
                puzzleCategoryDTO.setValue(puzzleCategory.get().getValue());
                puzzleCategoryDTO.setVersion(puzzleCategory.get().getVersion());
                puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.get().getCreated()));
                puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.get().getUpdated()));
                Collection<PuzzleGroup> puzzleGroupSet = puzzleCategory.get().getPuzzleGroupSet();

                Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
                while (itrPuzzleGroupSet.hasNext()) {
                    PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
                    PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                    puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                    puzzleGroupDTO.setId(puzzleGroup.getId());
                    puzzleGroupDTOCollection.add(puzzleGroupDTO);
                }
                puzzleCategoryDTO.setPuzzleGroupDTOSet(puzzleGroupDTOCollection);
            }

        return puzzleCategoryDTO;
    }

    @GetMapping("/groups/")
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
    @RequestMapping(value = "/group/id/{id}", method = RequestMethod.GET)
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
            }
            puzzleGroupDTO.setJourneyStepDTOCollection(journeyStepDTOCollection);
            puzzleGroupDTO.setPuzzleLevelDTOCollection(puzzleLevelDTOCollection);
            puzzleGroupDTO.setPuzzleSkillLearningContentDTOCollection(puzzleSkillLearningContentDTOCollection);
        }

        return  puzzleGroupDTO;
    }
    @GetMapping("/levels/")
    public Collection<PuzzleLevelDTO> getPuzzleLevels(Model model) {
        Collection<PuzzleLevelDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelDTO>();

        Collection<PuzzleLevel> puzzleLevelCollection = puzzleLevelService.findAll();
        Iterator<PuzzleLevel> itr = puzzleLevelCollection.iterator();
        while(itr.hasNext()){
            PuzzleLevel puzzleLevel = itr.next();
            PuzzleLevelDTO puzzleLevelDTO = new PuzzleLevelDTO();
            puzzleLevelDTO.setId(puzzleLevel.getId());
            puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
            puzzleLevelDTO.setCode(puzzleLevel.getCode());
            puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
            puzzleLevelDTO.setTitle(puzzleLevel.getTitle());
            puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
            puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
            puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
            puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
            puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
            puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));

            puzzleLevelDTOCollection.add(puzzleLevelDTO);

        }
        return puzzleLevelDTOCollection;
    }
}
