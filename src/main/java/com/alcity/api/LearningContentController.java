package com.alcity.api;

import com.alcity.entity.journey.Journey;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.learning.LearningContentService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "Learning Content  API's", description = "get Detail for learning content ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/lc")  // learning content
public class LearningContentController {

    @Autowired
    private LearningContentService learningContentService;
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<LearningContentDTO> getLearningContents(Model model) {
        Collection<LearningContent> learningTopicCollection = learningContentService.findAll();
        Collection<LearningContentDTO>  dtos= DTOUtil.getLearningContentDTOS(learningTopicCollection);
        return dtos;
    }

    @RequestMapping(value="/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public LearningContentDTO getLearningContentById(@PathVariable Long id) {
        Optional<LearningContent> learningContentOptional = learningContentService.findById(id);
        LearningContentDTO  learningContentDTO= DTOUtil.getLearningContentDTO(learningContentOptional.get());
        return learningContentDTO;
    }
    @Operation( summary = "Save a Learning Content ",  description = "Save a Learning Content")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveLearningContent(@RequestBody LearningContentDTO dto)  {
        LearningContent savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = learningContentService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + LearningContent.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = learningContentService.save(dto, "Edit");
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
    @Operation( summary = "delete a  Learning Content",  description = "delete a Learning Content entity and their data to data base")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteLearningContentById(@PathVariable Long id) {
        Optional<LearningContent> existingRecord = learningContentService.findById(id);
        if(existingRecord.isPresent()){
            try {
                learningContentService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", LearningContent.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


}
