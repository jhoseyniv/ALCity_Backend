package com.alcity.api;

import com.alcity.dto.alobject.*;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.ClientType;
import com.alcity.service.alobject.*;
import com.alcity.service.puzzle.PuzzleObjectService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;


@Tag(name = "Object", description = "the Al City Object Api ")
@RestController
@RequestMapping("/obj")

public class ALObjectController {

    @Autowired
    private ObjectCategoryService objectCategoryService;

    @Autowired
    private ActionRendererService actionRendererService;



    @Operation( summary = "Fetch all Object Categories",  description = "fetches all Object Category entities and their data from data source")
    @GetMapping("/cat/all")
    public Collection<ObjectCategoryDTO> getObjectCategorie(Model model) {
        Collection<ObjectCategoryDTO> oCategoryDTOCollection = new ArrayList<ObjectCategoryDTO>();
        Collection<ObjectCategory> objectCategories = objectCategoryService.findAll();
        Iterator<ObjectCategory> iterator = objectCategories.iterator();
        while(iterator.hasNext()){
            ObjectCategoryDTO objectCategoryDTO =DTOUtil.getObjectCategoryDTO(iterator.next());
            oCategoryDTOCollection.add(objectCategoryDTO);
        }
        return oCategoryDTOCollection;
    }
    @Operation( summary = "Fetch an Object Category by a Id",  description = "fetches an Object Category entity and their data from data source")
    @RequestMapping(value = "/cat/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectCategoryDTO getObjectCategoryById(@PathVariable Long id) {
        Optional<ObjectCategory> objectCategoryOptional = objectCategoryService.findById(id);
        if(objectCategoryOptional.isPresent())
            return DTOUtil.getObjectCategoryDTO(objectCategoryOptional.get());
        return null;
    }

    @Operation( summary = "Fetch All Object actions ",  description = "fetches All actions that ab Object can have in the al city ")
    @GetMapping("/action/all")
    public ObjectAction[] getObjectActions(Model model) {
        return ObjectAction.values();
    }

    @Operation( summary = "Fetch an action by a Id ",  description = "fetches one action by id ")
    @RequestMapping(value = "/action/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectAction getObjectActionById(@PathVariable Integer id) {
       return ObjectAction.getById(id);
    }

    @Operation( summary = "Fetch all owner types of action ",  description = "fetches all owner types for object actions")
    @GetMapping("/action/owner/type/all")
    public POActionOwnerType[] getObjectActionOwnerTypes(Model model) {
        return POActionOwnerType.values();
    }

    @Operation( summary = "Fetch all action renders types ",  description = "fetches all renderers types for all actions")
    @GetMapping("/action/renderer/all")
    public Collection<ActionRendererDTO> getActionRenderers(Model model) {
          Collection<ActionRendererDTO> actionRendererDTOCollection = new ArrayList<ActionRendererDTO>();
          Collection<ActionRenderer> actionRendererCollection = actionRendererService.findAll();
          Iterator<ActionRenderer> iterator = actionRendererCollection.iterator();
          while(iterator.hasNext()){
              ActionRendererDTO actionRendererDTO =DTOUtil.getActionRendererDTO(iterator.next());
              actionRendererDTOCollection.add(actionRendererDTO);
          }
          return actionRendererDTOCollection;
      }
    @RequestMapping(value = "/action/renderer/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActionRendererDTO getObjectActionRendererById(@PathVariable Long id) {
        Optional<ActionRenderer> actionRendererOptional = actionRendererService.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getActionRendererDTO(actionRendererOptional.get());
         return null;
    }


    @Operation( summary = "Fetch attribute owner types ",  description = "fetches all attribute owner types")
    @GetMapping("/att/owner/type/all")
    public AttributeOwnerType[] getAttributeOwnerTypes() {
        return AttributeOwnerType.values();
    }

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/att/all")
    public Collection<AttributeDTO> getAttributese(Model model) {
        Collection<Attribute> attributeCollection = attributeService.findAll();
        Collection<AttributeDTO> attributeDTOCollection = new ArrayList<AttributeDTO>();
        attributeDTOCollection = DTOUtil.getAttributesDTOS(attributeCollection);
        return attributeDTOCollection;
    }

    @RequestMapping(value = "/att/owner/{id}/type/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<AttributeDTO> getAttributesByOwnerIdAndOwnerType(@PathVariable Long id,@PathVariable Long type) {
        Collection<Attribute> attributeCollection = attributeService.findByOwnerIdAndAttributeOwnerType(id,AttributeOwnerType.getById(type));
        Collection<AttributeDTO> attributeDTOCollection = new ArrayList<AttributeDTO>();
        attributeDTOCollection = DTOUtil.getAttributesDTOS(attributeCollection);
        return  attributeDTOCollection;
    }
    @RequestMapping(value = "/att/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AttributeDTO getALCityAttributeById(@PathVariable Long id) {
        Optional<Attribute> attributeOptional = attributeService.findById(id);
        if(attributeOptional.isPresent())
            return  DTOUtil.getAttributeDTO(attributeOptional.get());
        return null;
    }





}
