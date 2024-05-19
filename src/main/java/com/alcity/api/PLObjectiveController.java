package com.alcity.api;

import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Puzzle Level Objective API's ", description = "Get Puzzle Levels Objectives for other systems...")
@RestController
@RequestMapping("/plo")
public class PLObjectiveController {

    @Autowired
    private PLObjectiveService plObjectiveService;



    @Operation( summary = "Fetch a objective by a Id ",  description = "fetches all data for a objectives")
    @RequestMapping(value = "/objective/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLObjectiveDTO getObjectivesById(@PathVariable Long id) {
        PLObjectiveDTO plObjectiveDT= new PLObjectiveDTO();
        Optional<PLObjective> plObjectiveOptional = plObjectiveService.findById(id);
        if(plObjectiveOptional.isPresent())
            plObjectiveDT = DTOUtil.getPuzzleLevelObjectiveDTO(plObjectiveOptional.get());
        return plObjectiveDT;
    }

}
