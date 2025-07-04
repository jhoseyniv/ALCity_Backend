package com.alcity.api;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.alobject.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.*;
import com.alcity.service.alobject.*;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ALCityResponseObject saveObjectCategory(@RequestBody ObjectCategoryDTO dto) throws Exception  {
        ObjectCategory savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = objectCategoryService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + ObjectCategory.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = objectCategoryService.save(dto, "Edit");
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
    @Operation( summary = "delete a  Object category entity",  description = "delete an object category entity and their data to data base")
    @DeleteMapping("/cat/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteObjectCategoryById(@PathVariable Long id) {
        Optional<ObjectCategory> existingRecord = objectCategoryService.findById(id);
        if(existingRecord.isPresent()){
            try {
                objectCategoryService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", ObjectCategory.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }





}
