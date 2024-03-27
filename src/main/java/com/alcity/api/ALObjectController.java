package com.alcity.api;

import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.UserGender;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.alobject.ALCityAttributeService;
import com.alcity.service.alobject.ObjectCategoryService;
import com.alcity.service.base.UserGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/object")

public class ALObjectController {

    @Autowired
    private ALCityAttributeService alCityAttributeService;

    @Autowired
    private ObjectCategoryService objectCategoryService;


    @GetMapping("/categories/")
    public Collection<ObjectCategory> getObjectCategorie(Model model) {
        Collection<ObjectCategory> objectCategories = objectCategoryService.findAll();
        return objectCategories;
    }

    @GetMapping("/attributes/")
    public Collection<ALCityAttribute> getAttributes(Model model) {
        Collection<ALCityAttribute> alCityAttributes = alCityAttributeService.findAll();
        return alCityAttributes;
    }


    @RequestMapping(value = "attribute/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<ALCityAttribute> getALCityAttributeById(@PathVariable Long id) {
        Optional<ALCityAttribute> alCityAttribute = alCityAttributeService.findById(id);
        return alCityAttribute;
    }

}
