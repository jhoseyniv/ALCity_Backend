package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.alobject.RendererDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.Renderer;
import com.alcity.service.alobject.RendererService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Tag(name = "Action Renderer", description = "The Action Renders API ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/ar")
public class ActionController {

    @Autowired
    private RendererService service;

    @Autowired
    private AttributeService attributeService;


    @Operation( summary = "Fetch an action render by id  ",  description = "Fetch an action render by id")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RendererDTO get(@PathVariable Long id) {
        Optional<Renderer> rendererOptional = service.findById(id);
        return rendererOptional.map(DTOUtil::getRendererDTO).orElse(null);
    }

    @Operation( summary = "Fetch all action renders ",  description = "fetches all renderers for all actions")
    @GetMapping("/all")
    public Collection<RendererDTO> getAll(Model model) {
        Collection<Renderer> renderers = service.findAll();
        return DTOUtil.getRendererDTOS(renderers);
    }


    @Operation( summary = "Save a Action Render Object  ",  description = "Save a Action Render  entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage save(@RequestBody RendererDTO dto) throws Exception, ResponseObject {
        Renderer savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<Renderer> rendererOptional = service.findById(dto.getId());
        try{
            if (rendererOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,Renderer.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),Renderer.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),Renderer.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }


@Operation( summary = "Delete an  Action renders ",  description = "delete an Action Render")
@DeleteMapping("/del/{id}")
@CrossOrigin(origins = "*")
public ResponseMessage deleteRender(@PathVariable Long id) {
    Optional<Renderer> requestedRecord = service.findById(id);
        if(requestedRecord.isPresent()){
            try {
                    service.delete(requestedRecord.get());
                }
            catch (Exception e) {
                    throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), Renderer.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),Renderer.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), Renderer.class.getSimpleName(),  id,SystemMessage.RecordNotFound);

    }

    @Operation( summary = "Fetch all parameters for a render by  rendere-id  ",  description = "Fetch all parameters for a render by  rendere-id ")
    @RequestMapping(value = "/id/{id}/params", method = RequestMethod.GET)
    @ResponseBody
    public  Collection<AttributeData> getParameters(@PathVariable Long id) {
        Optional<Renderer> rendererOptional = service.findById(id);
        if(rendererOptional.isPresent())
            return  DTOUtil.getAttributeForOwnerById(attributeService,rendererOptional.get().getId(), AttributeOwnerType.Action_Handler_Parameter);;
        return null;
    }


}
