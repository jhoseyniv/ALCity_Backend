package com.alcity.api;

import com.alcity.dto.puzzle.PGLearningSkillDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PGLearningSkill;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PGLearningSkillService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Skill Learning Content ", description = "Puzzle Skill Learning Content ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pg-skill-learning-content")

public class PGSkillLearningController {

    @Autowired
    private PGLearningSkillService pgSkillLearningContentService;
    @Autowired
    private PGService pgService;

    @Operation( summary = "Fetch all Puzzle Skill Learning Content for a puzzle group by  Id ",  description = "Puzzle Skill Learning Content for a puzzle group by  Id")
    @RequestMapping(value = "/id/{id}/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PGLearningSkillDTO> getAllPuzzleSkillLearningForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(id);
        if(puzzleGroupOptional.isEmpty()) return  null;
        PuzzleGroup puzzleGroup = puzzleGroupOptional.get();
        Collection<PGLearningSkillDTO> dtos = new ArrayList<PGLearningSkillDTO>();
        dtos = DTOUtil.getPGLearningSkillContentDTOS(puzzleGroup.getLearningSkillContents());
        return  dtos;
    }
    @Operation( summary = "add a  learning skill  to puzzle group ",  description = "add a  learning skill  to puzzle group")
    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseObject savePGLearningSkillContent(@RequestBody PGLearningSkillDTO dto)  {
        PGLearningSkill savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = pgSkillLearningContentService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PGLearningSkill.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord =  null; //puzzleLevelService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "Delete a  PG Skill learning Content ",  description = "Delete a  PG Skill learning Content")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteAPuzzleLevelLearningTopicById(@PathVariable Long id) {
        Optional<PGLearningSkill> existingRecord = pgSkillLearningContentService.findById(id);
        if(existingRecord.isPresent()){
            try {
                pgSkillLearningContentService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PGLearningSkill.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), ActionStatus.Error, existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }


}
