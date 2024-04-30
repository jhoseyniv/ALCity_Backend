package com.alcity.api;

import com.alcity.dto.puzzle.*;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pl")
public class PLController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @GetMapping("/all")
    public Collection<PLDTO> getPuzzleLevels(Model model) {
        Collection<PLDTO> puzzleLevelDTOCollection = new ArrayList<PLDTO>();

        Collection<PuzzleLevel> puzzleLevelCollection = puzzleLevelService.findAll();
        Iterator<PuzzleLevel> itr = puzzleLevelCollection.iterator();
        while(itr.hasNext()){
            PuzzleLevel puzzleLevel = itr.next();
            PLDTO puzzleLevelDTO = new PLDTO();
            puzzleLevelDTO.setId(puzzleLevel.getId());
            puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
            puzzleLevelDTO.setCode(puzzleLevel.getCode());
            puzzleLevelDTO.setApproveDate(puzzleLevel.getApproveDate());
            puzzleLevelDTO.setTitle(puzzleLevel.getName());
            puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
            puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
            puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
            puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
            puzzleLevelDTO.setUpdated(puzzleLevel.getUpdated());
            puzzleLevelDTO.setCreated(puzzleLevel.getCreated());

            puzzleLevelDTOCollection.add(puzzleLevelDTO);

        }
        return puzzleLevelDTOCollection;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLDTO getPuzzleLevelById(@PathVariable Long id) {
        PLDTO puzzleLevelDTO= new PLDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevelOptional);
        return puzzleLevelDTO;
    }

    @RequestMapping(value = "/id/{id}/objectives/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PLObjectiveDTO> getAllObjectivesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection= new ArrayList<PLObjectiveDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            puzzleLevelObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
        return puzzleLevelObjectiveDTOCollection;
    }


}
