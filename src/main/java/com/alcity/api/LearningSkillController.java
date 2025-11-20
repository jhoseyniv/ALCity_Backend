package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.learning.LearningSkillTreeDTO;
import com.alcity.dto.plimpexport.ruleexport.PostActionTreeExport;
import com.alcity.entity.alenum.SkillType;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("all-LearningSkillDTO")
    public Collection<LearningSkillDTO> getLearningSkills() {
        Collection<LearningSkill> skills = new ArrayList<>();
        skills = learningSkillService.findAll();
        Collection<LearningSkillDTO>  dtos = new ArrayList<LearningSkillDTO>();
        dtos = DTOUtil.getLearningSkillDTO(skills);
        return dtos;
    }

    @RequestMapping(value = "/skill/type/{type}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = "getLearningSkillsByType", key = "#p0")
    public Collection<LearningSkillDTO> getLearningSkillsByType(@PathVariable String type) {
        Collection<LearningSkill> skills = new ArrayList<>();
        SkillType skillType = SkillType.getByTitle(type);
        skills = learningSkillService.findByType(skillType);
        Collection<LearningSkillDTO>  dtos = new ArrayList<LearningSkillDTO>();
        dtos = DTOUtil.getLearningSkillDTO(skills);
        return dtos;
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

    @RequestMapping(value = "/skill-tree/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = "getLearnSkillTreeById", key = "#p0")
    public LearningSkillTreeDTO getLearnSkillTreeById(@PathVariable Long id) {
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(id);
        LearningSkillTreeDTO  skillTree = new LearningSkillTreeDTO();
        if(learningSkillOptional.isPresent())
            skillTree =DTOUtil.traverseSkillTree(skillTree,learningSkillService ,learningSkillOptional.get());
        return skillTree;
    }

    @RequestMapping(value = "/skill/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<LearningSkillDTO> getLearningSkillByCriteria(@PathVariable String criteria) {
        Collection<LearningSkill> skills = learningSkillService.findByTitleContains(criteria);
        Collection<LearningSkillDTO>  dtos = new ArrayList<LearningSkillDTO>();
         dtos = DTOUtil.getLearningSkillDTO(skills);
        return dtos;

    }


    @ExceptionHandler(UniqueConstraintException.class)
    @PostMapping("/skill/save")
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
