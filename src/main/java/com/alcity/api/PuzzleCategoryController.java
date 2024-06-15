package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PGDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;



@Tag(name = "Puzzle Category APIs ", description = "Get Puzzle Category data for ....")

@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pc")
//pc =puzzle category
public class PuzzleCategoryController {
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;
    @Operation( summary = "get all Puzzle Category ",  description = "get all Puzzle Category")
    @GetMapping("/all")
    public Collection<PuzzleCategoryDTO> getPuzzleCategories(Model model) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOS = new ArrayList<PuzzleCategoryDTO>();
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        puzzleCategoryDTOS = DTOUtil.getPuzzleCategoryDTOS(puzzleCategoryCollection);
        return puzzleCategoryDTOS;
    }

    @Operation( summary = "Get all Puzzle Category ",  description = "Get all Puzzle Category")
    @GetMapping("/id/{id}/pg/all")
    @CrossOrigin
    public Collection<PGDTO> getRelatedPuzzleGroupsWithACategory(@PathVariable Long id) {
        Collection<PGDTO> pgdtos = new ArrayList<PGDTO>();
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryService.findById(id);
        if(puzzleCategoryOptional.isPresent()) {
            Collection<PuzzleGroup> puzzleGroups = puzzleCategoryOptional.get().getPuzzleGroupCollection();
            pgdtos = DTOUtil.getPuzzleGroupDTOS(puzzleGroups);
        }
        return pgdtos;
    }

    @Operation( summary = "get a Puzzle Category by id ",  description = "get a Puzzle Category by id")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleCategoryDTO getPuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> puzzleCategory = puzzleCategoryService.findById(id);
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        if(puzzleCategory.isPresent())
                puzzleCategoryDTO = DTOUtil.getPuzzleCategoryDTO(puzzleCategory.get());
        return puzzleCategoryDTO;
    }
    @Operation( summary = "Get a few Puzzle Category by criteria ",  description = "Get a few Puzzle Category by criteria")
    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleCategoryDTO> getPuzzleCategoryByCriteria(@PathVariable String criteria) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOS = new ArrayList<PuzzleCategoryDTO>();
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findByValueContains(criteria);
        puzzleCategoryDTOS = DTOUtil.getPuzzleCategoryDTOS(puzzleCategoryCollection);
        return puzzleCategoryDTOS;
    }

    @Operation( summary = "Save a  Puzzle Category ",  description = "save a Puzzle Category entity to database")
    @PostMapping("/save")
    public Optional<PuzzleCategory> savePuzzleCategory(@RequestBody PuzzleCategoryDTO pcDTO)  {
        PuzzleCategory savedPuzzleCategory = null;
        try {
            savedPuzzleCategory = puzzleCategoryService.save(pcDTO);
        }catch (RuntimeException e )
        {
            throw new UniqueConstraintException(pcDTO.getLabel(), pcDTO.getId(), PuzzleCategory.class.toString());
        }
        Optional<PuzzleCategory> output = puzzleCategoryService.findById(savedPuzzleCategory.getId());
        return output;
    }

    @Operation( summary = "delete a  Puzzle Category ",  description = "delete a Puzzle Category entity and their data to data base")
    @DeleteMapping("/del/{id}")
    public ALCityResponseObject deletePuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> existingRecord = this.puzzleCategoryService.findById(id);
        if(existingRecord.isPresent()){
            try {
                puzzleCategoryService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
        {
            throw new ViolateForeignKeyException(existingRecord.get().getLabel(), existingRecord.get().getId(), PuzzleCategory.class.toString());
        }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

}
