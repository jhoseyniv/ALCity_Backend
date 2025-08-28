package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.customexception.ResponseObject;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Play History APIs", description = "Get Play history and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/ph")
public class PlayHistoryController {

    @Autowired
    private PlayHistoryService playHistoryService;

    @Autowired
    private AppMemberService appMemberService;

    @Operation( summary = "Get all play history for an Application Member by user id",  description = "get all play history for an Application Member  by user id...")
    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PlayHistoryDTO> getAllPlayHistoryForAUserById(@PathVariable Long id) {
        Collection<PlayHistoryDTO> dtos = new ArrayList<>();
        Optional<AppMember> appMemberOptional = appMemberService.findById(id);
        if(appMemberOptional.isEmpty()) return  null;
        Collection<PlayHistory>  histories= playHistoryService.findByPlayer(appMemberOptional.get());
        dtos = DTOUtil.getPlayHistoryDTOS(histories);
        return dtos;
    }

    @Operation( summary = "Get a play history for an Application Member by history id",  description = "get a play history for an Application Member  by history id...")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PlayHistoryDTO getPlayHistoryById(@PathVariable Long id) {
        Optional<PlayHistory>  historyOptional= playHistoryService.findById(id);
        if(historyOptional.isEmpty()) return  null;
        return DTOUtil.getPlayHistoryDTO(historyOptional.get());
    }

    @Operation( summary = "Get analytical data for a play history  by id",  description = "analytical data for a play history  by id..........")
    @RequestMapping(value = "/analytical-data/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public byte[] getAnalyticalDataById(@PathVariable Long id) {
        Optional<PlayHistory>  historyOptional= playHistoryService.findById(id);
        if(historyOptional.isEmpty()) return  null;
        return historyOptional.get().getAnalyticalData();
    }

    @Operation( summary = "Save analytical data into database for a play history",  description = "Save analytical data into database for a play history...")
    @PostMapping("/save-analytical-data/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveAnalyticalData(@RequestBody byte[] analyticalData,Long id) throws ResponseObject {
        ResponseMessage response = new ResponseMessage();
        Optional<PlayHistory> playHistoryOptional = playHistoryService.findById(id);
         if(playHistoryOptional.isEmpty())
                return new  ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), PlayHistory.class.getSimpleName() ,  id, SystemMessage.SaveOrEditMessage_Fail);
         PlayHistory playHistory = playHistoryOptional.get();
         playHistory.setAnalyticalData(analyticalData);
        try {
            playHistoryService.save(playHistory);
            }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , PlayHistory.class.getSimpleName()  , -1L ,e.getCause().getMessage());
        }
        return  new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), PlayHistory.class.getSimpleName() ,  playHistory.getId(), SystemMessage.SaveOrEditMessage_Success);
    }


    @Operation( summary = "Save a play history for an Application Member + puzzle level",  description = "Save a play history for an Application Member + puzzle level ...")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePlayHistory(@RequestBody PlayHistoryDTO dto) throws ResponseObject {
        PlayHistory savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PlayHistory> playHistoryOptional = playHistoryService.findById(dto.getId());

        try {
            if (playHistoryOptional.isEmpty())
                savedRecord = playHistoryService.save(dto,"Save");
            else
                savedRecord = playHistoryService.save(dto, "Edit");
        }
        catch (Exception e) {

            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , PlayHistory.class.getSimpleName()  , -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), PlayHistory.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.DeleteMessage);
        return response;
    }



}
