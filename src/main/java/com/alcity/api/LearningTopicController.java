package com.alcity.api;


import com.alcity.dto.alobject.RendererDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.utility.DTOUtil;
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

}
