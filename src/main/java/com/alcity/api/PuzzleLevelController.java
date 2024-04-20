package com.alcity.api;

import com.alcity.dto.puzzle.*;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pl")
public class PuzzleLevelController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @GetMapping("/all")
    public Collection<PuzzleLevelDTO> getPuzzleLevels(Model model) {
        Collection<PuzzleLevelDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelDTO>();

        Collection<PuzzleLevel> puzzleLevelCollection = puzzleLevelService.findAll();
        Iterator<PuzzleLevel> itr = puzzleLevelCollection.iterator();
        while(itr.hasNext()){
            PuzzleLevel puzzleLevel = itr.next();
            PuzzleLevelDTO puzzleLevelDTO = new PuzzleLevelDTO();
            puzzleLevelDTO.setId(puzzleLevel.getId());
            puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
            puzzleLevelDTO.setCode(puzzleLevel.getCode());
            puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
            puzzleLevelDTO.setTitle(puzzleLevel.getName());
            puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
            puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
            puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
            puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
            puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
            puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));

            puzzleLevelDTOCollection.add(puzzleLevelDTO);

        }
        return puzzleLevelDTOCollection;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleLevelDTO getPuzzleLevelById(@PathVariable Long id) {
        PuzzleLevelDTO puzzleLevelDTO= new PuzzleLevelDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevelOptional);
        return puzzleLevelDTO;
    }

    @RequestMapping(value = "/id/{id}/objectives/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PuzzleLevelObjectiveDTO> getAllObjectivesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection= new ArrayList<PuzzleLevelObjectiveDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            puzzleLevelObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
        return puzzleLevelObjectiveDTOCollection;
    }


}
