package com.alcity.api;

import com.alcity.dto.puzzle.*;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Tag(name = "Puzzle Level and related Entities ", description = "Get Puzzle Levels data Format for Other Database")
@RestController
@RequestMapping("/pl")
public class PLController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @Operation( summary = "Fetch all puzzle level data ",  description = "fetches all data for all puzzle level structure ")
    @GetMapping("/all")
    public Collection<PuzzleLevelLDTO> getPuzzleLevels(Model model) {
        Collection<PuzzleLevelLDTO> plDTOCollection = new ArrayList<PuzzleLevelLDTO>();
        Collection<PuzzleLevel> plCollection = puzzleLevelService.findAll();
        Iterator<PuzzleLevel> itr = plCollection.iterator();
        while(itr.hasNext()){
            PuzzleLevelLDTO puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(itr.next());
            plDTOCollection.add(puzzleLevelDTO);
        }
        return plDTOCollection;
    }

    @Operation( summary = "Fetch puzzle level data by a Id ",  description = "fetches all data for a puzzle level ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleLevelLDTO getPuzzleLevelById(@PathVariable Long id) {
        PuzzleLevelLDTO puzzleLevelDTO= new PuzzleLevelLDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevelOptional);
        return puzzleLevelDTO;
    }
    @Operation( summary = "Fetch all objective by a puzzle level Id ",  description = "fetches all objectives for a puzzle level ")
    @RequestMapping(value = "/id/{id}/objectives/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PLObjectiveDTO> getAllObjectivesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection= new ArrayList<PLObjectiveDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
                plObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
        return plObjectiveDTOCollection;
    }
//    @RequestMapping(value = "/objectives/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Collection<PLObjectiveDTO> getObjectivesByPuzzleLevelId(@PathVariable Long id) {
//        Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection= new ArrayList<PLObjectiveDTO>();
//        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
//        if(puzzleLevelOptional.isPresent())
//            puzzleLevelObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
//        return puzzleLevelObjectiveDTOCollection;
//    }



}
