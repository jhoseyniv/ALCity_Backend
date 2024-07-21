package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.base.LearningSkillDTO;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.ALCityObject;
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
    public ALCityResponseObject saveLearningSkill(@RequestBody LearningSkillDTO dto)  {
        LearningSkill savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = learningSkillService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getValue(), dto.getId(), "value must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = learningSkillService.save(dto, "Edit");
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
    @DeleteMapping("skill/del/{id}")
    public ResponseEntity<String> deleteLearningSkillById(@PathVariable Long id) {
        Optional<LearningSkill> existingRecord = learningSkillService.findById(id);
        if(existingRecord.isPresent()){
            try {
                learningSkillService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getLabel(), existingRecord.get().getId(), LearningSkill.class.toString());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
