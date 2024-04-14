package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.player.PermitedPlayerDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.*;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PuzzleGroupService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pl")
public class PuzzleLevelController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @GetMapping("/all")
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
            puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevel);

            Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevel);
            Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection = DTOUtil.getPuzzleLevel_LearningTopicDTOS(puzzleLevel);
            Collection<PuzzleLevelGroundDTO> puzzleLevelGroundDTOCollection = DTOUtil.getPuzzleLevelGroundDTOS(puzzleLevel);
            Collection<PermitedPlayerDTO> permitedPlayerDTOCollection = DTOUtil.getPermitedPlayerDTOS(puzzleLevel);
            Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection = DTOUtil.getPuzzleGroupObjectInstance(puzzleLevel);

            puzzleLevelDTO.setPuzzleLevelGroundDTOCollection(puzzleLevelGroundDTOCollection);
            puzzleLevelDTO.setPuzzleLevelObjectiveDTOCollection(puzzleLevelObjectiveDTOCollection);
            puzzleLevelDTO.setPuzzleLevel_learningTopicDTOCollection(puzzleLevel_learningTopicDTOCollection);
            puzzleLevelDTO.setPermitedPlayerDTOCollection(permitedPlayerDTOCollection);
            puzzleLevelDTO.setPuzzleGroupObjectInstanceDTOCollection(puzzleGroupObjectInstanceDTOCollection);
        }

        return puzzleLevelDTO;
    }
//    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public PuzzleLevelDTO getPuzzleLevelForInterpreter(@PathVariable Long id) {
//
//    }


    }
