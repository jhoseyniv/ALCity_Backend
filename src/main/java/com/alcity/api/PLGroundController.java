package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.puzzle.PLCellDTO;
import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PGLearningSkill;
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
    public ResponseMessage savePLGround(@RequestBody PLGroundDTO dto) {
        PLGround savedRecord = null;
        ResponseMessage response = new ResponseMessage();

        Optional<PLGround> plGroundOptional = plGroundService.findById(dto.getId());
        try{
            if (plGroundOptional.isEmpty())
                savedRecord = plGroundService.save(dto,"Save");
            else
                savedRecord = plGroundService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,PLGround.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PLGround.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail, Status.error.name(),PLGround.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
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

    @Operation( summary = "Delete a  PL Ground ",  description = "Delete a PL ground")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deletePLGroundById(@PathVariable Long id) {
        Optional<PLGround> requestedRecord = plGroundService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                plGroundService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), PGLearningSkill.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),PGLearningSkill.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), PGLearningSkill.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
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
