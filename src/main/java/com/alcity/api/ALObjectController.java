package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.alobject.*;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.service.alobject.*;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Tag(name = "ALCity Object Details", description = "get deatils for an Al City Object Api ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/obj")

public class ALObjectController {

    @Autowired
    private ObjectCategoryService objectCategoryService;


    @Operation( summary = "Fetch all Object Categories",  description = "fetches all Object Category entities and their data from data source")
    @GetMapping("/cat/all")
    @CrossOrigin(origins = "*")
    public Collection<ObjectCategoryDTO> getObjectCategorie(Model model) {
        Collection<ObjectCategory> objectCategories = objectCategoryService.findAll();
        Collection<ObjectCategoryDTO> output = DTOUtil.getObjectCategories(objectCategories);
        return output;
    }
    @Operation( summary = "Fetch an Object Category by a Id",  description = "fetches an Object Category entity and their data from data source")
    @RequestMapping(value = "/cat/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ObjectCategoryDTO getObjectCategoryById(@PathVariable Long id) {
        Optional<ObjectCategory> objectCategoryOptional = objectCategoryService.findById(id);
        if(objectCategoryOptional.isPresent())
            return DTOUtil.getObjectCategoryDTO(objectCategoryOptional.get());
        return null;
    }

    @Operation( summary = "Fetch All Object actions ",  description = "fetches All actions that an Object can have in the al city ")
    @GetMapping("/actions/all")
    @CrossOrigin(origins = "*")
    public ObjectActionType[] getObjectActions(Model model) {
        return ObjectActionType.values();
    }

    @Operation( summary = "Fetch an action by a Id ",  description = "fetches one action by id ")
    @RequestMapping(value = "/action/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ObjectActionType getObjectActionById(@PathVariable Integer id) {
       return ObjectActionType.getById(id);
    }

    @Operation( summary = "Fetch all owner types of action ",  description = "fetches all owner types for object actions")
    @GetMapping("/action/owner/type/all")
    @CrossOrigin(origins = "*")
    public POActionOwnerType[] getObjectActionOwnerTypes(Model model) {
        return POActionOwnerType.values();
    }

    @Operation( summary = "Fetch attribute owner types ",  description = "fetches all attribute owner types")
    @GetMapping("/att/owner/type/all")
    @CrossOrigin(origins = "*")
    public AttributeOwnerType[] getAttributeOwnerTypes() {
        return AttributeOwnerType.values();
    }

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/att/all")
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getAttributese(Model model) {
        Collection<Attribute> attributeCollection = attributeService.findAll();
        Collection<AttributeDTO> attributeDTOCollection = new ArrayList<AttributeDTO>();
        attributeDTOCollection = DTOUtil.getAttributesDTOS(attributeCollection);
        return attributeDTOCollection;
    }



    @Operation( summary = "Save an Object Category ",  description = "Save an Object Category ")
    @PostMapping("/cat/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveObjectCategory(@RequestBody ObjectCategoryDTO dto) throws Exception  {
        ObjectCategory savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<ObjectCategory> objectCategoryOptional = objectCategoryService.findById(dto.getId());
        try{
            if (objectCategoryOptional.isEmpty())
                savedRecord = objectCategoryService.save(dto,"Save");
            else
                savedRecord = objectCategoryService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , ObjectCategory.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),ObjectCategory.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), ObjectCategory.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;
     }


    @Operation( summary = "delete a  Object category entity",  description = "delete an object category entity and their data to data base")
    @DeleteMapping("/cat/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteObjectCategoryById(@PathVariable Long id) {
        Optional<ObjectCategory> requestedRecord = objectCategoryService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                objectCategoryService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), ObjectCategory.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess,  Status.ok.name(),ObjectCategory.class.getSimpleName(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),ObjectCategory.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }





}
