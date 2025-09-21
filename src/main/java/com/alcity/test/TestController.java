package com.alcity.test;


import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.plimpexport.PLImportDTO;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.puzzle.PLTemplateService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.test.importstruct.PLImportDTO_New;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "Test APIs", description = "Test API")

@RestController
@RequestMapping("/test")

public class TestController {
    @Autowired
    private PuzzleLevelService plService;
    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private PLTemplateService plTemplateService;


    @Operation( summary = "Import a puzzle level test",  description = "Import a puzzle level test entity and their data")
    @PostMapping("/import-test")
    @CrossOrigin(origins = "*")
    public ResponseMessage importPuzzleLevel_New(@RequestBody PLImportDTO_New dto) throws IOException, ClassNotFoundException {
        PuzzleLevel importedPuzzleLevel=null;
        ResponseMessage response = new ResponseMessage();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(dto.getId());
        if(puzzleLevelOptional.isEmpty()){
            importedPuzzleLevel =  plService.importPuzzleLevel_New(dto);
        }else{
            //first delete exist puzzle level and then add new pl
            plService.deletePuzzleLevel(puzzleLevelOptional.get());
            importedPuzzleLevel =  plService.importPuzzleLevel_New(dto);
        }
        Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(dto.getPuzzleTemplateId());
        PLTemplate plTemplate = plTemplateOptional.get();
        plTemplate.setPuzzleLevelId(importedPuzzleLevel.getId());
        plTemplateService.save(plTemplate);
        response = new ResponseMessage(ErrorType.ImportSuccess,  Status.ok.name(),PuzzleLevel.class.getSimpleName() , importedPuzzleLevel.getId(), SystemMessage.SaveOrEditMessage_Success);

        return response;
    }

}
