package com.alcity.api;

import com.alcity.dto.Interpreter.object.RecordrData;
import com.alcity.dto.alobject.ActionRendererDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Renderer;
import com.alcity.service.alobject.ActionRendererService;
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
    private ActionRendererService actionRendererService;

    @Autowired
    private AttributeService attributeService;

    @Operation( summary = "Fetch all action renders  ",  description = "fetches all renderers for all actions")
    @GetMapping("/all")
    public Collection<ActionRendererDTO> getActionRenderers(Model model) {
        Collection<ActionRendererDTO> actionRendererDTOCollection = new ArrayList<ActionRendererDTO>();
        Collection<Renderer> actionRendererCollection = actionRendererService.findAll();
        Iterator<Renderer> iterator = actionRendererCollection.iterator();
        while(iterator.hasNext()){
            ActionRendererDTO actionRendererDTO = DTOUtil.getActionRendererDTO(iterator.next());
            actionRendererDTOCollection.add(actionRendererDTO);
        }
        return actionRendererDTOCollection;
    }

    @Operation( summary = "Fetch an action render by id  ",  description = "Fetch an action render by id")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActionRendererDTO getObjectActionRendererById(@PathVariable Long id) {
        Optional<Renderer> actionRendererOptional = actionRendererService.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getActionRendererDTO(actionRendererOptional.get());
        return null;
    }

    @Operation( summary = "Fetch all parameters fo a render by  rendere-id  ",  description = "Fetch all parameters fo a render by  rendere-id ")
    @RequestMapping(value = "/id/{id}/params", method = RequestMethod.GET)
    @ResponseBody
    public  Collection<RecordrData> getObjectActionRendererParameters(@PathVariable Long id) {
        Optional<Renderer> actionRendererOptional = actionRendererService.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getAttributeForOwnerById(attributeService,actionRendererOptional.get().getId(), AttributeOwnerType.Action_Renderer_Parameter);;
        return null;
    }


}
