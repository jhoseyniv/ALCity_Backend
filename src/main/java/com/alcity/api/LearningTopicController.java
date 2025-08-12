package com.alcity.api;


import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Tag(name = "Learning Topic APIs", description = "Learning Topic ... ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/learning-topic")

public class LearningTopicController {
    @Autowired
    private LearningTopicService learningTopicService;

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<LearningTopicDTO> getLearningTopics(Model model) {
        Collection<LearningTopicDTO> learningTopicDTOS = new ArrayList<LearningTopicDTO>();
        Collection<LearningTopic> learningTopics = learningTopicService.findAll();
        Iterator<LearningTopic> iterator = learningTopics.iterator();
        while(iterator.hasNext()){
            LearningTopicDTO learningTopicDTO = DTOUtil.getLearningTopicDTO(iterator.next());
            learningTopicDTOS.add(learningTopicDTO);
        }
        return learningTopicDTOS;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Optional<LearningTopic> getLearningTopicById(@PathVariable Long id) {
        Optional<LearningTopic> learningTopic = learningTopicService.findById(id);
        return learningTopic;
    }
    @Operation( summary = "Save a Learning Topic ",  description = "Save a Learning Topic ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseObject saveLearningTopic(@RequestBody LearningTopicDTO dto)  {
        LearningTopic savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = learningTopicService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + LearningTopic.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = learningTopicService.save(dto, "Edit");
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
    @Operation( summary = "delete a  Object category entity",  description = "delete an object category entity and their data to data base")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteObjectCategoryById(@PathVariable Long id) {
        Optional<LearningTopic> existingRecord = learningTopicService.findById(id);
        if(existingRecord.isPresent()){
            try {
                learningTopicService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", LearningTopic.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);
    }

}
