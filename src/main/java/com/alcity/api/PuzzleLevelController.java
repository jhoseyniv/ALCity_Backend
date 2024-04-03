package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PuzzleGroupService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/p-level")
public class PuzzleLevelController {


    @Autowired
    private PuzzleLevelService puzzleLevelService;


    @GetMapping("/all/")
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

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleLevelDTO getPuzzleLevelById(@PathVariable Long id) {
        PuzzleLevelDTO puzzleLevelDTO= new PuzzleLevelDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PuzzleLevel puzzleLevel = new PuzzleLevel();

        if(puzzleLevelOptional.isPresent()){
            puzzleLevel = puzzleLevelOptional.get();

            Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection = new ArrayList<PuzzleLevelObjectiveDTO>();
            Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection = new ArrayList<PuzzleLevel_LearningTopicDTO>();

            Collection<PuzzleLevelObjective> puzzleLevelObjectiveCollection = puzzleLevel.getPuzzleLevelObjectiveCollection();
            Collection<PuzzleLevel_LearningTopic> puzzleLevel_learningTopicCollection = puzzleLevel.getPuzzleLevel_learningTopics();

            Iterator<PuzzleLevelObjective> itr_objectives = puzzleLevelObjectiveCollection.iterator();
            Iterator<PuzzleLevel_LearningTopic> itr_learningTopics = puzzleLevel_learningTopicCollection.iterator();

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

            while(itr_objectives.hasNext()){
                PuzzleLevelObjective puzzleLevelObjective = itr_objectives.next();
                PuzzleLevelObjectiveDTO puzzleLevelObjectiveDTO = new PuzzleLevelObjectiveDTO();
                puzzleLevelObjectiveDTO.setId(puzzleLevelObjective.getId());
                puzzleLevelObjectiveDTO.setVersion(puzzleLevelObjective.getVersion());
                puzzleLevelObjectiveDTO.setTitle(puzzleLevelObjective.getTitle());
                puzzleLevelObjectiveDTO.setCondition(puzzleLevelObjective.getCondition());
                puzzleLevelObjectiveDTO.setRewardAmount(puzzleLevelObjective.getRewardAmount());
                puzzleLevelObjectiveDTO.setSkillAmount(puzzleLevelObjective.getSkillAmount());
                puzzleLevelObjectiveDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevelObjective.getUpdated()));
                puzzleLevelObjectiveDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevelObjective.getCreated()));

                puzzleLevelObjectiveDTOCollection.add(puzzleLevelObjectiveDTO);
            }
            while(itr_learningTopics.hasNext()) {
                PuzzleLevel_LearningTopic puzzleLevel_learningTopic = itr_learningTopics.next();
                PuzzleLevel_LearningTopicDTO puzzleLevel_learningTopicDTO = new PuzzleLevel_LearningTopicDTO();
                LearningTopicDTO learningTopicDTO = new LearningTopicDTO();
                LearningContentDTO learningContentDTO = new LearningContentDTO();

                puzzleLevel_learningTopicDTO.setId(puzzleLevel_learningTopic.getId());
                puzzleLevel_learningTopicDTO.setVersion(puzzleLevel_learningTopic.getVersion());
                puzzleLevel_learningTopicDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getUpdated()));
                puzzleLevel_learningTopicDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getCreated()));

                learningTopicDTO.setId(puzzleLevel_learningTopic.getLearningTopic().getId());
                learningTopicDTO.setTitle(puzzleLevel_learningTopic.getLearningTopic().getTitle());
                learningTopicDTO.setVersion(puzzleLevel_learningTopic.getLearningTopic().getVersion());
                learningTopicDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningTopic().getCreated()));
                learningTopicDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningTopic().getUpdated()));
                puzzleLevel_learningTopicDTO.setLearningTopicDTO(learningTopicDTO);

                learningContentDTO.setId(puzzleLevel_learningTopic.getLearningContent().getId());
                learningContentDTO.setVersion(puzzleLevel_learningTopic.getLearningContent().getVersion());
                learningContentDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningContent().getCreated()));
                learningContentDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningContent().getUpdated()));

                learningContentDTO.setDescText(puzzleLevel_learningTopic.getLearningContent().getDescText());
                learningContentDTO.setDescBrief(puzzleLevel_learningTopic.getLearningContent().getDescBrief());

                puzzleLevel_learningTopicDTO.setLearningContentDTO(learningContentDTO);


                puzzleLevel_learningTopicDTOCollection.add(puzzleLevel_learningTopicDTO);
            }

            puzzleLevelDTO.setPuzzleLevelObjectiveDTOCollection(puzzleLevelObjectiveDTOCollection);
            puzzleLevelDTO.setPuzzleLevel_learningTopicDTOCollection(puzzleLevel_learningTopicDTOCollection);

        }


        return puzzleLevelDTO;
    }
}
