package com.alcity.api;

import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.entity.learning.LearningContent;
import com.alcity.service.learning.LearningContentService;
import com.alcity.utility.DTOUtil;
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
}
