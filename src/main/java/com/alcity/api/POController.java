package com.alcity.api;


import com.alcity.dto.puzzle.PuzzleObject_ObjectActionDTO;
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
public class POController {

    @Autowired
    private ALCityObjectService alCityObjectService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ALCityObjectDTO getPuzzleObjectById(@PathVariable Long id) {
        ALCityObjectDTO puzzleObjectDTO= new ALCityObjectDTO();
        Optional<ALCityObject> puzzleObject = alCityObjectService.findById(id);
        puzzleObjectDTO = DTOUtil.getPuzzleObjectById(puzzleObject);
        return  puzzleObjectDTO;
    }

    @GetMapping("/all")
    public Collection<ALCityObjectDTO> getPuzzleObject(Model model) {
        Collection<ALCityObject> alCityObjects = alCityObjectService.findAll();
        Collection<ALCityObjectDTO> puzzleObjectDTOCollection = new ArrayList<ALCityObjectDTO>();
        puzzleObjectDTOCollection =DTOUtil.getPuzzleObjects(alCityObjects);

        return puzzleObjectDTOCollection;
    }

    @Autowired
    private PuzzleObjectActionService puzzleObject_objectActionService;

    @GetMapping("/action/all")
    public Collection<PuzzleObject_ObjectActionDTO> getPuzzleObjectActions(Model model) {
        Collection<PuzzleObjectAction> puzzleObject_objectActionCollection = puzzleObject_objectActionService.findAll();
        Collection<PuzzleObject_ObjectActionDTO> puzzleObject_objectActionDTOCollection = new ArrayList<PuzzleObject_ObjectActionDTO>();
        puzzleObject_objectActionDTOCollection =DTOUtil.getPuzzleObjectActions(puzzleObject_objectActionCollection);

        return puzzleObject_objectActionDTOCollection;
    }

    @RequestMapping(value = "/action/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleObject_ObjectActionDTO getPuzzleObjectActionById(@PathVariable Long id) {
        PuzzleObject_ObjectActionDTO puzzleObject_objectActionDTO= new PuzzleObject_ObjectActionDTO();
        Optional<PuzzleObjectAction> puzzleObject_objectActionOptional = puzzleObject_objectActionService.findById(id);
        puzzleObject_objectActionDTO = DTOUtil.getPuzzleObjectAction(puzzleObject_objectActionOptional);
        return  puzzleObject_objectActionDTO;
    }



}
