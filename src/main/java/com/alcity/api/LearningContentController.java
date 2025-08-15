package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.service.learning.LearningContentService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseMessage saveLearningContent(@RequestBody LearningContentDTO dto)  {
        LearningContent savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<LearningContent> learningContentOptional = learningContentService.findById(dto.getId());
        try{
            if (learningContentOptional.isEmpty())
                savedRecord = learningContentService.save(dto,"Save");
            else
                savedRecord = learningContentService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , LearningContent.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name() , LearningContent.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() , LearningContent.class.getSimpleName() , dto.getId(), SystemMessage.SaveOrEditMessage_Fail);
        return response;

     }

    @Operation( summary = "delete a  Learning Content",  description = "delete a Learning Content entity and their data to data base")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteLearningContentById(@PathVariable Long id) {
        Optional<LearningContent> requestedRecord = learningContentService.findById(id);
        if (requestedRecord.isPresent()) {
            try {
                learningContentService.delete(requestedRecord.get());
            } catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), LearningContent.class.getSimpleName(), id, e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), LearningContent.class.getSimpleName(), id, SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(), LearningContent.class.getSimpleName(), id, SystemMessage.RecordNotFound);
    }
}
