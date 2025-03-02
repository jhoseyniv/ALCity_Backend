package com.alcity.api;


import com.alcity.dto.RemoteAccess.RemoteAccessDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.dto.search.ObjectSearchCriteriaDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Remote Access To Some  API's ", description = "Remote Access to API's...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/service")
public class RemoteAccessController extends BaseTable implements Serializable {

    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private AppMemberService memberService;


    @Operation( summary = "login  all AL City Objects by Object Category and puzzle group and search title ",  description = "Fetch all AL City Objects by Object Category and puzzle group and search title")
    @PostMapping("/get-pl")
    @CrossOrigin(origins = "*")
    public AppMember getPuzzleLevel(@RequestBody RemoteAccessDTO accessDTO) {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findByCode(accessDTO.getPuzzleCode());
        AppMember appMember = memberService.findRemoteUser(accessDTO);
        if(appMember ==null) {
            appMember = memberService.saveRemoteUser(accessDTO);
            return appMember;
        }
        return appMember;
    }

}
