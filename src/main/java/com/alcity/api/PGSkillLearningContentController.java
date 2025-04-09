package com.alcity.api;

import com.alcity.dto.puzzle.PGLearningSkillContentDTO;
import com.alcity.entity.puzzle.LearningTopicInPL;
import com.alcity.entity.puzzle.PGLearningSkillContent;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PGSkillLearningContentService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Skill Learning Content ", description = "Puzzle Skill Learning Content ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pg-skill-learning-content")

public class PGSkillLearningContentController {

    @Autowired
    private PGSkillLearningContentService pgSkillLearningContentService;
    @Autowired
    private PGService pgService;

    @Operation( summary = "Fetch all Puzzle Skill Learning Content for a puzzle group by  Id ",  description = "Puzzle Skill Learning Content for a puzzle group by  Id")
    @RequestMapping(value = "/id/{id}/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PGLearningSkillContentDTO> getAllPuzzleSkillLearningForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(id);
        if(puzzleGroupOptional.isEmpty()) return  null;
        PuzzleGroup puzzleGroup = puzzleGroupOptional.get();
        Collection<PGLearningSkillContentDTO> dtos = new ArrayList<PGLearningSkillContentDTO>();
        dtos = DTOUtil.getPGLearningSkillContentDTOS(puzzleGroup.getLearningSkillContents());
        return  dtos;
    }
    @Operation( summary = "add a  learning skill  to puzzle group ",  description = "add a  learning skill  to puzzle group")
    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePGLearningSkillContent(@RequestBody PGLearningSkillContentDTO dto)  {
        PGLearningSkillContent savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = pgSkillLearningContentService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getLearningSkillTitle(), dto.getId(), "Code Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord =  null; //puzzleLevelService.save(dto, "Edit");
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
    @Operation( summary = "Delete a  PG Skill learning Content ",  description = "Delete a  PG Skill learning Content")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteAPuzzleLevelLearningTopicById(@PathVariable Long id) {
        Optional<PGLearningSkillContent> existingRecord = pgSkillLearningContentService.findById(id);
        if(existingRecord.isPresent()){
            try {
                pgSkillLearningContentService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getLearningSkill().getValue(), existingRecord.get().getId(), PGLearningSkillContent.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


}
