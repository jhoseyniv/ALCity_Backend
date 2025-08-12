package com.alcity.api;

import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.base.LearningSkillDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Collection<LearningSkill> learningSkillCollection = learningSkillService.findByValueContains(criteria);
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
    public ResponseObject saveLearningSkill(@RequestBody LearningSkillDTO dto)  {
        LearningSkill savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = learningSkillService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + LearningSkill.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = learningSkillService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @DeleteMapping("skill/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteLearningSkillById(@PathVariable Long id) {
        Optional<LearningSkill> existingRecord = learningSkillService.findById(id);
        if(existingRecord.isPresent()){
            try {
                learningSkillService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", LearningSkill.class.toString(),existingRecord.get().getId());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
