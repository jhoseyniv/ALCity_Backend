package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseMessage deletePuzzleObjectActionById(@PathVariable Long id) {
        Optional<ObjectAction> requestedRecord = service.findById(id);
        if (requestedRecord.isPresent()) {
            try {
                service.delete(requestedRecord.get());
            } catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), ObjectAction.class.getSimpleName(), id, e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), ObjectAction.class.getSimpleName(), id, SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(), ObjectAction.class.getSimpleName(), id, SystemMessage.RecordNotFound);
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
    public ResponseMessage saveObjectAction(@RequestBody ActionDTO dto)  {
        ObjectAction savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<ObjectAction> objectActionOptional = service.findById(dto.getId());
        try{
            if (objectActionOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , ObjectAction.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), ObjectAction.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), ObjectAction.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

    }


}
