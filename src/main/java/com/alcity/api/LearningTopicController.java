package com.alcity.api;


import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.learning.LearningTopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "Learning topic Data Type APIs", description = "All APIs for basic data types... ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/learning-topic")

public class LearningTopicController {
    @Autowired
    private LearningTopicService learningTopicService;

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<LearningTopic> getLearningTopics(Model model) {
        Collection<LearningTopic> learningTopicCollection = learningTopicService.findAll();
        return learningTopicCollection;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Optional<LearningTopic> getLearningTopicById(@PathVariable Long id) {
        Optional<LearningTopic> learningTopic = learningTopicService.findById(id);
        return learningTopic;
    }

}
