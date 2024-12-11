package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Tag(name = "Puzzle Level API's ", description = "Get Puzzle Levels data Format for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pl")
public class PLController {
    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Operation( summary = "Fetch all puzzle level data ",  description = "fetches all data for all puzzle level structure ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    public PuzzleLevelLDTO getPuzzleLevelById(@PathVariable Long id) {
        PuzzleLevelLDTO puzzleLevelDTO= new PuzzleLevelLDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevelOptional);
        return puzzleLevelDTO;
    }

    @Operation( summary = "Fetch all objective by a puzzle level Id ",  description = "fetches all objectives for a puzzle level ")
    @RequestMapping(value = "/id/{id}/objectives/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLObjectiveDTO> getAllObjectivesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection= new ArrayList<PLObjectiveDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
        return plObjectiveDTOCollection;
    }

    @Operation( summary = "Fetch all Instances by a puzzle level Id ",  description = "fetches all Instances for a puzzle level ")
    @RequestMapping(value = "/id/{id}/instances/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    public Collection<PLRuleDTO> getAllRulesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLRuleDTO> plRuleDTOS= new ArrayList<PLRuleDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plRuleDTOS = DTOUtil.getRulesForPuzzleLevel(puzzleLevelOptional.get());
        return plRuleDTOS;
    }

    @Operation( summary = "Save a puzzle level  ",  description = "Save a puzzle level  entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleLevel(@RequestBody PuzzleLevelLDTO dto)  {
        PuzzleLevel savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = puzzleLevelService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getCode(), dto.getId(), "Code Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = puzzleLevelService.save(dto, "Edit");
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
    @Operation( summary = "delete a  Puzzle Level ",  description = "delete a Puzzle Level ")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleLevelById(@PathVariable Long id) {
        Optional<PuzzleLevel> existingRecord = puzzleLevelService.findById(id);
        if(existingRecord.isPresent()){
            try {
                puzzleLevelService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getTitle(), existingRecord.get().getId(), PuzzleLevel.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }





}
