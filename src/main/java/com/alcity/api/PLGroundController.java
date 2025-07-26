package com.alcity.api;


import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.puzzle.CameraSetupDTO;
import com.alcity.dto.puzzle.PLCellDTO;
import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLCellService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Level Ground API ", description = "Get Puzzle Levels Ground Format for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pl-ground")
public class PLGroundController {

    @Autowired
    private PLGroundService plGroundService;

    @Autowired
    private PLCellService plCellService;

     @Autowired
    private BinaryContentService binaryContentService;

    @Operation( summary = "Fetch puzzle level Ground by a Id ",  description = "Fetch puzzle level Ground by a Id ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLGroundDTO getPLGroundById(@PathVariable Long id) throws IOException, ClassNotFoundException, JSONException {
        PLGroundDTO   plGroundDTO= new PLGroundDTO();
        Optional<PLGround> plGroundOptional = plGroundService.findById(id);
        plGroundDTO = DTOUtil.getPLGroundDTO(plGroundOptional.get());
        return plGroundDTO;
    }

    @Operation( summary = "Save a PL Ground information ",  description = "Save a puzzle level ground entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePLGround(@RequestBody PLGroundDTO dto) {
        PLGround savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plGroundService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLGround.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = plGroundService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record not Found!");

        return responseObject;
    }
    @Operation( summary = "Fetch board graphic for a puzzle level by  Id ",  description = "Fetch board graphic for a puzzle level by  Id")
    @RequestMapping(value = "/id/{id}/boardgraphic", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public BoardGraphicDTO getBoardGraphicByPLGroundId(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PLGround> plGroundOptional = plGroundService.findById(id);
        byte[] boardGraphic=null;
        BoardGraphicDTO boardGraphicDTO = new BoardGraphicDTO();
        if(plGroundOptional.isPresent()) {
            boardGraphicDTO = PLDTOUtil.getBoardGraphicJSON(plGroundOptional.get());
        }
        return boardGraphicDTO;
    }

    @Operation( summary = "delete a  PL Ground ",  description = "delete a PL ground")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePLGroundById(@PathVariable Long id) {
        Optional<PLGround> existingRecord = plGroundService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plGroundService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLGround.class.toString(),existingRecord.get().getId());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "Fetch puzzle level cells for a PL Ground by a Id ",  description = "Fetch puzzle level cells for a PL Ground by a Id ")
    @RequestMapping(value = "/id/{id}/cells", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLCellDTO> getPLCellsById(@PathVariable Long id) throws IOException, ClassNotFoundException, JSONException {
        Collection<PLCell> cells = new ArrayList<>();
        Collection<PLCellDTO> dtos = new ArrayList<>();
        Optional<PLGround> plGroundOptional = plGroundService.findById(id);
        cells = plGroundOptional.get().getPlCells();
        dtos = DTOUtil.getPLCellDTOS(cells);
        return dtos;
    }


}
