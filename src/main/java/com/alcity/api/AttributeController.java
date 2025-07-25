package com.alcity.api;

import com.alcity.dto.alobject.AttributeDTOSave;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.service.alobject.AttributeService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Attribute Entity  API's ", description = "Get Attributes API for ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/att")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @Operation( summary = "Fetch an Attribute data by a Id ",  description = "fetches all data for a Attribute")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public AttributeDTO getAttributeById(@PathVariable Long id) {
        AttributeDTO attributeDTO= new AttributeDTO();
        Optional<Attribute> attributeOptional = attributeService.findById(id);
        if(attributeOptional.isEmpty()) return null;
        attributeDTO = DTOUtil.getAttributeDTO(attributeOptional.get());
        return attributeDTO;
    }

    @Operation( summary = "Save an Attribute Entity ",  description = "Save an Attribute Entity...")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveAttribute(@RequestBody AttributeDTOSave dto)  {
        Attribute savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = attributeService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + Attribute.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = attributeService.save(dto, "Edit");
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

    @Operation( summary = "Save an Attribute Collection ",  description = "Save an Attribute collection Entity...")
    @PostMapping("/save/all")
    @CrossOrigin(origins = "*")
    public Collection<ALCityResponseObject> saveAllAttribute(@RequestBody Collection<AttributeDTOSave> dtos)  {
        Collection<ALCityResponseObject> responseObject = new ArrayList<>();
        Collection<ALCityResponseObject> responseObjects = attributeService.saveAll(dtos);
        return responseObjects;
    }

    @Operation( summary = "delete an Attribute with all values",  description = "delete an Attribute with all values from database")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteAttributeById(@PathVariable Long id) {
        Optional<Attribute> existingRecord = attributeService.findById(id);
        if(existingRecord.isPresent()){
            try {
                attributeService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", Attribute.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }


  @Operation( summary = "Fetch all attributes for an owner by id and type ",  description = "Fetch all attributes for an owner by id and type ")
  @RequestMapping(value = "/owner/{id}/type/{type}", method = RequestMethod.GET)
  @ResponseBody
  @CrossOrigin(origins = "*")
  public Collection<AttributeDTO> getAttributesByOwnerIdAndOwnerType(@PathVariable Long id,@PathVariable String type) {
      Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(id,AttributeOwnerType.getByTitle(type));
      Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
      dtos = DTOUtil.getAttributesDTOS(attributes);
      return  dtos;
  }




}
