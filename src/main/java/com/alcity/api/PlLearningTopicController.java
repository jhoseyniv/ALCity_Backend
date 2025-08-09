package com.alcity.api;

import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.puzzle.PLLearningTopic;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.puzzle.PLLearningTopicService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ALCityResponseObject savePLLearningTopic(@RequestBody PlLearningTopicDTO dto)  {
        PLLearningTopic savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plLearningTopicService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLLearningTopic.class , "Error",savedRecord.getId() );
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
    @Operation( summary = "delete a  Puzzle Level learning Topic ",  description = "delete a Puzzle Level learning Topic")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteAPuzzleLevelLearningTopicById(@PathVariable Long id) {
        Optional<PLLearningTopic> existingRecord = plLearningTopicService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plLearningTopicService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLLearningTopic.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

}
