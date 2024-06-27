package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.Interpreter.object.RecordrData;
import com.alcity.dto.alobject.RendererDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.alobject.RendererService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private RendererService rendererService;

    @Autowired
    private AttributeService attributeService;

    @Operation( summary = "Fetch all action renders ",  description = "fetches all renderers for all actions")
    @GetMapping("/all")
    public Collection<RendererDTO> getActionRenderers(Model model) {
        Collection<RendererDTO> rendererDTOS = new ArrayList<RendererDTO>();
        Collection<Renderer> renderers = rendererService.findAll();
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
    public ALCityResponseObject saveActionRenders(@RequestBody RendererDTO dto)  {
        Renderer savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = rendererService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getHandler(), dto.getId(), "Handler Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = rendererService.save(dto, "Edit");
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

    @Operation( summary = "Fetch an action render by id  ",  description = "Fetch an action render by id")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RendererDTO getObjectActionRendererById(@PathVariable Long id) {
        Optional<Renderer> actionRendererOptional = rendererService.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getActionRendererDTO(actionRendererOptional.get());
        return null;
    }

    @Operation( summary = "Delete a  Action renders ",  description = "delete a Action Render")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteActionRendersById(@PathVariable Long id) {
        Optional<Renderer> existingRecord = rendererService.findById(id);
        if(existingRecord.isPresent()){
            try {
                rendererService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getHandler(), existingRecord.get().getId(), Renderer.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "Fetch all parameters fo a render by  rendere-id  ",  description = "Fetch all parameters fo a render by  rendere-id ")
    @RequestMapping(value = "/id/{id}/params", method = RequestMethod.GET)
    @ResponseBody
    public  Collection<RecordrData> getObjectActionRendererParameters(@PathVariable Long id) {
        Optional<Renderer> actionRendererOptional = rendererService.findById(id);
        if(actionRendererOptional.isPresent())
            return  DTOUtil.getAttributeForOwnerById(attributeService,actionRendererOptional.get().getId(), AttributeOwnerType.Action_Renderer_Parameter);;
        return null;
    }


}
