package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.puzzle.PLCellDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLCellService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "Puzzle Level Cell API ", description = "Get Puzzle Levels Cell ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pl-cell")

public class PLCellController {
    @Autowired
    private PLCellService plCellService;

    @Operation( summary = "Fetch puzzle level Cell by a Id ",  description = "Fetch puzzle level Cell by a Id ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLCellDTO getPLCelldById(@PathVariable Long id) throws IOException, ClassNotFoundException, JSONException {
        PLCellDTO   plCellDTO= new PLCellDTO();
        Optional<PLCell> plCellOptional = plCellService.findById(id);
        plCellDTO = DTOUtil.getPLCellDTO(plCellOptional.get());
        return plCellDTO;
    }

    @Operation( summary = "Save a PL Cell information ",  description = "Save a puzzle level Cell entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePLCell(@RequestBody PLCellDTO dto) {
        PLCell savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PLCell> plCellOptional = plCellService.findById(dto.getId());
        try{
            if (plCellOptional.isEmpty())
                savedRecord = plCellService.save(dto,"Save");
            else
                savedRecord = plCellService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , PLCell.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), PLCell.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), PLCell.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;

    }

    @Operation( summary = "delete a  puzzle level Cell ",  description = "Delete a puzzle level Cell")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deletePLCellById(@PathVariable Long id) {
        Optional<PLCell> requestedRecord = plCellService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                plCellService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation, PLCell.class.getSimpleName(), Status.error.name(), id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, PLCell.class.getSimpleName(), Status.ok.name(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,PLCell.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);
    }


}
