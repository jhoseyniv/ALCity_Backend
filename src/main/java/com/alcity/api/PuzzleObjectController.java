package com.alcity.api;


import com.alcity.dto.puzzle.PuzzleObject_ObjectActionDTO;
import com.alcity.dto.puzzle.PuzzleObjectDTO;
import com.alcity.entity.alobject.PuzzleObject_ObjectAction;
import com.alcity.entity.puzzle.PuzzleObject;
import com.alcity.service.alobject.PuzzleObject_ObjectActionService;
import com.alcity.service.puzzle.PuzzleObjectService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/po")
public class PuzzleObjectController {

    @Autowired
    private PuzzleObjectService puzzleObjectService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleObjectDTO getPuzzleObjectById(@PathVariable Long id) {
        PuzzleObjectDTO puzzleObjectDTO= new PuzzleObjectDTO();
        Optional<PuzzleObject> puzzleObject = puzzleObjectService.findById(id);
        puzzleObjectDTO = DTOUtil.getPuzzleObjectById(puzzleObject);
        return  puzzleObjectDTO;
    }
    @GetMapping("/all")
    public Collection<PuzzleObjectDTO> getPuzzleObject(Model model) {
        Collection<PuzzleObject> puzzleObjectCollection = puzzleObjectService.findAll();
        Collection<PuzzleObjectDTO> puzzleObjectDTOCollection = new ArrayList<PuzzleObjectDTO>();
        puzzleObjectDTOCollection =DTOUtil.getPuzzleObjects(puzzleObjectCollection);

        return puzzleObjectDTOCollection;
    }

    @Autowired
    private PuzzleObject_ObjectActionService puzzleObject_objectActionService;

    @GetMapping("/action/all")
    public Collection<PuzzleObject_ObjectActionDTO> getPuzzleObjectActions(Model model) {
        Collection<PuzzleObject_ObjectAction> puzzleObject_objectActionCollection = puzzleObject_objectActionService.findAll();
        Collection<PuzzleObject_ObjectActionDTO> puzzleObject_objectActionDTOCollection = new ArrayList<PuzzleObject_ObjectActionDTO>();
        puzzleObject_objectActionDTOCollection =DTOUtil.getPuzzleObjectActions(puzzleObject_objectActionCollection);

        return puzzleObject_objectActionDTOCollection;
    }

}
