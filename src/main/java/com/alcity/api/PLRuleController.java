package com.alcity.api;

import com.alcity.dto.puzzle.PLRulePostActionDTO;
import com.alcity.entity.alenum.ActionStatus;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.service.alobject.AttributeService;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.service.puzzle.PLRuleService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Level Rule API's ", description = "Puzzle Levels Rules API's")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/plr")
public class PLRuleController {
    @Autowired
    private PLRuleService plRuleService;

    @Autowired
    PLRulePostActionService plRulePostActionService;
    @Autowired
    AttributeService attributeService;

    @Operation( summary = "Save a puzzle level  Rule  ",  description = "Save a puzzle level  Rule entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseObject savePLRule(@RequestBody PLRuleDTO dto)  {
        PLRule savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plRuleService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLRule.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plRuleService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }
    @Operation( summary = "Fetch a Rule by a Id ",  description = "fetch a rule")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLRuleDTO getRuleById(@PathVariable Long id) {
        PLRuleDTO ruleDTO= new PLRuleDTO();
        Optional<PLRule> plRuleOptional = plRuleService.findById(id);
        if(plRuleOptional.isPresent())
            ruleDTO = DTOUtil.getPLRuleDTO(plRuleOptional.get());
        return ruleDTO;
    }
    @Operation( summary = "Fetch all Post Actions for a Rule by a rule Id ",  description = "Fetch all Post Actions for a Rule by a rule Id")
    @RequestMapping(value = "/id/{id}/post-actions/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLRulePostActionDTO> getPostActionsForRuleById(@PathVariable Long id) {
        Collection<PLRulePostActionDTO> rulePostActionDTOS = new ArrayList<>();
        Optional<PLRule> plRuleOptional = plRuleService.findById(id);
        if(plRuleOptional.isPresent()) {
            PLRule rule = plRuleOptional.get();
            Collection<PLRulePostAction> plRulePostActions = DTOUtil.getPlRulePostActions(plRulePostActionService,rule.getId());
            rulePostActionDTOS = DTOUtil.getPLRulePostActionDTOS(plRulePostActions,attributeService);
        }
        return rulePostActionDTOS;
    }
    @Operation( summary = "Fetch all Post Actions as child actions for a post action by Id ",  description = "Fetch all Post Actions as child actions for a post action by Id ")
    @RequestMapping(value = "/pid/{pid}/child-post-actions/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLRulePostActionDTO> getChildPostActionsForAnotherPostActionId(@PathVariable Long pid) {
        Collection<PLRulePostActionDTO> rulePostActionDTOS = new ArrayList<>();
        Optional<PLRulePostAction> postActionOptional = plRulePostActionService.findById(pid);
        if(postActionOptional.isPresent()) {
            PLRulePostAction action = postActionOptional.get();
            Collection<PLRulePostAction> plRulePostActions = DTOUtil.getPlRulePostActions(plRulePostActionService,action.getId());
            rulePostActionDTOS = DTOUtil.getPLRulePostActionDTOS(plRulePostActions,attributeService);
        }
        return rulePostActionDTOS;
    }

    @Operation( summary = "Copy a  Post Action in puzzle level rule  ",  description = "Copy a  Post Action in puzzle level rule ")
    @PostMapping("/copy/post-action/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject copyPostAction(@PathVariable Long id) {
        Optional<PLRulePostAction> postActionOptional = plRulePostActionService.findById(id);
        PLRulePostAction copiedAction = null;
        if(postActionOptional.isEmpty())
           return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);
        PLRulePostAction postAction = postActionOptional.get();
        copiedAction = plRulePostActionService.copy(postAction,postAction.getOwnerId());
        return  new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, postAction.getId(), SystemMessage.SaveOrEditMessage_Success);
    }
    @Operation( summary = "Copy a  Rule in a puzzle level by rule id ",  description = "Copy a  Rule in puzzle level by rule id ")
    @PostMapping("/copy/rule/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject copyRule(@PathVariable Long id) {
        Optional<PLRule> ruleOptional = plRuleService.findById(id);
        PLRule copiedRule = null;
        if(ruleOptional.isEmpty())
            return
                     new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, id,SystemMessage.RecordNotFound);
        PLRule rule = ruleOptional.get();
        copiedRule = plRuleService.copy(rule,rule.getPuzzleLevel());
        return  new ResponseObject(ErrorType.SaveSuccess, PLRule.class.getSimpleName() , ActionStatus.OK, rule.getId(), SystemMessage.SaveOrEditMessage_Success);
    }

    @Operation( summary = "Save a puzzle level  Rule Post Action ",  description = "Save a puzzle level  Rule Post Action entity and their data to data base")
    @PostMapping("/save/post-action")
    @CrossOrigin(origins = "*")
    public ResponseObject savePLRulePostAtion(@RequestBody PLRulePostActionDTO dto)  {
        PLRulePostAction savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plRulePostActionService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + PLRulePostAction.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = plRulePostActionService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , ActionStatus.OK, savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }

    @Operation( summary = "Delete a  Puzzle Level Rule Post Action",  description = "Delete a Puzzle Level Rule Post Action")
    @DeleteMapping("/del/post-action/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deletePuzzleLevelRulePostActionById(@PathVariable Long id) {
        Optional<PLRulePostAction> existingRecord = plRulePostActionService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plRulePostActionService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLRulePostAction.class.toString(),existingRecord.get().getId());
            }

            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), ActionStatus.OK, existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
       return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), ActionStatus.Error, existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Delete a  Puzzle Level Rule",  description = "Delete a Puzzle Level Rule")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deletePuzzleLevelRuleById(@PathVariable Long id) {
        Optional<PLRule> existingRecord = plRuleService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plRuleService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", PLRule.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, PLRule.class.getSimpleName(), ActionStatus.OK, existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, PLRule.class.getSimpleName(), ActionStatus.Error, existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }


}
