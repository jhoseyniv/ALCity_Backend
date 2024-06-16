package com.alcity.api;


import com.alcity.dto.Interpreter.object.RecordrData;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation( summary = "Fetch all parameters for a action  by id  ",  description = "Fetch all parameters fo a action by id ")
    @RequestMapping(value = "/id/{id}/params", method = RequestMethod.GET)
    @ResponseBody
    public Collection<RecordrData> getParametersForAction(@PathVariable Long id) {
        Optional<PuzzleObjectAction> puzzleObjectActionOptional = puzzleObjectActionService.findById(id);
        PuzzleObjectAction puzzleObjectAction = new PuzzleObjectAction();
        if(puzzleObjectActionOptional.isPresent()) {
            puzzleObjectAction = puzzleObjectActionOptional.get();
            Renderer actionRenderer = puzzleObjectAction.getActionRenderer();
            if(actionRenderer != null)
                    return  DTOUtil.getAttributeForOwnerById(attributeService,actionRenderer.getId(), AttributeOwnerType.Action_Renderer_Parameter);
            else
                return null;
        }
        return null;
    }

}
