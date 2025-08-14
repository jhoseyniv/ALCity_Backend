package com.alcity.api;

import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
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

    @Autowired
    private LearningContentService learningContentService;
    @Autowired
    private LearningTopicService learningTopicService;

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
    public ResponseObject savePLLearningTopic(@RequestBody PlLearningTopicDTO dto)  {
        PLLearningTopic savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plLearningTopicService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLLearningTopic.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord =  null; //puzzleLevelService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "delete a  Puzzle Level learning Topic ",  description = "delete a Puzzle Level learning Topic")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteAPuzzleLevelLearningTopicById(@PathVariable Long id) {
        Optional<PLLearningTopic> existingRecord = plLearningTopicService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plLearningTopicService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLLearningTopic.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.ok.name(), existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }

}
