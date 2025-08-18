package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.appmember.*;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.search.AppMemberSearchCriteriaDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.appmember.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.service.Journey.JourneyService;
import com.alcity.o3rdparty.ALCityAcessRight;
import com.alcity.service.appmember.LearningSkillTransactionService;
import com.alcity.service.appmember.WalletTransactionService;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Application Member APIs", description = "Get Application Member and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/user")
public class AppMemberController {

    @Autowired
    private AppMemberService appMemberService;
    @Autowired
    private PLObjectiveService plObjectiveService;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private LearningSkillTransactionService learningSkillTransactionService;

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<AppMemberDTO> getApplicationMembers(Model model) {
        Collection<AppMember> appMemberCollection = appMemberService.findAll();
        Collection<AppMemberDTO> dtos = DTOUtil.getAppMemberDTOS(appMemberCollection);
        return dtos;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public AppMemberDTO getApplicationMemberById(@PathVariable Long id) {
        Optional<AppMember> member = appMemberService.findById(id);
        AppMemberDTO dto = DTOUtil.getAppMemberDTO(member.get());
        return dto;
    }

    @Operation( summary = "Get Avatar by User Id ",  description = "Get Avatar by User Id ...")
    @GetMapping("/get-avatar/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        Optional<AppMember>  appMemberOptional= appMemberService.findById(id);
        if(appMemberOptional.isEmpty()) return  null;
        BinaryContent binaryContent = appMemberOptional.get().getIcon();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + binaryContent.getFileName() + "\"")
                .body(binaryContent.getContent());
    }

