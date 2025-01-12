package com.alcity.api;


import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.RecordNotFoundException;
import com.alcity.dto.puzzle.ALCityObjectInPGDTO;
import com.alcity.dto.puzzle.PuzzleObjectActionDTO;
import com.alcity.dto.puzzle.ALCityObjectDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.service.alobject.ObjectCategoryService;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.service.puzzle.ALCityObjectInPGService;
import com.alcity.service.puzzle.ALCityObjectService;
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

@Tag(name = "AL City Objects APIs", description = "Get ALCity Objects and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/co")  // city object=co
public class ALCityObjectController {

    @Autowired
    private ALCityObjectService alCityObjectService;
    @Autowired
    private ObjectCategoryService objectCategoryService;
    @Autowired
    private PuzzleObjectActionService puzzleObjectActionService;

    @Autowired
    private ALCityObjectInPGService alCityObjectInPGService;

    @Operation( summary = "Fetch an AL City Object  by id ",  description = "Fetch an AL City Object  by id  ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ALCityObjectDTO getALCityObject(@PathVariable Long id) {
        ALCityObjectDTO puzzleObjectDTO= new ALCityObjectDTO();
        Optional<ALCityObject> alCityObject = alCityObjectService.findById(id);
        if(alCityObject.isPresent())
            puzzleObjectDTO = DTOUtil.getALCityObjectDTO(alCityObject.get());
        else puzzleObjectDTO=null;
        return  puzzleObjectDTO;
    }
    @Operation( summary = "Fetch all AL City Objects ",  description = "Fetch all AL City Objects ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<ALCityObjectDTO> getALCityObjects(Model model) {
        Collection<ALCityObject> cityObjects = alCityObjectService.findAll();
        Collection<ALCityObjectDTO> dtos = new ArrayList<ALCityObjectDTO>();
        dtos =DTOUtil.getALCityObjectsDTOS(cityObjects);

        return dtos;
    }
    @Operation( summary = "Fetch all AL City Objects by Object Category ",  description = "Fetch all AL City Objects ")
    @GetMapping("/all/cat/id/{id}")
    @CrossOrigin(origins = "*")
    public Collection<ALCityObjectDTO> getALCityObjectsByCategory(@PathVariable Long id) {
        Optional<ObjectCategory> category = objectCategoryService.findById(id);
        Collection<ALCityObject> alCityObjects = alCityObjectService.findALCityObjectByObjectCategory(category.get());
        Collection<ALCityObjectDTO> alCityObjectDTOS = new ArrayList<ALCityObjectDTO>();
        alCityObjectDTOS =DTOUtil.getALCityObjectsDTOS(alCityObjects);
        return alCityObjectDTOS;
    }

    @Operation( summary = "Fetch all Puzzle Groups that this Object are in them ",  description = "Fetch all Puzzle Groups that this Object are in them ")
    @RequestMapping(value = "/id/{id}/pg", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public Collection<ALCityObjectInPGDTO> getPuzzleGroupsForALCityObject(@PathVariable Long id) {
        Collection<ALCityObjectInPGDTO> alCityObjectInPGDTOS = new ArrayList<ALCityObjectInPGDTO>();
        Optional<ALCityObject> alCityObjectOptional = alCityObjectService.findById(id);
        if(alCityObjectOptional.isPresent()) {
            Collection<ALCityObjectInPG> alCityObjects = alCityObjectInPGService.findByalCityObject(alCityObjectOptional.get());
            alCityObjectInPGDTOS = DTOUtil.getALCityObjectInPGDTOS(alCityObjects);
        }
        else throw new RecordNotFoundException(id,"alicty object","not found");

        return alCityObjectInPGDTOS;
    }
    @Operation( summary = "Save an AL City Object ",  description = "Save an AL City Object")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveALCityObject(@RequestBody ALCityObjectDTO dto)  {
        ALCityObject savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save

                savedRecord = alCityObjectService.save(dto,"Save");

            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = alCityObjectService.save(dto, "Edit");
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
    @Operation( summary = "delete an  AL City Object",  description = "delete an AL City Object entity and their data to data base")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteALCityObjectById(@PathVariable Long id) {
        Optional<ALCityObject> existingRecord = alCityObjectService.findById(id);
        if(existingRecord.isPresent()){
            alCityObjectService.delete(existingRecord.get());
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "Fetch all actions for an al city object ",  description = "Fetch all actions for an al city object")
    @RequestMapping(value = "/id/{id}/actions", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PuzzleObjectActionDTO> getALCityObjectActions(@PathVariable Long id) {
        Collection<PuzzleObjectActionDTO> puzzleObjectActionDTOS = new ArrayList<PuzzleObjectActionDTO>();
        Collection<PuzzleObjectAction> puzzleObjectActions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.ALCity_Object);
        puzzleObjectActionDTOS = DTOUtil.getPuzzleObjectActionDTOS(puzzleObjectActions);
        return  puzzleObjectActionDTOS;
    }
    @Operation( summary = "Fetch all Puzzle Groups for an al city object ",  description = "Fetch all Puzzle Groups for an al city object")
    @RequestMapping(value = "/id/{id}/pg/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ALCityObjectInPGDTO> getPuzzleGroupForALCityObject(@PathVariable Long id) {
        Collection<ALCityObjectInPGDTO> dtos = new ArrayList<ALCityObjectInPGDTO>();
        Optional<ALCityObject> alCityObjectOptional = alCityObjectService.findById(id);
        if(alCityObjectOptional.isEmpty()) return dtos;

        dtos = DTOUtil.getALCityObjectInPGDTOS(alCityObjectOptional.get().getAlCityObjectInPGCollection());
        return  dtos;
    }



}
