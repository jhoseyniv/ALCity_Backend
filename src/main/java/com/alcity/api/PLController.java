package com.alcity.api;

import com.alcity.customexception.NotNullConstraintException;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Tag(name = "Puzzle Level and related Entities ", description = "Get Puzzle Levels data Format for other systems...")
@RestController
@RequestMapping("/pl")
public class PLController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private PLObjectiveService plObjectiveService;

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
    @Operation( summary = "Save a puzzle level  Objective  ",  description = "save a puzzle level  objective entity and their data to data base")
    @PostMapping("/id/{id}/save")
    public PLObjective savePLObjective(@RequestBody PLObjectiveDTO plObjectiveDTO,Long id)  {
        PLObjective savedObjective = null;

        try {
            savedObjective = plObjectiveService.save(plObjectiveDTO);
        }catch (RuntimeException e )
        {
            throw new UniqueConstraintException(plObjectiveDTO.getTitle(), plObjectiveDTO.getId(), PLObjectiveDTO.class.toString());
        }
        Optional<PLObjective> output = plObjectiveService.findById(savedObjective.getId());
        return output.get();
    }



}
