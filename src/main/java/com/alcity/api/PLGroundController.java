package com.alcity.api;


import com.alcity.dto.puzzle.PLCellDTO;
import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.service.base.BinaryContentService;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLCellService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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
    public ResponseObject savePLGround(@RequestBody PLGroundDTO dto) {
        PLGround savedRecord = null;
        ResponseObject responseObject = new ResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plGroundService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLGround.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = plGroundService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

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
    public ResponseObject deletePLGroundById(@PathVariable Long id) {
        Optional<PLGround> existingRecord = plGroundService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plGroundService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLGround.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.error.name(), id,SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);
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
