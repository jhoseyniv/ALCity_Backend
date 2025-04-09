package com.alcity.api;

import com.alcity.dto.puzzle.LearningSkillLContentDTO;
import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.puzzle.PGSkillLearningContentRepository;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PGSkillLearningContentService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Skill Learning Content ", description = "Puzzle Skill Learning Content ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/ps-learning-content")

public class PuzzleSkillLearningContentController {

    @Autowired
    private PGSkillLearningContentService pgSkillLearningContentService;
    @Autowired
    private PGService pgService;

    @Operation( summary = "Fetch all Puzzle Skill Learning Content for a puzzle group by  Id ",  description = "Puzzle Skill Learning Content for a puzzle group by  Id")
    @RequestMapping(value = "/id/{id}/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<LearningSkillLContentDTO> getAllPuzzleSkillLearningForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(id);
        if(puzzleGroupOptional.isEmpty()) return  null;
        PuzzleGroup puzzleGroup = puzzleGroupOptional.get();
      //  Collection<PlLearningTopicDTO>  plLearningTopicDTOS = DTOUtil.get(puzzleLevel);
        return  null;
    }

}
