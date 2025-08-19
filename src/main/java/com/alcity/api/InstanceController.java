package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.plimpexport.ActionData;
import com.alcity.dto.puzzle.InstanceDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.ActionService;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.InstanceService;
import com.alcity.service.puzzle.PLCellService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Algoopia Object instances used in different puzzle Level", description = "Algoopia Object instances used in different puzzle Level")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/co-pl")
public class InstanceController {

    @Autowired
    private InstanceService service;

//    @Autowired
//    private AttributeService attributeService;

    @Autowired
    private PLCellService plCellService;


    @Autowired
    ActionService actionService;

    @Operation( summary = "Add a Object Instance to a Puzzle Level ",  description = "Add a Object Instance to a Puzzle Level ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveInstance(@RequestBody InstanceDTO dto)  {
        Instance savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        if (dto.getName()==null) dto.setName("instance_"+dto.getRow()+"_"+dto.getCol()+"_"+dto.getzOrder()+"_puzzle_id"+dto.getPuzzleLevelId());
        Optional<Instance> instanceOptional = service.findById(dto.getId());

        try{
            if (instanceOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , Instance.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name() , Instance.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() , Instance.class.getSimpleName() , dto.getId(), SystemMessage.SaveOrEditMessage_Fail);
        return response;
   }

    @Operation( summary = "Add an Instance to a PL Cell ",  description = "Add an Instance to a PL Cell ")
    @PostMapping("/{iid}/iid/add-to-cell/{cid}/cid")
    @CrossOrigin(origins = "*")
    public ResponseMessage addInstanceToCell(@PathVariable Long iid, @PathVariable Long cid)  {
        //Instance instance = null;
        ResponseMessage response = new ResponseMessage();
        Optional<Instance> instanceOptional = service.findById(iid);
        Optional<PLCell> plCellOptional = plCellService.findById(iid);

        if(plCellOptional.isEmpty() || instanceOptional.isEmpty())
            return new ResponseMessage(ErrorType.RecordNotFound,  Status.error.name(),Instance.class.getSimpleName(),cid,SystemMessage.RecordNotFound);

        Instance instance = instanceOptional.get();
        instance.setPlCell(plCellOptional.get());
        service.save(instance);
        return response;
    }

    @Operation( summary = "Copy an Instance to a other cells of a puzzle ",  description = "Copy an Instance to  other cells of a puzzle")
    @RequestMapping(value = "/copy/instance/id/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseMessage copyInstanceToOtherCellsInPL(@PathVariable Long id) {
        ResponseMessage responseObject = new ResponseMessage();
        Optional<Instance> instanceOptional = service.findById(id);
        if(instanceOptional.isEmpty()) return
                 new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(),Instance.class.getSimpleName(),  id,SystemMessage.RecordNotFound);

        Instance instance = instanceOptional.get();
        PuzzleLevel puzzleLevel = instance.getPuzzleLevel();
        Collection<PLGround> plGrounds = puzzleLevel.getPlGrounds();
        PLGround plGround = plGrounds.iterator().next();
        Collection<Instance> copiedInstances = service.copyOneInstanceToOthers(instance,plGround);

        return
                new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), Instance.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }


    @Operation( summary = "Fetch an Algoopia object that define in a puzzle level ",  description = "Fetch an Algoopia object that defined in a puzzle level")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public InstanceDTO getAnAObjectInPLDTO(@PathVariable Long id) {
        Optional<Instance> instanceOptional = service.findById(id);
        if(instanceOptional.isEmpty()) return null;
        InstanceDTO instanceDTO = DTOUtil.getALCityObjectInPLDTO(instanceOptional.get());
        return  instanceDTO;
    }
    @Operation( summary = "Fetch all actions for an Algoopia object instance in a puzzle level by instance Id ",  description = "Fetch all actions for an Algoopia object instance ")
    @RequestMapping(value = "/id/{id}/actions/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ActionData> getAllActionsForAnALCityInstanceInPuzzleLevelById(@PathVariable Long id) {
        Collection<ActionData> actionsData = new ArrayList<ActionData>();
        Collection<ObjectAction> actions = new ArrayList<ObjectAction>();
        actions = actionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.Puzzle_Level_Instance);
        actionsData = DTOUtil.getObjectActionDTOS(actions);
        return  actionsData;
    }

 /*   @Operation( summary = "Fetch all variables for a city object instance in a puzzle level by instance Id ",  description = "Fetch all variables for an alcity object instance ")
    @RequestMapping(value = "/id/{id}/variables/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<RecordData> getAllVariablesForAnALCityInstanceInPuzzleLevelById(@PathVariable Long id) {
        Collection<RecordData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,id,AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        return  variables;
    }
    @Operation( summary = "Fetch all properties for an object instance in a puzzle level by instance Id ",  description = "Fetch all properties for an alcity object instance  ")
    @RequestMapping(value = "/id/{id}/properties/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<RecordData> getAllPropertiesForAnALCityInstanceInPuzzleLevelById(@PathVariable Long id) {
        Collection<RecordData>  properties = DTOUtil.getAttributeForOwnerById(attributeService,id,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        return  properties;
    }
*/

    @Operation( summary = "Remove an Instance From a Puzzle Level",  description = "Remove an  AL City Object  Instance From a Puzzle Level")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteInstance(@PathVariable Long id) {
        Optional<Instance> instance = service.findById(id);
        if(instance.isPresent()){
            try {
                service.deleteById(instance.get().getId());
            }catch (Exception e )
            {
                throw new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name() , Instance.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess,  Status.ok.name(),Instance.class.getSimpleName(), instance.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(),Instance.class.getSimpleName(),  instance.get().getId(),SystemMessage.RecordNotFound);
    }


}
