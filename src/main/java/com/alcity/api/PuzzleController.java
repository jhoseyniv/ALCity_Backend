package com.alcity.api;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.dto.puzzle.PuzzleGroupDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.puzzle.PuzzleGroupService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PuzzleGroupService puzzleGroupService;

    @GetMapping("/categories")
    public Collection<PuzzleCategoryDTO> getPuzzleCategories(Model model) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOCollection = new ArrayList<PuzzleCategoryDTO>();

        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        Iterator<PuzzleCategory> itr = puzzleCategoryCollection.iterator();
        while(itr.hasNext()) {
            Collection<PuzzleGroupDTO> puzzleGroupDTOCollection = new ArrayList<PuzzleGroupDTO>();
            PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO.setId(puzzleCategory.getId());
            puzzleCategoryDTO.setLabel(puzzleCategory.getLabel());
            puzzleCategoryDTO.setValue(puzzleCategory.getValue());
            puzzleCategoryDTO.setVersion(puzzleCategory.getVersion());
            puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.getCreated()));
            puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.getUpdated()));
            Collection<PuzzleGroup>  puzzleGroupSet = puzzleCategory.getPuzzleGroupSet();

            Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
            while(itrPuzzleGroupSet.hasNext()){
                PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
                PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                puzzleGroupDTO.setId(puzzleGroup.getId());
                puzzleGroupDTOCollection.add(puzzleGroupDTO);
            }
            puzzleCategoryDTO.setPuzzleGroupDTOSet(puzzleGroupDTOCollection);
            puzzleCategoryDTOCollection.add(puzzleCategoryDTO);
        }

     return puzzleCategoryDTOCollection;
    }


    @RequestMapping(value = "/category/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleCategoryDTO getPuzzleCategoryById(@PathVariable Long id) {
            Optional<PuzzleCategory> puzzleCategory = puzzleCategoryService.findById(id);
            Collection<PuzzleGroupDTO> puzzleGroupDTOCollection = new ArrayList<>();
            PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();

            if(puzzleCategory.isPresent()) {
                puzzleCategoryDTO.setId(puzzleCategory.get().getId());
                puzzleCategoryDTO.setLabel(puzzleCategory.get().getLabel());
                puzzleCategoryDTO.setValue(puzzleCategory.get().getValue());
                puzzleCategoryDTO.setVersion(puzzleCategory.get().getVersion());
                puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.get().getCreated()));
                puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.get().getUpdated()));
                Collection<PuzzleGroup> puzzleGroupSet = puzzleCategory.get().getPuzzleGroupSet();

                Iterator<PuzzleGroup> itrPuzzleGroupSet = puzzleGroupSet.iterator();
                while (itrPuzzleGroupSet.hasNext()) {
                    PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
                    PuzzleGroup puzzleGroup = itrPuzzleGroupSet.next();
                    puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
                    puzzleGroupDTO.setId(puzzleGroup.getId());
                    puzzleGroupDTOCollection.add(puzzleGroupDTO);
                }
                puzzleCategoryDTO.setPuzzleGroupDTOSet(puzzleGroupDTOCollection);
            }

        return puzzleCategoryDTO;
    }

    @GetMapping("/groups/")
    public Collection<PuzzleGroupDTO> getPuzzleGroups(Model model) {
        Collection<PuzzleGroup> puzzleGroupCollection = puzzleGroupService.findAll();
        Collection<PuzzleGroupDTO> puzzleGroupDTOCollection = new ArrayList<PuzzleGroupDTO>();
        Iterator<PuzzleGroup> puzzleGroupIterator = puzzleGroupCollection.iterator();
        while (puzzleGroupIterator.hasNext()) {
            PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
            PuzzleGroup puzzleGroup = puzzleGroupIterator.next();
            puzzleGroupDTO.setId(puzzleGroup.getId());
            puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
            BinaryContent binaryContent_icon = puzzleGroup.getIcon();
            BinaryContent binaryContent_pic = puzzleGroup.getPic();

            BinaryContentDTO binaryContentDTO_icon = new BinaryContentDTO(binaryContent_icon.getFileName(),binaryContent_icon.getSize(),binaryContent_icon.getContent(),binaryContent_icon.getId(),binaryContent_icon.getVersion()
                    ,DateUtils.getDatatimeFromLong(binaryContent_icon.getCreated()),DateUtils.getDatatimeFromLong(binaryContent_icon.getUpdated()));
            BinaryContentDTO binaryContentDTO_pic = new BinaryContentDTO(binaryContent_pic.getFileName(),binaryContent_pic.getSize(),binaryContent_pic.getContent(),binaryContent_pic.getId(),binaryContent_pic.getVersion()
                    ,DateUtils.getDatatimeFromLong(binaryContent_pic.getCreated()),DateUtils.getDatatimeFromLong(binaryContent_pic.getUpdated()));
            puzzleGroupDTO.setIcon(binaryContentDTO_icon);
            puzzleGroupDTO.setPic(binaryContentDTO_pic);
            puzzleGroupDTOCollection.add(puzzleGroupDTO);
        }

        return puzzleGroupDTOCollection;
    }
    @RequestMapping(value = "/group/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleGroupDTO getPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = puzzleGroupService.findById(id);
        PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
        if(puzzleGroup.isPresent()) {
            puzzleGroupDTO.setId(puzzleGroup.get().getId());
            puzzleGroupDTO.setTitle(puzzleGroup.get().getTitle());
            BinaryContent binaryContent_icon = puzzleGroup.get().getIcon();
            BinaryContent binaryContent_pic = puzzleGroup.get().getPic();

            BinaryContentDTO binaryContentDTO_icon = new BinaryContentDTO(binaryContent_icon.getFileName(), binaryContent_icon.getSize(), binaryContent_icon.getContent(), binaryContent_icon.getId(), binaryContent_icon.getVersion()
                    , DateUtils.getDatatimeFromLong(binaryContent_icon.getCreated()), DateUtils.getDatatimeFromLong(binaryContent_icon.getUpdated()));
            BinaryContentDTO binaryContentDTO_pic = new BinaryContentDTO(binaryContent_pic.getFileName(), binaryContent_pic.getSize(), binaryContent_pic.getContent(), binaryContent_pic.getId(), binaryContent_pic.getVersion()
                    , DateUtils.getDatatimeFromLong(binaryContent_pic.getCreated()), DateUtils.getDatatimeFromLong(binaryContent_pic.getUpdated()));
            puzzleGroupDTO.setIcon(binaryContentDTO_icon);
            puzzleGroupDTO.setPic(binaryContentDTO_pic);
        }
        return  puzzleGroupDTO;
    }


}
