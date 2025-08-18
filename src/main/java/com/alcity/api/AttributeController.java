package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.alobject.AttributeDTOSave;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.Attribute;
import com.alcity.service.alobject.AttributeService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AttributeService service;

    @Operation( summary = "Fetch an Attribute data by a Id ",  description = "fetches all data for a Attribute")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public AttributeDTO getAttributeById(@PathVariable Long id) {
        AttributeDTO attributeDTO= new AttributeDTO();
        Optional<Attribute> attributeOptional = service.findById(id);
        if(attributeOptional.isEmpty()) return null;
        attributeDTO = DTOUtil.getAttributeDTO(attributeOptional.get());
        return attributeDTO;
    }

    @Operation( summary = "Save an Attribute Entity ",  description = "Save an Attribute Entity...")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveAttribute(@RequestBody AttributeDTOSave dto) throws ResponseObject {
        Attribute savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<Attribute> attributeOptional = service.findById(dto.getId());
        try{
            if (attributeOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , Attribute.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,  Status.ok.name(),Attribute.class.getSimpleName() , savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),Attribute.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

    }

    @Operation( summary = "Save an Attribute Collection ",  description = "Save an Attribute collection Entity...")
    @PostMapping("/save/all")
    @CrossOrigin(origins = "*")
    public Collection<ResponseObject> saveAllAttribute(@RequestBody Collection<AttributeDTOSave> dtos)  {
        Collection<ResponseObject> responseObject = new ArrayList<>();
        Collection<ResponseObject> responseObjects = service.saveAll(dtos);
        return responseObjects;
    }

    @Operation( summary = "delete an Attribute with all values",  description = "delete an Attribute with all values from database")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteAttributeById(@PathVariable Long id) {
        Optional<Attribute>  requestedRecord = service.findById(id);
        if(requestedRecord.isPresent()){
            try {
                service.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), Attribute.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess,Status.ok.name(), Attribute.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(),Attribute.class.getSimpleName(), id,SystemMessage.RecordNotFound);
    }


  @Operation( summary = "Fetch all attributes for an owner by id and type ",  description = "Fetch all attributes for an owner by id and type ")
  @RequestMapping(value = "/owner/{id}/type/{type}", method = RequestMethod.GET)
  @ResponseBody
  @CrossOrigin(origins = "*")
  public Collection<AttributeDTO> getAttributesByOwnerIdAndOwnerType(@PathVariable Long id,@PathVariable String type) {
      Collection<Attribute> attributes = service.findByOwnerIdAndAttributeOwnerTypeNew(id,AttributeOwnerType.getByTitle(type));
      Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
      dtos = DTOUtil.getAttributesDTOS(attributes);
      return  dtos;
  }




}
