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


@Tag(name = "Puzzle Level API's ", description = "Get Puzzle Levels data Format for other systems...")
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

    @Operation( summary = "Fetch all Instances by a puzzle level Id ",  description = "fetches all objectives for a puzzle level ")
    @RequestMapping(value = "/id/{id}/instances/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PLInstanceDTO> getAllInstancesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLInstanceDTO> plInstancesDTOS= new ArrayList<PLInstanceDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plInstancesDTOS = DTOUtil.getPuzzleLevelInstance(puzzleLevelOptional.get());
        return plInstancesDTOS;
    }
    @Operation( summary = "Fetch all Rules by a puzzle level Id ",  description = "fetches all Rules for a puzzle level ")
    @RequestMapping(value = "/id/{id}/rules/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PLRuleDTO> getAllRulesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLRuleDTO> plRuleDTOS= new ArrayList<PLRuleDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plRuleDTOS = DTOUtil.getRulesForPuzzleLevel(puzzleLevelOptional.get());
        return plRuleDTOS;
    }


//    @Operation( summary = "Save a puzzle level  Objective  ",  description = "save a puzzle level  objective entity and their data to data base")
//    @PostMapping("/objective/id/{id}/save")
//    public ALCityReturnObject savePLObjective(@RequestBody PLObjectiveDTO plObjectiveDTO, @PathVariable Long id)  {
//        ALCityReturnObject savedObjective = null;
//        try {
//            savedObjective = plObjectiveService.saveDTO(plObjectiveDTO,id);
//        }catch (RuntimeException e )
//        {
//            throw new UniqueConstraintException(plObjectiveDTO.getTitle(), plObjectiveDTO.getId(), PLObjectiveDTO.class.toString());
//        }
//        if(savedObjective.getStatus()==0L) {
//            Optional<PLObjective> output = plObjectiveService.findById(savedObjective.getRecordId());
//            savedObjective.setRecordId(output.get().getId());
//        }
//        else return savedObjective;
//
//        return savedObjective;
//    }



}
