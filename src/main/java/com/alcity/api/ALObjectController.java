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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/obj")

public class ALObjectController {

    @Autowired
    private ObjectCategoryService objectCategoryService;

    @Autowired
    private PuzzleObjectService puzzleObjectService;

    @Autowired
    private ActionRendererService actionRendererService;



    @GetMapping("/cat/all")
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
    @RequestMapping(value = "/cat/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectCategoryDTO getObjectCategoryById(@PathVariable Long id) {
        Optional<ObjectCategory> objectCategoryOptional = objectCategoryService.findById(id);
        ObjectCategoryDTO objectCategoryDTO = new ObjectCategoryDTO();
        if(objectCategoryOptional.isPresent()){
            ObjectCategory objectCategory = objectCategoryOptional.get();
            objectCategoryDTO.setId(objectCategory.getId());
            objectCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(objectCategory.getCreated()));
            objectCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(objectCategory.getUpdated()));
            objectCategoryDTO.setVersion(objectCategory.getVersion());
            objectCategoryDTO.setLabel(objectCategory.getLabel());
            objectCategoryDTO.setValue(objectCategory.getValue());
        } else objectCategoryDTO = null;

        return objectCategoryDTO;
    }
    @GetMapping("/action/all")
    public ObjectAction[] getObjectActions(Model model) {
        return ObjectAction.values();
    }

    @RequestMapping(value = "/action/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectAction getObjectActionById(@PathVariable Integer id) {
       return ObjectAction.getById(id);
    }

    @GetMapping("/action/owner/type/all")
    public POActionOwnerType[] getObjectActionOwnerTypes(Model model) {
        return POActionOwnerType.values();
    }
//
//    @RequestMapping(value = "/action/owner/type/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public PuzzleObjectActionOwnerTypeDTO getObjectActionOwnerTypeById(@PathVariable Long id) {
//        PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO=new PuzzleObjectActionOwnerTypeDTO();
//        Optional<POActionOwnerType> puzzleObjectActionOwnerTypeOptional = puzzleObjectActionOwnerTypeService.findById(id);
//        if(puzzleObjectActionOwnerTypeOptional.isPresent()){
//            POActionOwnerType puzzleObjectActionOwnerType = puzzleObjectActionOwnerTypeOptional.get();
//            puzzleObjectActionOwnerTypeDTO.setId(puzzleObjectActionOwnerType.getId());
//            puzzleObjectActionOwnerTypeDTO.setLabel(puzzleObjectActionOwnerType.getLabel());
//            puzzleObjectActionOwnerTypeDTO.setValue(puzzleObjectActionOwnerType.getValue());
//            puzzleObjectActionOwnerTypeDTO.setVersion(puzzleObjectActionOwnerType.getVersion());
//            puzzleObjectActionOwnerTypeDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleObjectActionOwnerType.getCreated()));
//            puzzleObjectActionOwnerTypeDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleObjectActionOwnerType.getUpdated()));
//        }else puzzleObjectActionOwnerTypeDTO = null;
//
//        return puzzleObjectActionOwnerTypeDTO;
//    }


    @GetMapping("/action/renderer/all")
    public Collection<ActionRendererDTO> getActionRenderers(Model model) {
          Collection<ActionRendererDTO> actionRendererDTOCollection = new ArrayList<ActionRendererDTO>();
          Collection<ActionRenderer> actionRendererCollection = actionRendererService.findAll();
          Iterator<ActionRenderer> iterator = actionRendererCollection.iterator();
          while(iterator.hasNext()){
              ActionRendererDTO actionRendererDTO = new ActionRendererDTO();
              ActionRenderer actionRenderer = iterator.next();
              actionRendererDTO.setId(actionRenderer.getId());
              actionRendererDTO.setCreated(DateUtils.getDatatimeFromLong(actionRenderer.getCreated()));
              actionRendererDTO.setUpdated(DateUtils.getDatatimeFromLong(actionRenderer.getUpdated()));
              actionRendererDTO.setVersion(actionRenderer.getVersion());
              ObjectAction objectAction = actionRenderer.getObjectAction();

//              ObjectActionDTO objectActionDTO = new ObjectActionDTO(objectAction.getId(),objectAction.getLabel(),objectAction.getValue(),objectAction.getVersion(),
//                      DateUtils.getDatatimeFromLong(objectAction.getCreated()),
//                      DateUtils.getDatatimeFromLong(objectAction.getUpdated()));

           //   actionRendererDTO.setObjectActionDTO(objectActionDTO);

              ClientType clientType = actionRenderer.getClientType();
              ClientTypeDTO clientTypeDTO = new ClientTypeDTO(clientType.getId(),clientType.getLabel(),clientType.getValue(), clientType.getVersion(),
                      DateUtils.getDatatimeFromLong(clientType.getCreated()),
                      DateUtils.getDatatimeFromLong(clientType.getUpdated()));

              actionRendererDTO.setClientTypeDTO(clientTypeDTO);
              actionRendererDTO.setHandler(actionRenderer.getHandler());
              actionRendererDTOCollection.add(actionRendererDTO);
          }
          return actionRendererDTOCollection;
      }
    @RequestMapping(value = "/action/renderer/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActionRendererDTO getObjectActionRendererById(@PathVariable Long id) {
        Optional<ActionRenderer> actionRendererOptional = actionRendererService.findById(id);
        ActionRendererDTO actionRendererDTO = new ActionRendererDTO();
        if(actionRendererOptional.isPresent())  DTOUtil.getActionRenderer(actionRendererOptional.get());
        else actionRendererDTO = null;
    return actionRendererDTO;
    }


    @GetMapping("/att/owner/type/all")
    public AttributeOwnerType[] getAttributeOwnerTypes() {
        return AttributeOwnerType.values();
    }

//
//    @RequestMapping(value = "/att/owner/type/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public AttributeOwnerType getAttributeOwnerTypeById(@PathVariable String ownerType) {
//        return AttributeOwnerType.;
//    }

    @Autowired
    private AttributeService alCityAttributeService;

    @GetMapping("/att/all")
    public Collection<AttributeDTO> getAttributes(Model model) {
        Collection<Attribute> alCityAttributeCollection = alCityAttributeService.findAll();
        Collection<AttributeDTO> alCityAttributeDTOCollection = new ArrayList<AttributeDTO>();
        alCityAttributeDTOCollection = DTOUtil.getALCityAttributes(alCityAttributeCollection);
        return alCityAttributeDTOCollection;
    }
    @RequestMapping(value = "/att/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AttributeDTO getALCityAttributeById(@PathVariable Long id) {
        Optional<Attribute> alCityAttributeOptional = alCityAttributeService.findById(id);
        AttributeDTO alCityAttributeDTO = new AttributeDTO();
        alCityAttributeDTO = DTOUtil.getALCityAttributeDTO(alCityAttributeOptional);
        return alCityAttributeDTO;
    }





}