    @Operation( summary = "Get public puzzle levels for a app member ",  description = "Get all puzzles for a user ...")
    @RequestMapping(value = "/id/{id}/all-pl", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> getPublicPuzzleLevels(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<PLDTO> pldtos = appMemberService.getPublicPuzzleLevels(memberOptional.get());
        return pldtos;
    }

    @Operation( summary = "Get puzzle levels defined for a app member but not played ",  description = "Get all puzzle levels defined for a app member but not played ...")
    @RequestMapping(value = "/id/{id}/not-played", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> getApplicationMemberPuzzleLevelsNotPlayed(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<PLDTO>  publicPuzzleLevels = appMemberService.getPublicPuzzleLevels(memberOptional.get());
        Collection<PLDTO>  playedPuzlles = appMemberService.getPuzzleLevelsPlayed(memberOptional.get());
        Collection<PLDTO> pldtos = appMemberService.getPuzzleLevelsNotPlayed(publicPuzzleLevels,playedPuzlles);
        return pldtos;
    }
    @Operation( summary = "Get puzzle levels for an Application Member that played",  description = "Get all puzzle levels for an Application Member that played ...")
    @RequestMapping(value = "/id/{id}/played", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> getPuzzleLevelsPlayedByUserId(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<PLDTO> pldtos = appMemberService.getPuzzleLevelsPlayed(memberOptional.get());
        return pldtos;
    }


    @PostMapping("/search")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AppMemberDTO> getBinaryContentBySearchCriteria(@RequestBody AppMemberSearchCriteriaDTO criteriaDTO ) {
        Collection<AppMember> appMembers = appMemberService.findByCriteria(criteriaDTO);
        return DTOUtil.getAppMemberDTOS(appMembers);
    }

    @Operation( summary = "Get all history for an Application Member",  description = "get all play history for an Application Member ...")
    @RequestMapping(value = "/id/{id}/playhistory", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PlayHistoryDTO> getPlayHistoryByUserId(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<PlayHistory>  histories= memberOptional.get().getPlayHistories();
        Collection<PlayHistoryDTO> dtos = DTOUtil.getPlayHistoryDTOS(histories);
        return dtos;
    }
    @Operation( summary = "Get a Journey Information with steps and scores for an Application Member",  description = "get a data structure that encompass steps and puzzles for an member and a journey ...")
    @RequestMapping(value = "/id/{id}/journey/jid/{jid}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public AppMemberJourneyInfo getPuzzleLevelMappedStepInJourney(@PathVariable Long id, @PathVariable Long jid) {
        long start_time = System.currentTimeMillis();

        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Optional<Journey> journeyOptional = journeyService.findById(jid);
        AppMemberJourneyInfo journeyInfoWithScores =null;
        if(memberOptional.isEmpty()  || journeyOptional.isEmpty()) return  null;
        AppMemberJourneyInfo journeyInfo = appMemberService.getAppMemberJourneyInfo(memberOptional.get(),journeyOptional.get());
        long end_time = System.currentTimeMillis();
        journeyInfoWithScores =appMemberService.getAppMemberJourneyInfoWithScores(memberOptional.get(),journeyInfo);
        System.out.println("Milliseconds for running getPuzzleLevelMappedStepInJourney Method = " + (end_time - start_time));
        return journeyInfoWithScores;
    }

    @Operation( summary = "Get all journeys for an Application Member with scores",  description = "get all journeys for an Application Member and scores ...")
    @RequestMapping(value = "/id/{id}/journeys", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AppMemberJourneyDTO> getJourneysByUserId(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<Journey> journeys = journeyService.findAll();
        Collection<AppMemberJourneyDTO> dtos = appMemberService.getAppMemberJourneysByScores(memberOptional.get(),journeys);
        return dtos;
    }

    @Operation( summary = "Delete an  Application Member ",  description = "delete an Application Member .....")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteWalletItemById(@PathVariable Long id) {
        Optional<AppMember> requestedRecord = appMemberService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                appMemberService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation, AppMember.class.getSimpleName(), Status.error.name(), id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, AppMember.class.getSimpleName(), Status.ok.name(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,AppMember.class.getSimpleName(), Status.error.name(), id,SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Update Avatar for an App Member ",  description = "Update Avatar for an App Member")
    @RequestMapping(value ="/update-avatar/memberId/{memId}/avatarId/{avatarId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseObject getPuzzleLevel(@PathVariable Long memId, @PathVariable Long avatarId) {
        AppMember updatedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        updatedRecord = appMemberService.updateAvatar(memId, avatarId);
            if(updatedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, BaseObject.class.getSimpleName() , Status.ok.name(), updatedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, BaseObject.class.getSimpleName() , Status.error.name() , -1L ,"e.getCause().getMessage()");

        return responseObject;
    }


    @Operation( summary = "Save an App Member ",  description = "Save an App Member ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveAppMember(@RequestBody AppMemberDTO dto)  {
        AppMember savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<AppMember> appMemberOptional = appMemberService.findById(dto.getId());
        try{
            if (appMemberOptional.isEmpty())
                savedRecord = appMemberService.save(dto,"Save");
            else
                savedRecord = appMemberService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , AppMember.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),AppMember.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), AppMember.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;

    }

    @Operation( summary = "Charge or Decharge a wallet for specific  Member ",  description = "Save a record in APPMember_WalletItem Table : application member wallet management ")
    @PostMapping("/id/{id}/wallet/charge")
    @CrossOrigin(origins = "*")
    public ResponseObject chargeOrDechargeAppMemberWallet(@RequestBody AppMemberWalletDTO dto)  {
        AppMember_WalletItem savedRecord = null;
        ResponseObject responseObject = new ResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = appMemberService.chargeOrDeChargeAppMemberWallet(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + AppMember_WalletItem.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, AppMember_WalletItem.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = appMemberService.chargeOrDeChargeAppMemberWallet(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, AppMember_WalletItem.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, AppMember_WalletItem.class.getSimpleName() , Status.error.name() , -1L ,"e.getCause().getMessage()");
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, AppMember_WalletItem.class.getSimpleName() , Status.error.name() , -1L ,"e.getCause().getMessage()");
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, AppMember_WalletItem.class.getSimpleName() , Status.error.name() , -1L ,"e.getCause().getMessage()");

        return responseObject;
    }

    public boolean checkPLRewardConstraint(WalletItemTransactionDTO dto){
        Long objectiveId = dto.getCounterpartyId();
        Long appMemberId = dto.getAppMemberId();
        Optional<AppMember> appMemberOptional = appMemberService.findById(appMemberId);
        Optional<PLObjective> plObjectiveOptional = plObjectiveService.findById(objectiveId);

        if(appMemberOptional.isEmpty() || plObjectiveOptional.isEmpty() ) return false;

        Optional<WalletTransaction> transactionOptional = walletTransactionService.findByAppMemberAndCounterpartyId(appMemberOptional.get(),plObjectiveOptional.get().getId());
        if(transactionOptional.isPresent()) return false;

        return true;
    }
    public boolean checkPLSkillConstraint(LearningSkillTransactionDTO dto){
        return true;
    }

    @Operation( summary = "Apply Reward for specific  Member after playing puzzles ",  description = "Apply Reward for specific  Member after playing puzzles")
    @PostMapping("/apply-reward")
    @CrossOrigin(origins = "*")
    public ResponseObject applyReward(@RequestBody WalletItemTransactionDTO dto)  {
        WalletTransaction savedRecord = null;
        ResponseObject responseObject = new ResponseObject();
        boolean checkIsRewardBefore = checkPLRewardConstraint(dto);

        if(checkIsRewardBefore == false)
        responseObject = new ResponseObject(ErrorType.UniquenessViolation, WalletTransaction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), "The user got this a reward before!");

        else {
            savedRecord = walletTransactionService.save(dto, "Save");
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletTransaction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);

        }
        return responseObject;
    }

    @Operation( summary = "Apply Skill for specific  Member after playing puzzles ",  description = "Apply Skill for specific  Member after playing puzzles")
    @PostMapping("/apply-skill")
    @CrossOrigin(origins = "*")
    public ResponseObject applySkill(@RequestBody LearningSkillTransactionDTO dto)  {
        LearningSkillTransaction savedRecord = null;
        ResponseObject responseObject = new ResponseObject();
        boolean checkIsRewardBefore = checkPLSkillConstraint(dto);

        if(checkIsRewardBefore == false)
            return new ResponseObject(ErrorType.UniquenessViolation, LearningSkillTransaction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), "The user got this a learning skill before");
       else {
            savedRecord = learningSkillTransactionService.save(dto, "Save");
            responseObject = new ResponseObject(ErrorType.SaveSuccess, AppMember_WalletItem.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        }
        return responseObject;
    }


    @Operation( summary = "Save a Guest User ",  description = "Save a Guest User ")
    @RequestMapping(value = "/save/guest/byear/{byear}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public AppMemberDTO saveGuestUser(@PathVariable Integer byear)  {
        AppMember savedRecord = null;
        AppMemberDTO appMemberDTO = new AppMemberDTO();

            try {
                savedRecord = appMemberService.saveGuestUser(byear);
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + AppMember.class , "Error",savedRecord.getId() );
            }
            appMemberDTO =  DTOUtil.getAppMemberDTO(savedRecord);
        return appMemberDTO;
    }

    @Operation( summary = "Get all wallet items for an application member ",  description = "Get all wallet items")
    @RequestMapping(value = "/id/{id}/wallet/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AppMemberWalletDTO> getWalletDataByUserId(@PathVariable Long id) {
        Optional<AppMember> member = appMemberService.findById(id);
        Collection<AppMember_WalletItem> applicationMember_walletItems = member.get().getApplicationMember_walletItems();
        Collection<AppMemberWalletDTO> dtos = DTOUtil.getAppMemberWalletDTOS(applicationMember_walletItems);
        return dtos;
    }

    @Operation( summary = "Login to System ",  description = "Login Action")
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ALCityAcessRight login(@RequestBody AppMemberDTO memberDTO)  {
        Optional<AppMember> member = appMemberService.findByUsername(memberDTO.getUsername());
        if(member.isEmpty())
            return  new ALCityAcessRight(-1L, memberDTO.getUsername(),-1,"data not found","-1",-1,"error","error","error",-1L,"error","error");
        if(!member.get().getPassword().equals(memberDTO.getPassword()))
            return  new ALCityAcessRight(-1L, memberDTO.getUsername(),-1,"data not found","-1",-1,"error","error","error",-1L,"error","error");
        appMemberService.login(member.get().getUsername(), member.get().getPassword());
        ALCityAcessRight accessRight = new ALCityAcessRight(member.get().getId(), member.get().getUsername(),0,"Login Successfull","JWT Token", member.get().getAge(), memberDTO.getNickname(), memberDTO.getMobile(),
                memberDTO.getEmail(), memberDTO.getIconId(), memberDTO.getMemberType(), memberDTO.getGender());
        return accessRight;
    }


}
