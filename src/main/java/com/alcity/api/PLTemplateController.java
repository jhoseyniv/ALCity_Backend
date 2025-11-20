package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.puzzle.PLTemplateDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLTemplateService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Level Template ", description = "Puzzle Level Template APIs...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plt")
public class PLTemplateController {
    @Autowired
    private PLTemplateService plTemplateService;

    @Operation( summary = "Fetch all puzzle level templates data ",  description = "fetches all data for all puzzle level templates structure ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    @Cacheable(value = "getPLTemplates", key = "#p0")
    public Collection<PLTemplateDTO> getPLTemplates(Model model) {
        Collection<PLTemplateDTO> templateDTOS = new ArrayList<PLTemplateDTO>();
        Collection<PLTemplate> templates = plTemplateService.findAll();
        templateDTOS = DTOUtil.getPLTemplateDTOS(templates);

        return templateDTOS;
    }

    @Operation( summary = "Fetch puzzle level template data by a Id ",  description = "fetches all data for a puzzle level template ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLTemplateDTO getPLTemplateById(@PathVariable Long id) {
        PLTemplateDTO   dto= new PLTemplateDTO();
        Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(id);
        if(plTemplateOptional.isEmpty()) return null;
        dto = DTOUtil.getPLTemplateDTO(plTemplateOptional.get());
        return dto;
    }


    @Operation( summary = "Save a puzzle level Template  ",  description = "Save a puzzle level Template  entity and their data to data base")
    @PostMapping("/new")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveNewPuzzleLevelTemplate(@RequestBody PLTemplateDTO dto)  {
        PLTemplate savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(dto.getId());

            try {
                savedRecord = plTemplateService.saveNew(dto);
            } catch (RuntimeException e) {
                throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() ,BaseObject.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
            }

        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), BaseObject.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), BaseObject.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }
    @Operation( summary = "edit a puzzle level Template content only  ",  description = "edit a puzzle level Template content  only")
    @PostMapping("/edit-content")
    @CrossOrigin(origins = "*")
    public ResponseObject editContentOnPuzzleLevelTemplate(@RequestBody PLTemplateDTO dto)  {
        PLTemplate savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

       if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plTemplateService.editContent(dto);
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
           responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
           responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "edit a puzzle level Template except content   ",  description = "edit a puzzle level Template except content  only")
    @PostMapping("/no-edit-content")
    @CrossOrigin(origins = "*")
    public ResponseObject noEditContentOnPuzzleLevelTemplate(@RequestBody PLTemplateDTO dto)  {
        PLTemplate savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plTemplateService.noEditContent(dto);
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "delete a  Puzzle Level  Template",  description = "delete a Puzzle Level template")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deletePuzzleLevelTemplateById(@PathVariable Long id) {
        Optional<PLTemplate> existingRecord = plTemplateService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plTemplateService.delete(existingRecord.get());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PuzzleLevel.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.ok.name(), existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }


}
