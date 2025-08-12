package com.alcity.api;

import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PGObjectDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.puzzle.PGObjectService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Al City Object used in different puzzle groups", description = "this part get actions and objects in a puzzle groups api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/opg")

public class PGObjectController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private PGObjectService alCityObjectInPGService;

//    @Autowired
//    private AttributeService attributeService;
//    @Autowired
//    private AttributeValueService attributeValueService;


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
    public PGObjectDTO getAnAObjectInPGDTO(@PathVariable Long id) {
        Optional<PGObject> alCityObjectInPGOptional = alCityObjectInPGService.findById(id);
        PGObjectDTO alCityObjectInPGDTO = DTOUtil.getALCityObjectInPGDTO(alCityObjectInPGOptional.get());
        return  alCityObjectInPGDTO;
    }

    @Operation( summary = "Add a Object to a Puzzle Group ",  description = "Add a Object to a Puzzle Group ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseObject saveALCityObjectInPG(@RequestBody PGObjectDTO dto)  {
        PGObject savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = alCityObjectInPGService.save(dto,"Save");
//                DTOUtil.copyActionFromTo(dto.getAlCityObjectId(), savedRecord.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
//                        AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,
//                        POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
//                DTOUtil.copyPropertyFromTo(dto.getAlCityObjectId(),savedRecord.getId(),AttributeOwnerType.Object_Property,AttributeOwnerType.Puzzle_Group_Object_Property,attributeService,attributeValueService);
//                DTOUtil.copyVariableFromTo(dto.getAlCityObjectId(),savedRecord.getId(),AttributeOwnerType.Object_Variable,AttributeOwnerType.Puzzle_Group_Object_Variable,attributeService,attributeValueService);

            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PGObject.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = alCityObjectInPGService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "Remove a Object From a Puzzle Group",  description = "Remove an  AL City Object From a Puzzle Group")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteALCityObjectInPGById(@PathVariable Long id) {
        Optional<PGObject> existingRecord = alCityObjectInPGService.findById(id);
        if(existingRecord.isPresent()){
            try {
                alCityObjectInPGService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PGObject.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), ActionStatus.OK, existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }



}
