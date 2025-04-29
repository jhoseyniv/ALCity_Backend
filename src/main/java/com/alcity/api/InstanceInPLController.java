package com.alcity.api;


import com.alcity.dto.Interpreter.object.ActionData;
import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.dto.puzzle.CityObjectInPLDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.ALCityInstanceInPL;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.InstanceInPLService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Algoopia Object instances used in different puzzle Level", description = "Algoopia Object instances used in different puzzle Level")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/co-pl")
public class InstanceInPLController {

    @Autowired
    private InstanceInPLService service;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    ActionService actionService;

    @Operation( summary = "Add a Object Instance to a Puzzle Level ",  description = "Add a Object Instance to a Puzzle Level ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveALCityObjectInPL(@RequestBody CityObjectInPLDTO dto)  {
        ALCityInstanceInPL savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = service.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getName(), dto.getId(), "title must be Unique");
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

    @Operation( summary = "Copy an Instance to a other cells of a puzzle ",  description = "Copy an Instance to  other cells of a puzzle")
    @RequestMapping(value = "/copy/instance/id/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ALCityResponseObject copyInstanceToOtherCellsInPL(@PathVariable Long id) {
        ALCityResponseObject responseObject = new ALCityResponseObject();
        Optional<ALCityInstanceInPL> instanceOptional = service.findById(id);
        if(instanceOptional.isEmpty()) return new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Instance Not Found!");
        ALCityInstanceInPL instance = instanceOptional.get();
        PuzzleLevel puzzleLevel = instance.getPuzzleLevel();
        Collection<PLGround> plGrounds = puzzleLevel.getPlGrounds();
        PLGround plGround = plGrounds.iterator().next();
        service.copyAllInstances(instance,plGround);
        return  responseObject;
    }


    @Operation( summary = "Fetch an Algoopia object that define in a puzzle level ",  description = "Fetch an Algoopia object that defined in a puzzle level")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public CityObjectInPLDTO getAnAObjectInPLDTO(@PathVariable Long id) {
        Optional<ALCityInstanceInPL> alCityInstanceInPLOptional = service.findById(id);
        CityObjectInPLDTO cityObjectInPLDTO = DTOUtil.getALCityObjectInPLDTO(alCityInstanceInPLOptional.get());
        return  cityObjectInPLDTO;
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

    @Operation( summary = "Remove a City Object Instance From a Puzzle Level",  description = "Remove an  AL City Object  Instance From a Puzzle Level")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteALCityObjectInPLById(@PathVariable Long id) {
        Optional<ALCityInstanceInPL> existingRecord = service.findById(id);
        if(existingRecord.isPresent()){
            try {
                service.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getName(), existingRecord.get().getId(), CityObjectInPGDTO.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


}
