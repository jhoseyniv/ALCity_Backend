package com.alcity.api;

import com.alcity.dto.puzzle.PLDTO;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PGDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.SearchCriteria;
import com.alcity.utility.SearchSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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
    @CrossOrigin(origins = "*")
    public Collection<PuzzleCategoryDTO> getPuzzleCategories(Model model) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOS = new ArrayList<PuzzleCategoryDTO>();
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        puzzleCategoryDTOS = DTOUtil.getPuzzleCategoryDTOS(puzzleCategoryCollection);
        return puzzleCategoryDTOS;
    }

    @Operation( summary = "Get all Puzzle Group for a puzzle Category ",  description = "Get all Puzzle Category")
    @GetMapping("/id/{id}/pg/all")
    @CrossOrigin(origins = "*")
    public Collection<PGDTO> getRelatedPuzzleGroupsWithACategory(@PathVariable Long id) {
        Collection<PGDTO> pgdtos = new ArrayList<PGDTO>();
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryService.findById(id);
        if(puzzleCategoryOptional.isPresent()) {
            Collection<PuzzleGroup> puzzleGroups = puzzleCategoryOptional.get().getPuzzleGroupCollection();
            pgdtos = DTOUtil.getPuzzleGroupDTOS(puzzleGroups);
        }
        return pgdtos;
    }
    @Operation( summary = "Get all Puzzle Levels for a puzzle Category by age order ",  description = "Get all Puzzle Levels for a puzzle Category by age order")
    @GetMapping("/id/{id}/pl/all")
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> a(@PathVariable Long id) {
        Collection<PLDTO> pldtos = new ArrayList<PLDTO>();
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryService.findById(id);
        if(puzzleCategoryOptional.isPresent()) {
            Collection<PuzzleGroup> puzzleGroups = puzzleCategoryOptional.get().getPuzzleGroupCollection();
            pldtos = DTOUtil.getPuzzleLevelsDTOS(puzzleGroups);
        }
        return pldtos;
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
    @CrossOrigin(origins = "*")
    public Collection<PuzzleCategoryDTO> getPuzzleCategoryByCriteria(@PathVariable String criteria) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOS = new ArrayList<PuzzleCategoryDTO>();
        SearchSpecification spec1 =
                new SearchSpecification(new SearchCriteria("value", ":", criteria));

        SearchSpecification spec2 =
                new SearchSpecification(new SearchCriteria("label", ":", criteria));

        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll(Specification.where(spec1).or(spec2));
        puzzleCategoryDTOS = DTOUtil.getPuzzleCategoryDTOS(puzzleCategoryCollection);
        return puzzleCategoryDTOS;
    }

    @Operation( summary = "Save a  Puzzle Category ",  description = "save a Puzzle Category entity to database")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleCategory(@RequestBody PuzzleCategoryDTO dto) {
        PuzzleCategory savedPuzzleCategory = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedPuzzleCategory = puzzleCategoryService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getLabel(), dto.getId(), PuzzleCategory.class.toString());
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedPuzzleCategory.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
                Optional<PuzzleCategory>  puzzleCategoryOptional = puzzleCategoryService.findById(dto.getId());
                savedPuzzleCategory = puzzleCategoryService.save(dto, "Edit");
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedPuzzleCategory.getId(), "Record Updated Successfully!");
        }
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", savedPuzzleCategory.getId(), "Record Not Found!");

        return responseObject;
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
