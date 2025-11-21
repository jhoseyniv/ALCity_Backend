package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLTemplateDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
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
    @Operation( summary = "Get all Puzzle Category ",  description = "get all Puzzle Category")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    @Cacheable("all-PuzzleCategoryDTO")
    public Collection<PuzzleCategoryDTO> getPuzzleCategories() {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOS = new ArrayList<PuzzleCategoryDTO>();
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        puzzleCategoryDTOS = DTOUtil.getPuzzleCategoryDTOS(puzzleCategoryCollection);
        return puzzleCategoryDTOS;
    }

    @Operation( summary = "Get all Puzzle Group for a puzzle Category ",  description = "Get all Puzzle Category")
    @GetMapping("/id/{id}/pg/all")
    @CrossOrigin(origins = "*")
    @Cacheable("getRelatedPuzzleGroupsOfACategory")
    public Collection<PGDTO> getRelatedPuzzleGroupsOfACategory(@PathVariable Long id) {
        Collection<PGDTO> pgdtos = new ArrayList<PGDTO>();
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryService.findById(id);
        if(puzzleCategoryOptional.isPresent()) {
            Collection<PuzzleGroup> puzzleGroups = puzzleCategoryOptional.get().getPuzzleGroupCollection();
            pgdtos = DTOUtil.getPuzzleGroupDTOS(puzzleGroups);
        }
        return pgdtos;
    }

    @Operation( summary = "Get all Puzzle Level Templates by a puzzle Category id ",  description = "Get all Puzzle Level Templates")
    @GetMapping("/id/{id}/plt/all")
    @CrossOrigin(origins = "*")
    @Cacheable(value = "getRelatedPuzzleLevelTemplatesOfACategory", key = "#id")
    public Collection<PLTemplateDTO> getRelatedPuzzleLevelTemplatesOfACategory(@PathVariable Long id) {
        Collection<PLTemplateDTO> plTemplateDTOS = new ArrayList<PLTemplateDTO>();
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryService.findById(id);
        if(puzzleCategoryOptional.isPresent()) {
            Collection<PLTemplate> templates = puzzleCategoryOptional.get().getPlTemplates();
            plTemplateDTOS = DTOUtil.getPLTemplateDTOS(templates);
        }
        return plTemplateDTOS;
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
    public ResponseMessage savePuzzleCategory(@RequestBody PuzzleCategoryDTO dto) {
        PuzzleCategory savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryService.findById(dto.getId());
        try {
            if(puzzleCategoryOptional.isEmpty())
                savedRecord = puzzleCategoryService.save(dto,"Save");
            else
                savedRecord = puzzleCategoryService.save(dto,"Edit");
            if(savedRecord !=null)
                response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PuzzleCategory.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), PuzzleCategory.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        }
        catch (RuntimeException e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , PuzzleCategory.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }

        return response;
    }

    @Operation( summary = "delete a  Puzzle Category ",  description = "delete a Puzzle Category entity and their data to data base")
    @DeleteMapping("/del/{id}")
    public ResponseObject deletePuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> existingRecord = this.puzzleCategoryService.findById(id);
        if(existingRecord.isPresent()){
            try {
                puzzleCategoryService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
        {
            throw new ViolateForeignKeyException(-1, "error", PuzzleCategory.class.toString(),existingRecord.get().getId());
        }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.error.name(), id,SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);
    }

}
