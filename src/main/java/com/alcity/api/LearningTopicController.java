package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.BaseObject;
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
    public ResponseMessage saveLearningTopic(@RequestBody LearningTopicDTO dto)  {
        LearningTopic savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<LearningTopic> learningTopicOptional = learningTopicService.findById(dto.getId());
        try{
            if (learningTopicOptional.isEmpty())
                savedRecord = learningTopicService.save(dto,"Save");
            else
                savedRecord = learningTopicService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , LearningTopic.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), LearningTopic.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), LearningTopic.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }
    @Operation( summary = "delete a  learning topic entity",  description = "delete a learning topic entity and their data to data base")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteLearningTopicById(@PathVariable Long id) {
        Optional<LearningTopic> requestedRecord = learningTopicService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                learningTopicService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), ObjectCategory.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), ObjectCategory.class.getSimpleName(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),ObjectCategory.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

}
