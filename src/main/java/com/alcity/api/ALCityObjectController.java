package com.alcity.api;


import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.search.ObjectSearchCriteriaDTO;
import com.alcity.dto.search.SearchResultCityObjectDTO;
import com.alcity.entity.alenum.DataType;
import com.alcity.utility.PLDTOUtil;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.RecordNotFoundException;
import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.service.alobject.ObjectCategoryService;
import com.alcity.service.alobject.ActionService;
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
import java.util.stream.Collectors;

@Tag(name = "AL City Objects APIs", description = "Get ALCity Objects and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/co")  // city object=co
public class ALCityObjectController {

    @Autowired
    private ALCityObjectService objectService;
    @Autowired
    private ObjectCategoryService objectCategoryService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ALCityObjectInPGService alCityObjectInPGService;

    @Operation( summary = "Fetch an AL City Object  by id ",  description = "Fetch an AL City Object  by id  ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public CityObjectDTO getALCityObject(@PathVariable Long id) {
        CityObjectDTO dto= new CityObjectDTO();
        Optional<ALCityObject> objectOptional = objectService.findById(id);
        if(objectOptional.isPresent()) {
            ALCityObject object = objectOptional.get();
            dto = PLDTOUtil.getCityObjectDTO(object,actionService,attributeService);
        }
        return  dto;
    }
    @Operation( summary = "Fetch all AL City Objects ",  description = "Fetch all AL City Objects ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<CityObjectDTO> getALCityObjects(Model model) {
        Collection<ALCityObject> objects = objectService.findAll();
        Collection<CityObjectDTO> dtos = new ArrayList<CityObjectDTO>();
        dtos =PLDTOUtil.getCityObjectsDTOS(objects,actionService,attributeService);
        return dtos;
    }

    @Operation( summary = "Fetch all AL City Objects by Object Category ",  description = "Fetch all AL City Objects ")
    @GetMapping("/all/cat/id/{id}")
    @CrossOrigin(origins = "*")
    public Collection<CityObjectDTO> getALCityObjectsByCategory(@PathVariable Long id) {
        Optional<ObjectCategory> category = objectCategoryService.findById(id);
        Collection<ALCityObject> objects = objectService.findALCityObjectByObjectCategory(category.get());
        Collection<CityObjectDTO> objectDTOS = new ArrayList<CityObjectDTO>();
        objectDTOS =PLDTOUtil.getCityObjectsDTOS(objects,actionService,attributeService);
        return objectDTOS;
    }
    @Operation( summary = "Fetch all AL City Objects by Object Category and puzzle group and search title ",  description = "Fetch all AL City Objects by Object Category and puzzle group and search title")
    @PostMapping("/search")
    @CrossOrigin(origins = "*")
    public Collection<CityObjectDTO> getALCityObjectsByCriteria(@RequestBody ObjectSearchCriteriaDTO criteriaDTO) {
        Collection<ALCityObject> objects = objectService.searchCityObjectSByCriteria(criteriaDTO);
        Collection<CityObjectDTO> objectDTOS = new ArrayList<CityObjectDTO>();
        objectDTOS =PLDTOUtil.getCityObjectsDTOS(objects,actionService,attributeService);
        return objectDTOS;
    }
    @Operation( summary = "Fetch all AL City Objects in a puzzle group by  title and category ",  description = "Fetch all AL City Objects in a puzzle group by  title and category")
    @PostMapping("/search-in-pg")
    @CrossOrigin(origins = "*")
    public Collection<SearchResultCityObjectDTO> getALCityObjectsInAPGByCriteria(@RequestBody ObjectSearchCriteriaDTO criteriaDTO) {
        Collection<SearchResultCityObjectDTO> results = objectService.searchCityObjectInPGByCriteria(criteriaDTO);
        return results;
    }


    @Operation( summary = "Fetch all Puzzle Groups that this Object are in them ",  description = "Fetch all Puzzle Groups that this Object are in them ")
    @RequestMapping(value = "/id/{id}/pg", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public Collection<CityObjectInPGDTO> getPuzzleGroupsForALCityObject(@PathVariable Long id) {
        Collection<CityObjectInPGDTO> alCityObjectInPGDTOS = new ArrayList<CityObjectInPGDTO>();
        Optional<ALCityObject> alCityObjectOptional = objectService.findById(id);
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
    public ALCityResponseObject saveALCityObject(@RequestBody CityObjectDTO dto)  {
        ALCityObject savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save

                savedRecord = objectService.save(dto,"Save");

            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = objectService.save(dto, "Edit");
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
        Optional<ALCityObject> existingRecord = objectService.findById(id);
        if(existingRecord.isPresent()){
            objectService.delete(existingRecord.get());
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "Fetch all actions for an al city object ",  description = "Fetch all actions for an al city object")
    @RequestMapping(value = "/id/{id}/actions", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ActionDTO> getALCityObjectActions(@PathVariable Long id) {
        Collection<ActionDTO> actionDTOS = new ArrayList<ActionDTO>();
        Collection<ObjectAction> actions = actionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.ALCity_Object);
        actionDTOS = PLDTOUtil.getActionDTOS(actions);
        return  actionDTOS;
    }

    @Operation( summary = "Fetch all attributes for an al city object ",  description = "Fetch all attributes for an al city object")
    @RequestMapping(value = "/id/{id}/atts", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getALCityObjectAttributes(@PathVariable Long id) {
        Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
        Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(id, AttributeOwnerType.AlCity_Object);
        dtos = DTOUtil.getAttributesDTOS(attributes);
        return  dtos;
    }

    @Operation( summary = "Fetch all attributes for an al city object by attribute content type () ",  description = "Fetch all attributes for an al city object")
    @RequestMapping(value = "/id/{id}/atts/contents", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getALCityObjectAttributesByContent(@PathVariable Long id) {
        Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
        Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(id, AttributeOwnerType.AlCity_Object);
        dtos = DTOUtil.getAttributesDTOS(attributes);
        dtos = dtos.stream().filter(dto -> dto.getDataType().equalsIgnoreCase(DataType.Binary.name())).collect(Collectors.toList());
        return  dtos;
    }

    @Operation( summary = "Fetch all Puzzle Groups for an al city object ",  description = "Fetch all Puzzle Groups for an al city object")
    @RequestMapping(value = "/id/{id}/pg/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<CityObjectInPGDTO> getPuzzleGroupForALCityObject(@PathVariable Long id) {
        Collection<CityObjectInPGDTO> dtos = new ArrayList<CityObjectInPGDTO>();
        Optional<ALCityObject> alCityObjectOptional = objectService.findById(id);
        if(alCityObjectOptional.isEmpty()) return dtos;

        dtos = DTOUtil.getALCityObjectInPGDTOS(alCityObjectOptional.get().getAlCityObjectInPGCollection());
        return  dtos;
    }



}
