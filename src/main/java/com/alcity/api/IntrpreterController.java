package com.alcity.api;


import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.Interpreter.CameraSetupInterpreter;
import com.alcity.dto.Interpreter.InterpreterUtil;
import com.alcity.dto.Interpreter.PuzzleLevelInterpreterDTO;
import com.alcity.dto.Interpreter.PuzzleLevelObjectiveData;
import com.alcity.dto.puzzle.PuzzleGroup_PuzzleObjectDTO;
import com.alcity.dto.puzzle.PuzzleLevelDTO;
import com.alcity.dto.puzzle.PuzzleLevelGroundDTO;
import com.alcity.dto.puzzle.PuzzleLevelObjectiveDTO;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleLevelGround;
import com.alcity.entity.puzzle.PuzzleLevelObjective;
import com.alcity.service.puzzle.PuzzleLevelGroundService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/puzz")
public class IntrpreterController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @Autowired
    private PuzzleLevelGroundService puzzleLevelGroundService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleLevelInterpreterDTO getPuzzleLevelForInterpreter(@PathVariable Long id) {
         PuzzleLevelInterpreterDTO puzzleLevelInterpreterDTO= new PuzzleLevelInterpreterDTO();
         Optional<PuzzleLevelGround> puzzleLevelGroundOptional = puzzleLevelGroundService.findByPuzzleLevelId(id);
        PuzzleLevelGround puzzleLevelGround = new PuzzleLevelGround();
         if(puzzleLevelGroundOptional.isPresent()){
             puzzleLevelGround = puzzleLevelGroundOptional.get();
             CameraSetup cameraSetup =  puzzleLevelGround.getCameraSetup();
             CameraSetupInterpreter cameraSetupInterpreter = new CameraSetupInterpreter(cameraSetup.getxPosition(),cameraSetup.getyPosition(), cameraSetup.getzPosition(),
                     cameraSetup.getxRotation(),cameraSetup.getyRotation(),cameraSetup.getzRotation());
             puzzleLevelInterpreterDTO.setCameraSetup(cameraSetupInterpreter);

             PuzzleLevel pl = puzzleLevelGround.getPuzzleLevel();
             puzzleLevelInterpreterDTO.setCode(pl.getCode());
             puzzleLevelInterpreterDTO.setName(pl.getName());

             Collection<PuzzleLevelObjectiveData> puzzleLevelObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
             puzzleLevelInterpreterDTO.setObjectives(puzzleLevelObjectiveDataCollection);

            // Collection<PuzzleGroup_PuzzleObjectDTO> objects = DTOUtil.getPuzzleGroup_PuzzleObjectDTOS()

             puzzleLevelInterpreterDTO.setCols(puzzleLevelGround.getNumColumns());
             puzzleLevelInterpreterDTO.setRows(puzzleLevelGround.getNumRows());

         }

        return puzzleLevelInterpreterDTO;
    }

}
