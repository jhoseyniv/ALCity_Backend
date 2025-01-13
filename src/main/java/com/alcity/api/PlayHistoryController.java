package com.alcity.api;

import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.play.PlayHistory;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

}
