package com.alcity.api;


import com.alcity.dto.plimpexport.ActionData;
import com.alcity.dto.puzzle.InstanceDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
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
    public ResponseObject saveInstance(@RequestBody InstanceDTO dto)  {
        Instance instance = null;
        ResponseObject responseObject = new ResponseObject();
        if (dto.getName()==null) dto.setName("instance_"+dto.getRow()+"_"+dto.getCol()+"_"+dto.getzOrder()+"_puzzle_id"+dto.getPuzzleLevelId());
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                instance = service.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + Instance.class , "Error",instance.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), instance.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            instance = service.save(dto, "Edit");
            if(instance !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), instance.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (instance==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "Add an Instance to a PL Cell ",  description = "Add an Instance to a PL Cell ")
    @PostMapping("/{iid}/iid/add-to-cell/{cid}/cid")
    @CrossOrigin(origins = "*")
    public ResponseObject addInstanceToCell(@PathVariable Long iid, @PathVariable Long cid)  {
        //Instance instance = null;
        ResponseObject responseObject = new ResponseObject();
        Optional<Instance> instanceOptional = service.findById(iid);
        Optional<PLCell> plCellOptional = plCellService.findById(iid);

        if(plCellOptional.isEmpty() || instanceOptional.isEmpty())
            return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(),cid,SystemMessage.RecordNotFound);

        Instance instance = instanceOptional.get();
        instance.setPlCell(plCellOptional.get());
        service.save(instance);
        return responseObject;
    }

    @Operation( summary = "Copy an Instance to a other cells of a puzzle ",  description = "Copy an Instance to  other cells of a puzzle")
    @RequestMapping(value = "/copy/instance/id/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseObject copyInstanceToOtherCellsInPL(@PathVariable Long id) {
        ResponseObject responseObject = new ResponseObject();
        Optional<Instance> instanceOptional = service.findById(id);
        if(instanceOptional.isEmpty()) return
                 new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);

        Instance instance = instanceOptional.get();
        PuzzleLevel puzzleLevel = instance.getPuzzleLevel();
        Collection<PLGround> plGrounds = puzzleLevel.getPlGrounds();
        PLGround plGround = plGrounds.iterator().next();
        Collection<Instance> copiedInstances = service.copyOneInstanceToOthers(instance,plGround);

        return
                new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);
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
    public ResponseObject deleteInstance(@PathVariable Long id) {
        Optional<Instance> instance = service.findById(id);
        if(instance.isPresent()){
            try {
                service.deleteById(instance.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", Instance.class.toString(),instance.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.ok.name(), instance.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), instance.get().getId(),SystemMessage.RecordNotFound);
    }


}
