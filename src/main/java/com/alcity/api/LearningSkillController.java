package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/learn")
public class LearningSkillController {
    @Autowired
    private LearningSkillService learningSkillService;
    @GetMapping("/skill/all")
    public Collection<LearningSkillDTO> getLearningSkills(Model model) {
        Collection<LearningSkill> lsCollection = new ArrayList<>();
        Collection<LearningSkillDTO> lsDTOCollection = new ArrayList<LearningSkillDTO>();
        lsCollection = learningSkillService.findAll();
        Iterator<LearningSkill> iterator = lsCollection.iterator();
        while(iterator.hasNext()){
            LearningSkillDTO learningSkillDTO = new LearningSkillDTO();
            learningSkillDTO = DTOUtil.getLearningSkillDTO(iterator.next());
            lsDTOCollection.add(learningSkillDTO);
        }
        return lsDTOCollection;
    }

    @RequestMapping(value = "/skill/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LearningSkillDTO getLearnSkillById(@PathVariable Long id) {
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(id);
        LearningSkillDTO learningSkillDTO = new LearningSkillDTO();
        if(learningSkillOptional.isPresent())
            learningSkillDTO = DTOUtil.getLearningSkillDTO(learningSkillOptional.get());
        return learningSkillDTO;
    }
    @RequestMapping(value = "/skill/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<LearningSkillDTO> getLearningSkillByCriteria(@PathVariable String criteria) {
        Collection<LearningSkill> learningSkillCollection = learningSkillService.findByTitleContains(criteria);
        LearningSkillDTO learningSkillDTO = new LearningSkillDTO();
        Collection<LearningSkillDTO>  learningSkillDTOCollection = new ArrayList<LearningSkillDTO>();
        Iterator<LearningSkill> itr = learningSkillCollection.iterator();

        while(itr.hasNext()){
            LearningSkill learningSkill = itr.next();
            learningSkillDTO = DTOUtil.getLearningSkillDTO(learningSkill);
            learningSkillDTOCollection.add(learningSkillDTO);
        }
        return learningSkillDTOCollection;

    }


    @ExceptionHandler(UniqueConstraintException.class)
    @PostMapping("skill/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveLearningSkill(@RequestBody LearningSkillDTO dto)  {
        LearningSkill savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(dto.getId());
        try{
            if (learningSkillOptional.isEmpty())
                savedRecord = learningSkillService.save(dto,"Save");
            else
                savedRecord = learningSkillService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , LearningSkill.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name() , LearningSkill.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() , LearningSkill.class.getSimpleName() , dto.getId(), SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }


    @DeleteMapping("skill/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteLearningSkillById(@PathVariable Long id) {
        Optional<LearningSkill> requestedRecord = learningSkillService.findById(id);
        if (requestedRecord.isPresent()) {
            try {
                learningSkillService.delete(requestedRecord.get());
            } catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), LearningSkill.class.getSimpleName(), id, e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), LearningSkill.class.getSimpleName(), id, SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(), LearningSkill.class.getSimpleName(), id, SystemMessage.RecordNotFound);


     }


}
