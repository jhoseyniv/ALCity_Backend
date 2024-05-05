package com.alcity.api;

import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/pc")
//pc =puzzle category
public class PuzzleCategoryController {
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;
    @GetMapping("/all")
    public Collection<PuzzleCategoryDTO> getPuzzleCategories(Model model) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOCollection = new ArrayList<PuzzleCategoryDTO>();
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        Iterator<PuzzleCategory> itr = puzzleCategoryCollection.iterator();
        while(itr.hasNext()) {
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO = DTOUtil.getPuzzleCategoryDTO(puzzleCategory);
            puzzleCategoryDTOCollection.add(puzzleCategoryDTO);
        }
        return puzzleCategoryDTOCollection;
    }
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleCategoryDTO getPuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> puzzleCategory = puzzleCategoryService.findById(id);
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        if(puzzleCategory.isPresent())
                puzzleCategoryDTO = DTOUtil.getPuzzleCategoryDTO(puzzleCategory.get());
        return puzzleCategoryDTO;
    }
    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleCategoryDTO> getPuzzleCategoryByCriteria(@PathVariable String criteria) {
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findByValueContains(criteria);
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOCollection = new ArrayList<PuzzleCategoryDTO>();
        Iterator<PuzzleCategory> itr = puzzleCategoryCollection.iterator();

        while(itr.hasNext()){
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO = DTOUtil.getPuzzleCategoryDTO(puzzleCategory);
            puzzleCategoryDTOCollection.add(puzzleCategoryDTO);
        }
        return puzzleCategoryDTOCollection;
    }

    @PostMapping("/save")
    public Optional<PuzzleCategory> savePuzzleCategory(@RequestBody PuzzleCategory puzzleCategory)  {
        PuzzleCategory savedPuzzleCategory = null;
        try {
            savedPuzzleCategory = puzzleCategoryService.save(puzzleCategory);
        }catch (RuntimeException e )
        {
            throw new UniqueConstraintException(puzzleCategory.getLabel(), puzzleCategory.getId(), PuzzleCategory.class.toString());
        }
        Optional<PuzzleCategory> output = puzzleCategoryService.findById(savedPuzzleCategory.getId());
        return output;
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deletePuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> existingRecord = this.puzzleCategoryService.findById(id);
        if(existingRecord.isPresent()){
            try {
                this.puzzleCategoryService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
        {
            throw new ViolateForeignKeyException(existingRecord.get().getLabel(), existingRecord.get().getId(), PuzzleCategory.class.toString());
        }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

}
