package com.alcity.api;

import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.customexception.ResponseObject;
import com.alcity.service.play.PlayHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Play History APIs", description = "Get Play history and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/ph")
public class PlayHistoryController {

    @Autowired
    private PlayHistoryService playHistoryService;
    @Operation( summary = "Get play history for an Application Member",  description = "get all play history for an Application Member ...")
    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PlayHistoryDTO getPlayHistoryById(@PathVariable Long id) {
        Optional<PlayHistory>  historyOptional= playHistoryService.findById(id);
        PlayHistoryDTO dto = playHistoryService.getPlayHistory(historyOptional.get());
        return dto;
    }

    @Operation( summary = "Save a play history for an Application Member + puzzle level",  description = "Save a play history for an Application Member + puzzle level ...")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseObject savePlayHistory(@RequestBody PlayHistoryDTO dto) throws ResponseObject {
        PlayHistory savedRecord = null;
        ResponseObject responseObject = new ResponseObject();
        Optional<PlayHistory> playHistoryOptional = playHistoryService.findById(dto.getId());

        try {
            if (playHistoryOptional.isEmpty())
                savedRecord = playHistoryService.save(dto,"Save");
            else
                savedRecord = playHistoryService.save(dto, "Edit");
        }
        catch (Exception e) {

            throw new ResponseObject(ErrorType.UniquenessViolation, PlayHistory.class.getSimpleName() , Status.error.name() , -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            responseObject = new ResponseObject(ErrorType.SaveSuccess, BaseObject.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.DeleteMessage);


        return responseObject;
    }
}
