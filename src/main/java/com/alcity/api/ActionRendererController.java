package com.alcity.api;

import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.alobject.RendererDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.puzzle.BaseObject;
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

@Tag(name = "Action Renderer", description = "the Al City Action Renders Api ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/ar")
public class ActionRendererController {

    @Autowired
    private RendererService service;

    @Autowired
    private AttributeService attributeService;

    @Operation( summary = "Fetch all action renders ",  description = "fetches all renderers for all actions")
    @GetMapping("/all")
    public Collection<RendererDTO> getActionRenderers(Model model) {
        Collection<RendererDTO> rendererDTOS = new ArrayList<RendererDTO>();
        Collection<Renderer> renderers = service.findAll();
        Iterator<Renderer> iterator = renderers.iterator();
        while(iterator.hasNext()){
            RendererDTO rendererDTO = DTOUtil.getActionRendererDTO(iterator.next());
            rendererDTOS.add(rendererDTO);
        }
        return rendererDTOS;
    }
    @Operation( summary = "Save a Action Render Object  ",  description = "Save a Action Render  entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseObject save(@RequestBody RendererDTO dto) throws Exception  {
        Renderer savedRecord = null;
        ResponseObject response = new ResponseObject();
        Optional<Renderer> rendererOptional = service.findById(dto.getId());
        try{
            if (rendererOptional.isEmpty())
                savedRecord = service.save(dto,"Save");
            else
                savedRecord = service.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Renderer.class.getSimpleName() ,ActionStatus.Error , -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseObject(ErrorType.SaveSuccess, Renderer.class.getSimpleName() ,ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseObject(ErrorType.SaveFail, Renderer.class.getSimpleName() ,ActionStatus.Error, -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }

    @Operation( summary = "Fetch an action render by id  ",  description = "Fetch an action render by id")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RendererDTO getObjectActionRendererById(@PathVariable Long id) {
        Optional<Renderer> actionRendererOptional = service.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getActionRendererDTO(actionRendererOptional.get());
        return null;
    }

    @Operation( summary = "Delete a  Action renders ",  description = "delete a Action Render")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteActionRendersById(@PathVariable Long id) {
        Optional<Renderer> requestedRecord = service.findById(id);

        if(requestedRecord.isPresent()){
            try {
                    service.delete(requestedRecord.get());
                }
            catch (Exception e) {
                    return new ResponseObject(ErrorType.ForeignKeyViolation, Renderer.class.getSimpleName(),ActionStatus.Error, id,e.getCause().getMessage());
            }
            return new ResponseObject(ErrorType.SaveSuccess, Renderer.class.getSimpleName(),ActionStatus.OK, id,SystemMessage.DeleteMessage);
        }
        return  new ResponseObject(ErrorType.RecordNotFound,Renderer.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);

    }

    @Operation( summary = "Fetch all parameters fo a render by  rendere-id  ",  description = "Fetch all parameters fo a render by  rendere-id ")
    @RequestMapping(value = "/id/{id}/params", method = RequestMethod.GET)
    @ResponseBody
    public  Collection<AttributeData> getObjectActionRendererParameters(@PathVariable Long id) {
        Optional<Renderer> actionRendererOptional = service.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getAttributeForOwnerById(attributeService,actionRendererOptional.get().getId(), AttributeOwnerType.Action_Handler_Parameter);;
        return null;
    }


}
