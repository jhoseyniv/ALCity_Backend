package com.alcity.api;

import com.alcity.dto.puzzle.PuzzleObjectActionDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Al City Object used in different puzzle groups", description = "this part get actions and objects in a puzzle groups api")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/opg")

public class ALCityObjectInPGController {

    @Autowired
    private PuzzleObjectActionService puzzleObjectActionService;


    @Operation( summary = "Fetch all actions for an al city object that define in a puzzle group ",  description = "Fetch all actions for an al city object")
    @RequestMapping(value = "/id/{id}/actions", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleObjectActionDTO> getActionsForAObjectInPG(@PathVariable Long id) {
        Collection<PuzzleObjectActionDTO> puzzleObjectActionDTOS = new ArrayList<PuzzleObjectActionDTO>();
        Collection<PuzzleObjectAction> puzzleObjectActions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(id, POActionOwnerType.Puzzle_Group_Object);
        puzzleObjectActionDTOS = DTOUtil.getPuzzleObjectActionDTOS(puzzleObjectActions);
        return  puzzleObjectActionDTOS;
    }


}
