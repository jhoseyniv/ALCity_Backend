package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PGObjectDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
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
    private PGObjectService pgObjectService;

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
        Optional<PGObject> pgObjectOptional = pgObjectService.findById(id);
        if(pgObjectOptional.isPresent()) return null;
        return DTOUtil.getPGObjectDTO(pgObjectOptional.get());
    }

    @Operation( summary = "Add a Object to a Puzzle Group ",  description = "Add a Object to a Puzzle Group ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveALCityObjectInPG(@RequestBody PGObjectDTO dto)  {
        PGObject savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PGObject> pgObjectOptional = pgObjectService.findById(dto.getId());
        try{
            if (pgObjectOptional.isEmpty())
                savedRecord = pgObjectService.save(dto,"Save");
            else
                savedRecord = pgObjectService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,PGObject.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PGObject.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),PGObject.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }

    @Operation( summary = "Remove a Object From a Puzzle Group",  description = "Remove an  AL City Object From a Puzzle Group")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteALCityObjectInPGById(@PathVariable Long id) {
        Optional<PGObject> requestedRecord = pgObjectService.findById(id);

        if(requestedRecord.isPresent()){
            try {
                pgObjectService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), PGObject.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PGObject.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), PGObject.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
     }



}
