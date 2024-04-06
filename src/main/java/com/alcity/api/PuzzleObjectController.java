package com.alcity.api;


import com.alcity.dto.puzzle.PuzzleLevelDTO;
import com.alcity.dto.puzzle.PuzzleObjectDTO;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleObject;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.service.puzzle.PuzzleObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/p-object")
public class PuzzleObjectController {

    @Autowired
    private PuzzleObjectService puzzleObjectService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleObjectDTO getPuzzleObjectById(@PathVariable Long id) {
        PuzzleObjectDTO puzzleObjectDTO= new PuzzleObjectDTO();
        PuzzleObject po = new PuzzleObject();
        Optional<PuzzleObject> puzzleObject = puzzleObjectService.findById(id);
        if(puzzleObject.isPresent()) {
            po= puzzleObject.get();
            puzzleObjectDTO.setId(puzzleObject.get().getId());
            puzzleObjectDTO.setObjectCategory(po.getObjectCategory().getLabel());
            puzzleObjectDTO.setTitle(po.getTitle());
            puzzleObjectDTO.setVersion(po.getVersion());
        }
        return  puzzleObjectDTO;
    }


}
