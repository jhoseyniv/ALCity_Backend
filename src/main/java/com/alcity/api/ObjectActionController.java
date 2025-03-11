package com.alcity.api;


import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "Object Action APIs", description = "Get Object actions and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/poa")

public class ObjectActionController {
    @Autowired
    private ActionService service;


    @Autowired
    AttributeService attributeService;

    @Operation( summary = "Delete a Puzzle Object Action ",  description = "Delete a Puzzle Object Action ")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleObjectActionById(@PathVariable Long id) {
        Optional<ObjectAction> existingRecord = service.findById(id);
        if(existingRecord.isPresent()){
            try {
                service.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getObjectAction().name(), existingRecord.get().getId(), PuzzleGroup.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

   @Operation( summary = "Fetch all actions for an object by id  ",  description = "Fetch all actions for an object by id ")
    @RequestMapping(value = "/obj/id/{id}/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ActionDTO> getActionsForAALCityObject(@PathVariable Long id) {
        Collection<ObjectAction> actions = service.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.Object);
        Collection<ActionDTO> dtos= PLDTOUtil.getActionDTOS(actions);
        return dtos;
    }
   @Operation( summary = "Fetch all parameters for an action defined in a city object by id  ",  description = "Fetch all parameters for an action defined in a object by id")
    @RequestMapping(value = "/action/id/{id}/param/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getParametersForCityObjectAction(@PathVariable Long id) {
       Optional<ObjectAction>  objectAction = service.findById(id);
       Long rendereId= objectAction.get().getActionRenderer().getId();
       //Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerType(id, AttributeOwnerType.Object_Action_Handler_Parameter);
       Collection<Attribute> parameters = attributeService.findByOwnerId(rendereId);
        Collection<AttributeDTO> dtos= DTOUtil.getAttributesDTOS(parameters);
        return dtos;
    }

    @Operation( summary = "Save a Object Action... ",  description = "Save a Object Action...")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveObjectAction(@RequestBody ActionDTO dto)  {
        ObjectAction savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = service.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getObjectAction().toString(), dto.getId(), "title must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = service.save(dto, "Edit");
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


}
