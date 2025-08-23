package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.puzzle.PGLearningSkillDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PGLearningSkill;
import com.alcity.entity.puzzle.PGObject;
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
    private PGLearningSkillService skillService;
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
    public ResponseMessage savePGLearningSkillContent(@RequestBody PGLearningSkillDTO dto)  {
        PGLearningSkill savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PGLearningSkill> pgLearningSkillOptional = skillService.findById(dto.getId());
        try{
            if (pgLearningSkillOptional.isEmpty())
                savedRecord = skillService.save(dto,"Save");
            else
                savedRecord = skillService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,PGLearningSkill.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PGLearningSkill.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),PGLearningSkill.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }


    @Operation( summary = "Delete a  PG Skill learning Content ",  description = "Delete a  PG Skill learning Content")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteAPuzzleLevelLearningTopicById(@PathVariable Long id) {
        Optional<PGLearningSkill> requestedRecord = skillService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                skillService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), PGLearningSkill.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PGLearningSkill.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), PGLearningSkill.class.getSimpleName(),  id,SystemMessage.RecordNotFound);

      }


}
