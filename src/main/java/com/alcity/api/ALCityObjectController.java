package com.alcity.api;


import com.alcity.dto.puzzle.PuzzleObjectActionDTO;
import com.alcity.dto.puzzle.ALCityObjectDTO;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.service.puzzle.ALCityObjectService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Object APIs", description = "Get Puzzle Object and related entities as rest api")
@RestController
@RequestMapping("/po")
public class ALCityObjectController {

    @Autowired
    private ALCityObjectService alCityObjectService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ALCityObjectDTO getALCityObjectById(@PathVariable Long id) {
        ALCityObjectDTO puzzleObjectDTO= new ALCityObjectDTO();
        Optional<ALCityObject> alCityObject = alCityObjectService.findById(id);
        if(alCityObject.isPresent())
            puzzleObjectDTO = DTOUtil.getALCityObjectDTO(alCityObject.get());
        else puzzleObjectDTO=null;
        return  puzzleObjectDTO;
    }

    @GetMapping("/all")
    public Collection<ALCityObjectDTO> getALLALCityObjects(Model model) {
        Collection<ALCityObject> alCityObjects = alCityObjectService.findAll();
        Collection<ALCityObjectDTO> alCityObjectDTOS = new ArrayList<ALCityObjectDTO>();
        alCityObjectDTOS =DTOUtil.getALCityObjectsDTOS(alCityObjects);

        return alCityObjectDTOS;
    }

    @Autowired
    private PuzzleObjectActionService puzzleObject_objectActionService;

    @GetMapping("/action/all")
    public Collection<PuzzleObjectActionDTO> getPuzzleObjectActions(Model model) {
        Collection<PuzzleObjectAction> puzzleObjectActions = puzzleObject_objectActionService.findAll();
        Collection<PuzzleObjectActionDTO> puzzleObjectActionDTOS = new ArrayList<PuzzleObjectActionDTO>();
        puzzleObjectActionDTOS =DTOUtil.getPuzzleObjectActionDTOS(puzzleObjectActions);

        return puzzleObjectActionDTOS;
    }

    @RequestMapping(value = "/action/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleObjectActionDTO getPuzzleObjectActionById(@PathVariable Long id) {
        PuzzleObjectActionDTO puzzleObjectActionDTO= new PuzzleObjectActionDTO();
        Optional<PuzzleObjectAction> puzzleObjectActionOptional = puzzleObject_objectActionService.findById(id);
        if(puzzleObjectActionOptional.isPresent())
                    puzzleObjectActionDTO = DTOUtil.getPuzzleObjectActionDTO(puzzleObjectActionOptional.get());
        else puzzleObjectActionDTO=null;
        return  puzzleObjectActionDTO;
    }



}
