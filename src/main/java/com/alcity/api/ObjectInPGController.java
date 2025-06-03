package com.alcity.api;

import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.puzzle.ObjectInPGService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Al City Object used in different puzzle groups", description = "this part get actions and objects in a puzzle groups api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/opg")

public class ObjectInPGController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private ObjectInPGService alCityObjectInPGService;

    @Autowired
    private AttributeService attributeService;
    @Autowired
    private AttributeValueService attributeValueService;


    @Operation( summary = "Fetch all actions for an al city object that define in a puzzle group ",  description = "Fetch all actions for an al city object")
    @RequestMapping(value = "/id/{id}/actions/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ActionDTO> getActionsForAnObjectInPG(@PathVariable Long id) {
        Collection<ActionDTO> actionDTOS = new ArrayList<ActionDTO>();
        Collection<ObjectAction> actions = actionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.Puzzle_Group_Object);
        actionDTOS = PLDTOUtil.getActionDTOS(actions);
        return  actionDTOS;
    }

    @Operation( summary = "Fetch a object that define in a puzzle group ",  description = "Fetch an al city object that defined in a puzzle group")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public CityObjectInPGDTO getAnAObjectInPGDTO(@PathVariable Long id) {
        Optional<ALCityObjectInPG> alCityObjectInPGOptional = alCityObjectInPGService.findById(id);
        CityObjectInPGDTO alCityObjectInPGDTO = DTOUtil.getALCityObjectInPGDTO(alCityObjectInPGOptional.get());
        return  alCityObjectInPGDTO;
    }

    @Operation( summary = "Add a Object to a Puzzle Group ",  description = "Add a Object to a Puzzle Group ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveALCityObjectInPG(@RequestBody CityObjectInPGDTO dto)  {
        ALCityObjectInPG savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = alCityObjectInPGService.save(dto,"Save");
//                DTOUtil.copyActionFromTo(dto.getAlCityObjectId(), savedRecord.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
//                        AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,
//                        POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
//                DTOUtil.copyPropertyFromTo(dto.getAlCityObjectId(),savedRecord.getId(),AttributeOwnerType.Object_Property,AttributeOwnerType.Puzzle_Group_Object_Property,attributeService,attributeValueService);
//                DTOUtil.copyVariableFromTo(dto.getAlCityObjectId(),savedRecord.getId(),AttributeOwnerType.Object_Variable,AttributeOwnerType.Puzzle_Group_Object_Variable,attributeService,attributeValueService);

            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + ALCityObjectInPG.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = alCityObjectInPGService.save(dto, "Edit");
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
    @Operation( summary = "Remove a Object From a Puzzle Group",  description = "Remove an  AL City Object From a Puzzle Group")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteALCityObjectInPGById(@PathVariable Long id) {
        Optional<ALCityObjectInPG> existingRecord = alCityObjectInPGService.findById(id);
        if(existingRecord.isPresent()){
            try {
                alCityObjectInPGService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", ALCityObjectInPG.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }



}
