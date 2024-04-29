package com.alcity.api;

import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.dto.puzzle.PGDTO;
import com.alcity.entity.base.DataType;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        Iterator<PuzzleCategory> itr = puzzleCategoryCollection.iterator();
        while(itr.hasNext()) {
            Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();
            PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO.setId(puzzleCategory.getId());
            puzzleCategoryDTO.setLabel(puzzleCategory.getLabel());
            puzzleCategoryDTO.setValue(puzzleCategory.getValue());
            puzzleCategoryDTO.setVersion(puzzleCategory.getVersion());
            puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.getCreated()));
            puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.getUpdated()));
            Collection<PuzzleGroup>  puzzleGroupSet = puzzleCategory.getPuzzleGroupCollection();

            Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
            while(itrPuzzleGroupSet.hasNext()){
                PGDTO puzzleGroupDTO = new PGDTO();
                PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                puzzleGroupDTO.setId(puzzleGroup.getId());
                puzzleGroupDTOCollection.add(puzzleGroupDTO);
            }
            puzzleCategoryDTO.setPuzzleGroupDTOCollection(puzzleGroupDTOCollection);
            puzzleCategoryDTOCollection.add(puzzleCategoryDTO);
        }
        return puzzleCategoryDTOCollection;
    }
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleCategoryDTO getPuzzleCategoryById(@PathVariable Long id) {
        Optional<PuzzleCategory> puzzleCategory = puzzleCategoryService.findById(id);
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<>();
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();

        if(puzzleCategory.isPresent()) {
            puzzleCategoryDTO.setId(puzzleCategory.get().getId());
            puzzleCategoryDTO.setLabel(puzzleCategory.get().getLabel());
            puzzleCategoryDTO.setValue(puzzleCategory.get().getValue());
            puzzleCategoryDTO.setVersion(puzzleCategory.get().getVersion());
            puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.get().getCreated()));
            puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.get().getUpdated()));
            Collection<PuzzleGroup> puzzleGroupSet = puzzleCategory.get().getPuzzleGroupCollection();

            Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
            while (itrPuzzleGroupSet.hasNext()) {
                PGDTO puzzleGroupDTO = new PGDTO();
                PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                puzzleGroupDTO.setId(puzzleGroup.getId());
                puzzleGroupDTOCollection.add(puzzleGroupDTO);
            }
            puzzleCategoryDTO.setPuzzleGroupDTOCollection(puzzleGroupDTOCollection);
        }
        return puzzleCategoryDTO;
    }
    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleCategoryDTO getPuzzleCategoryByCriteria(@PathVariable String criteria) {
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findByValueContains(criteria);
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<>();
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        Iterator<PuzzleCategory> itr = puzzleCategoryCollection.iterator();

        while(itr.hasNext()){
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO.setId(puzzleCategory.getId());
            puzzleCategoryDTO.setLabel(puzzleCategory.getLabel());
            puzzleCategoryDTO.setValue(puzzleCategory.getValue());
            puzzleCategoryDTO.setVersion(puzzleCategory.getVersion());
            puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.getCreated()));
            puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.getUpdated()));
            Collection<PuzzleGroup> puzzleGroupSet = puzzleCategory.getPuzzleGroupCollection();

            Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
            while (itrPuzzleGroupSet.hasNext()) {
                PGDTO puzzleGroupDTO = new PGDTO();
                PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                puzzleGroupDTO.setId(puzzleGroup.getId());
                puzzleGroupDTOCollection.add(puzzleGroupDTO);
            }
            puzzleCategoryDTO.setPuzzleGroupDTOCollection(puzzleGroupDTOCollection);
        }
        return puzzleCategoryDTO;
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


}
