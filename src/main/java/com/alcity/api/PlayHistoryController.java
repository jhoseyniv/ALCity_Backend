package com.alcity.api;

import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public ALCityResponseObject savePlayHistory(@RequestBody PlayHistoryDTO dto)  {
        PlayHistory savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = playHistoryService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PuzzleLevel.class , "Error",savedRecord.getId() );
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = playHistoryService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
    }
}
