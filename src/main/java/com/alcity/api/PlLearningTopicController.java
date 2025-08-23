package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.puzzle.PGLearningSkill;
import com.alcity.entity.puzzle.PLLearningTopic;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.puzzle.PLLearningTopicService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Level Learning Topic ", description = "Get Puzzle Levels learning topics...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pl-learning-topic")
public class PlLearningTopicController {


    @Autowired
    PLLearningTopicService plLearningTopicService;

    @Autowired
    private PuzzleLevelService puzzleLevelService;


    @Operation( summary = "Fetch all learning topics for a puzzle level by  Id ",  description = "Fetch all variables for a puzzle level by  Id")
    @RequestMapping(value = "/id/{id}/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PlLearningTopicDTO> getAllLearningTopicsForPuzzleLevelById(@PathVariable Long id) {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isEmpty()) return  null;
        PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
        Collection<PlLearningTopicDTO>  plLearningTopicDTOS = DTOUtil.getPl_LearningTopicDTOS(puzzleLevel);
        return  plLearningTopicDTOS;
    }

    @Operation( summary = "add a  learning topic to puzzle level ",  description = "Save a puzzle level learning topic entity and their data to data base")
    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePLLearningTopic(@RequestBody PlLearningTopicDTO dto)  {
        PLLearningTopic savedRecord = null;
        ResponseMessage response = new ResponseMessage();

        Optional<PLLearningTopic> plLearningTopicOptional = plLearningTopicService.findById(dto.getId());
        try{
            if (plLearningTopicOptional.isEmpty())
                savedRecord = plLearningTopicService.save(dto,"Save");
            else
                savedRecord = plLearningTopicService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,PLLearningTopic.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PLLearningTopic.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),PLLearningTopic.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }

    @Operation( summary = "Delete a  Puzzle Level learning Topic ",  description = "delete a Puzzle Level learning Topic")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteAPuzzleLevelLearningTopicById(@PathVariable Long id) {
        Optional<PLLearningTopic> requestedRecord = plLearningTopicService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                plLearningTopicService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), PLLearningTopic.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PLLearningTopic.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), PLLearningTopic.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

}
