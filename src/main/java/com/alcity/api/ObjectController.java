package com.alcity.api;


import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.search.ObjectSearchCriteriaDTO;
import com.alcity.dto.search.SearchResultCityObjectDTO;
import com.alcity.entity.alenum.*;
import com.alcity.utility.PLDTOUtil;
import com.alcity.entity.alobject.Attribute;
import com.alcity.service.alobject.AttributeService;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.RecordNotFoundException;
import com.alcity.dto.puzzle.PGObjectDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.service.alobject.ObjectCategoryService;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.puzzle.PGObjectService;
import com.alcity.service.puzzle.BaseObjectService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ObjectController {

    @Autowired
    private BaseObjectService service;
    @Autowired
    private ObjectCategoryService objectCategoryService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private AttributeService attributeService;

    @Autowired
    private PGObjectService alCityObjectInPGService;

    @Operation( summary = "Fetch an AL City Object  by id ",  description = "Fetch an AL City Object  by id  ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public CityObjectDTO getALCityObject(@PathVariable Long id) {
        CityObjectDTO dto= new CityObjectDTO();
        Optional<BaseObject> objectOptional = service.findById(id);
        if(objectOptional.isPresent()) {
            BaseObject object = objectOptional.get();
            dto = PLDTOUtil.getCityObjectDTO(object,actionService,attributeService);
        }
        return  dto;
    }
    @Operation( summary = "Fetch all AL City Objects ",  description = "Fetch all AL City Objects ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<CityObjectDTO> getALCityObjects(Model model) {
        Collection<BaseObject> objects = service.findAll();
        Collection<CityObjectDTO> dtos = new ArrayList<CityObjectDTO>();
        dtos =PLDTOUtil.getCityObjectsDTOS(objects,actionService,attributeService);
        return dtos;
    }

    @Operation( summary = "Fetch all AL City Objects by Object Category ",  description = "Fetch all AL City Objects ")
    @GetMapping("/all/cat/id/{id}")
    @CrossOrigin(origins = "*")
    public Collection<CityObjectDTO> getALCityObjectsByCategory(@PathVariable Long id) {
        Optional<ObjectCategory> category = objectCategoryService.findById(id);
        Collection<BaseObject> objects = service.findALCityObjectByObjectCategory(category.get());
        Collection<CityObjectDTO> objectDTOS = new ArrayList<CityObjectDTO>();
        objectDTOS =PLDTOUtil.getCityObjectsDTOS(objects,actionService,attributeService);
        return objectDTOS;
    }
    @Operation( summary = "Fetch all AL City Objects by Object Category and puzzle group and search title ",  description = "Fetch all AL City Objects by Object Category and puzzle group and search title")
    @PostMapping("/search")
    @CrossOrigin(origins = "*")
    public Collection<CityObjectDTO> getALCityObjectsByCriteria(@RequestBody ObjectSearchCriteriaDTO criteriaDTO) {
        Collection<BaseObject> objects = service.searchCityObjectSByCriteria(criteriaDTO);
        Collection<CityObjectDTO> objectDTOS = new ArrayList<CityObjectDTO>();
        objectDTOS =PLDTOUtil.getCityObjectsDTOS(objects,actionService,attributeService);
        return objectDTOS;
    }
    @Operation( summary = "Fetch all AL City Objects in a puzzle group by  title and category ",  description = "Fetch all AL City Objects in a puzzle group by  title and category")
    @PostMapping("/search-in-pg")
    @CrossOrigin(origins = "*")
    public Collection<SearchResultCityObjectDTO> getALCityObjectsInAPGByCriteria(@RequestBody ObjectSearchCriteriaDTO criteriaDTO) {
        Collection<SearchResultCityObjectDTO> results = service.searchCityObjectInPGByCriteria(criteriaDTO);
        return results;
    }


    @Operation( summary = "Fetch all Puzzle Groups that this Object are in them ",  description = "Fetch all Puzzle Groups that this Object are in them ")
    @RequestMapping(value = "/id/{id}/pg", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public Collection<PGObjectDTO> getPuzzleGroupsForALCityObject(@PathVariable Long id) {
        Collection<PGObjectDTO> alCityObjectInPGDTOS = new ArrayList<PGObjectDTO>();
        Optional<BaseObject> alCityObjectOptional = service.findById(id);
        if(alCityObjectOptional.isPresent()) {
            Collection<PGObject> alCityObjects = alCityObjectInPGService.findByalCityObject(alCityObjectOptional.get());
            alCityObjectInPGDTOS = DTOUtil.getALCityObjectInPGDTOS(alCityObjects);
        }
        else throw new RecordNotFoundException(id,"alicty object","not found");

        return alCityObjectInPGDTOS;
    }
    @Operation( summary = "Save an Algoopia Object ",  description = "Save an Algoopia Object")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseObject save(@RequestBody CityObjectDTO dto) throws ResponseObject {
        BaseObject savedRecord = null;
        ResponseObject response = new ResponseObject();
        Optional<BaseObject> baseObjectOptional = service.findById(dto.getId());
        try{
               if (baseObjectOptional.isEmpty())
                   savedRecord = service.save(dto,"Save");
               else
                   savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,BaseObject.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseObject(ErrorType.SaveSuccess,Status.ok.name(), BaseObject.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseObject(ErrorType.SaveFail,Status.ok.name(), BaseObject.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }

    @Operation( summary = "delete an Algoopia Object",  description = "delete an Algoopia Object entity and their data to data base")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject delete(@PathVariable Long id) {
        Optional<BaseObject>  requestedRecord = service.findById(id);
        if(requestedRecord.isPresent()){
            try {
                service.delete(requestedRecord.get());
            }
            catch (Exception e) {
                return new ResponseObject(ErrorType.ForeignKeyViolation, BaseObject.class.getSimpleName(), Status.error.name(), id,e.getCause().getMessage());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, BaseObject.class.getSimpleName(), Status.ok.name(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseObject(ErrorType.RecordNotFound,BaseObject.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Fetch all actions for an al city object ",  description = "Fetch all actions for an al city object")
    @RequestMapping(value = "/id/{id}/actions/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ActionDTO> getALCityObjectActions(@PathVariable Long id) {
        Collection<ActionDTO> actionDTOS = new ArrayList<ActionDTO>();
        Collection<ObjectAction> actions = actionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.Object);
        actionDTOS = PLDTOUtil.getActionDTOS(actions);
        return  actionDTOS;
    }

    @Operation( summary = "Fetch all attributes for an al city object ",  description = "Fetch all attributes for an al city object")
    @RequestMapping(value = "/id/{id}/atts", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getALCityObjectProperties(@PathVariable Long id) {
        Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
        Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(id, AttributeOwnerType.Object_Property);
        dtos = DTOUtil.getAttributesDTOS(attributes);
        return  dtos;
    }

    @Operation( summary = "Fetch all properties for a city object by attribute content type () ",  description = "Fetch all attributes for a city object")
    @RequestMapping(value = "/id/{id}/atts/contents", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AttributeDTO> getALCityObjectPropertiesByContent(@PathVariable Long id) {
        Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
        Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(id, AttributeOwnerType.Object_Property);
        dtos = DTOUtil.getAttributesDTOS(attributes);
        dtos = dtos.stream().filter(dto -> dto.getDataType().equalsIgnoreCase(DataType.Binary.name())).collect(Collectors.toList());
        return  dtos;
    }

    @Operation( summary = "Fetch all Puzzle Groups for an al city object ",  description = "Fetch all Puzzle Groups for an al city object")
    @RequestMapping(value = "/id/{id}/pg/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PGObjectDTO> getPuzzleGroupForALCityObject(@PathVariable Long id) {
        Collection<PGObjectDTO> dtos = new ArrayList<PGObjectDTO>();
        Optional<BaseObject> alCityObjectOptional = service.findById(id);
        if(alCityObjectOptional.isEmpty()) return dtos;

        dtos = DTOUtil.getALCityObjectInPGDTOS(alCityObjectOptional.get().getAlCityObjectInPGCollection());
        return  dtos;
    }



}
