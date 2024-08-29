package com.alcity.api;


import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.Interpreter.object.RecordData;
import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.puzzle.ALCityObjectDTO;
import com.alcity.dto.puzzle.PuzzleObjectActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "ALCity Object Action APIs", description = "Get AL City  Object actions and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/poa")

public class PuzzleObjectActionController {
    @Autowired
    private PuzzleObjectActionService puzzleObjectActionService;


    @Autowired
    AttributeService attributeService;

    @Operation( summary = "Delete a Puzzle Object Action ",  description = "Delete a Puzzle Object Action ")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleObjectActionById(@PathVariable Long id) {
        Optional<PuzzleObjectAction> existingRecord = puzzleObjectActionService.findById(id);
        if(existingRecord.isPresent()){
            try {
                puzzleObjectActionService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getObjectAction().name(), existingRecord.get().getId(), PuzzleGroup.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "Fetch all actions for an alcity object by id  ",  description = "Fetch all actions for an alcity object by id ")
    @RequestMapping(value = "/obj/id/{id}/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PuzzleObjectActionDTO> getActionsForAALCityObject(@PathVariable Long id) {
        Collection<PuzzleObjectAction> puzzleObjectActions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.ALCity_Object);
        Collection<PuzzleObjectActionDTO> dtos= DTOUtil.getPuzzleObjectActionDTOS(puzzleObjectActions);
        return dtos;
    }
    @Operation( summary = "Fetch all parameters for an action defined in a al city object by id  ",  description = "Fetch all parameters for an action defined in a al city object by id")
    @RequestMapping(value = "/action/id/{id}/param/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getParamsForanALCityObjectAction(@PathVariable Long id) {
        Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(id, AttributeOwnerType.AlCity_Object);
        Collection<AttributeDTO> dtos= DTOUtil.getAttributesDTOS(attributes);
        return dtos;
    }

    @Operation( summary = "Save a Puzzle Object Action... ",  description = "Save a Puzzle Object Action...")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleObjectAction(@RequestBody PuzzleObjectActionDTO dto)  {
        PuzzleObjectAction savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = puzzleObjectActionService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getObjectAction().toString(), dto.getId(), "title must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = puzzleObjectActionService.save(dto, "Edit");
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
