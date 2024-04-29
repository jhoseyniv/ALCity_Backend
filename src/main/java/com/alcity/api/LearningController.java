package com.alcity.api;

import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.service.base.ClientTypeService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/learn")
public class LearningController {
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

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LearningSkillDTO getLearnSkillById(@PathVariable Long id) {
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(id);
        LearningSkillDTO learningSkillDTO = new LearningSkillDTO();
        if(learningSkillOptional.isPresent())
            learningSkillDTO = DTOUtil.getLearningSkillDTO(learningSkillOptional.get());
        return learningSkillDTO;
    }
    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
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

}
