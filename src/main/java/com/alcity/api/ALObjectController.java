package com.alcity.api;

import com.alcity.dto.alobject.ObjectCategoryDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.dto.puzzle.PuzzleGroupDTO;
import com.alcity.dto.puzzle.PuzzleObjectDTO;
import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.base.UserGender;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.PuzzleObject;
import com.alcity.service.alobject.ALCityAttributeService;
import com.alcity.service.alobject.ObjectCategoryService;
import com.alcity.service.base.UserGenderService;
import com.alcity.service.puzzle.PuzzleObjectService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/object")

public class ALObjectController {

    @Autowired
    private ALCityAttributeService alCityAttributeService;

    @Autowired
    private ObjectCategoryService objectCategoryService;

    @Autowired
    private PuzzleObjectService puzzleObjectService;


    @GetMapping("/categories/all")
    public Collection<ObjectCategoryDTO> getObjectCategorie(Model model) {
        Collection<ObjectCategoryDTO> objectCategoryDTOCollection = new ArrayList<ObjectCategoryDTO>();
        Collection<ObjectCategory> objectCategories = objectCategoryService.findAll();
        Iterator<ObjectCategory> iterator = objectCategories.iterator();
        while(iterator.hasNext()){
            ObjectCategoryDTO objectCategoryDTO = new ObjectCategoryDTO();
            ObjectCategory objectCategory = iterator.next();
            objectCategoryDTO.setId(objectCategory.getId());
            objectCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(objectCategory.getCreated()));
            objectCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(objectCategory.getUpdated()));
            objectCategoryDTO.setVersion(objectCategory.getVersion());
            objectCategoryDTO.setLabel(objectCategory.getLabel());
            objectCategoryDTO.setValue(objectCategory.getValue());
            objectCategoryDTOCollection.add(objectCategoryDTO);
        }
        return objectCategoryDTOCollection;
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

    @RequestMapping(value = "category/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleObjectDTO> getPuzzleObjectsByObjectCategoryById(@PathVariable Long id) {
        Collection<PuzzleObjectDTO> puzzleObjectDTOCollection = new ArrayList<PuzzleObjectDTO>();
        Optional<ObjectCategory> objectCategoryOptional = objectCategoryService.findById(id);
        if(objectCategoryOptional.isPresent()) {
            Collection<PuzzleObject> puzzleObjectCollection = new ArrayList<PuzzleObject>();
            ObjectCategory objectCategory =objectCategoryOptional.get();
            puzzleObjectCollection = objectCategory.getPuzzleObjectCollection();
            Iterator<PuzzleObject> itr = puzzleObjectCollection.iterator();
            while(itr.hasNext()){
                PuzzleObject puzzleObject = new PuzzleObject();
                PuzzleObjectDTO puzzleObjectDTO = new PuzzleObjectDTO();
                puzzleObject = itr.next();
                puzzleObjectDTO.setId(puzzleObject.getId());
                puzzleObjectDTO.setObjectCategory(puzzleObject.getObjectCategory().getLabel());
                puzzleObjectDTO.setTitle(puzzleObject.getTitle());
                puzzleObjectDTO.setVersion(puzzleObject.getVersion());
                puzzleObjectDTO.setCreated(DateUtils.getDatatimeFromLong(objectCategory.getCreated()));
                puzzleObjectDTO.setUpdated(DateUtils.getDatatimeFromLong(objectCategory.getUpdated()));

                BinaryContentDTO iconDTO = new BinaryContentDTO(puzzleObject.getIcon().getFileName(),puzzleObject.getIcon().getSize(),
                        puzzleObject.getIcon().getContent(),puzzleObject.getIcon().getId(),puzzleObject.getIcon().getVersion(),
                        DateUtils.getDatatimeFromLong(puzzleObject.getIcon().getCreated())
                        ,DateUtils.getDatatimeFromLong(puzzleObject.getIcon().getUpdated()));
                puzzleObjectDTO.setIcon(iconDTO);
                puzzleObjectDTOCollection.add(puzzleObjectDTO);
            }

        }

      return puzzleObjectDTOCollection;
    }

}
