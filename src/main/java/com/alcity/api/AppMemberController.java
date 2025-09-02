package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.appmember.*;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.search.AppMemberSearchCriteriaDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.appmember.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import com.alcity.service.Journey.JourneyService;
import com.alcity.o3rdparty.ALCityAcessRight;
import com.alcity.service.appmember.*;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.service.base.ClientTypeService;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Application Member APIs", description = "Get Application Member and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/user")
public class AppMemberController {

    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private ClientTypeService clientTypeService;

    @Autowired
    private PLObjectiveService plObjectiveService;
    @Autowired
    private AppMember_WalletItemService appMemberWalletItemService;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private LearningSkillTransactionService learningSkillTransactionService;

    @Autowired
    private AppMember_LearningSkillService appMemberLearningSkillService;


    @Operation( summary = "Get XP by this week for an app member ",  description = "Get XP by this week for an app member")
    @RequestMapping(value = "/id/{id}/xp-week", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AppMemberWeekXPDTO> getXPByWeek(@PathVariable Long id) {
        Collection<AppMemberWeekXPDTO> dtos = new ArrayList<>();
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        if(memberOptional.isEmpty())
            return null;
        LocalDateTime today = LocalDateTime.now();

        Collection<LearningSkillTransaction> transactions_0 = learningSkillTransactionService.findByTransactionDateContainingAndAppMember(DateUtils.getDate(today),memberOptional.get());
        AppMemberWeekXPDTO appMemberWeekXPDT_0 = DTOUtil.getXPForADate(transactions_0,today,id);
        dtos.add(appMemberWeekXPDT_0);

        Collection<LearningSkillTransaction> transactions_1 = learningSkillTransactionService.findByTransactionDateContaining(DateUtils.getDate(today.minusDays(1)));
        AppMemberWeekXPDTO appMemberWeekXPDT_1 = DTOUtil.getXPForADate(transactions_1,today.minusDays(1),id);
        dtos.add(appMemberWeekXPDT_1);

        Collection<LearningSkillTransaction> transactions_2 = learningSkillTransactionService.findByTransactionDateContaining(DateUtils.getDate(today.minusDays(2)));
        AppMemberWeekXPDTO appMemberWeekXPDT_2 = DTOUtil.getXPForADate(transactions_2,today.minusDays(2),id);
        dtos.add(appMemberWeekXPDT_2);

        Collection<LearningSkillTransaction> transactions_3 = learningSkillTransactionService.findByTransactionDateContaining(DateUtils.getDate(today.minusDays(3)));
        AppMemberWeekXPDTO appMemberWeekXPDT_3 = DTOUtil.getXPForADate(transactions_3,today.minusDays(3),id);
        dtos.add(appMemberWeekXPDT_3);

        Collection<LearningSkillTransaction> transactions_4 = learningSkillTransactionService.findByTransactionDateContaining(DateUtils.getDate(today.minusDays(4)));
        AppMemberWeekXPDTO appMemberWeekXPDT_4 = DTOUtil.getXPForADate(transactions_4,today.minusDays(4),id);
        dtos.add(appMemberWeekXPDT_4);

        Collection<LearningSkillTransaction> transactions_5 = learningSkillTransactionService.findByTransactionDateContaining(DateUtils.getDate(today.minusDays(5)));
        AppMemberWeekXPDTO appMemberWeekXPDT_5 = DTOUtil.getXPForADate(transactions_5,today.minusDays(5),id);
        dtos.add(appMemberWeekXPDT_5);

        Collection<LearningSkillTransaction> transactions_6 = learningSkillTransactionService.findByTransactionDateContaining(DateUtils.getDate(today.minusDays(6)));
        AppMemberWeekXPDTO appMemberWeekXPDT_6 = DTOUtil.getXPForADate(transactions_6,today.minusDays(6),id);
        dtos.add(appMemberWeekXPDT_6);

        return dtos;
    }

    @Operation( summary = "Get skill Radar Chart for an app member ",  description = "this api get a radar chart from fundamental skills for a player ")
    @RequestMapping(value = "/id/{id}/skill-radar-chart", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<LearningSkillRadarDTO> getRadarChartData(@PathVariable Long id) {
        Collection<LearningSkillRadarDTO> dtos = new ArrayList<>();
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        if(memberOptional.isEmpty())
            return null;
        Collection<AppMember_LearningSkill> memberSkills = appMemberLearningSkillService.findByApplicationMember(memberOptional.get());
        Collection<AppMember_LearningSkill> majorSkills = memberSkills.stream().filter(memberLearningSkill -> memberLearningSkill.getLearningSkill().getType().equals(SkillType.Skill)).collect(Collectors.toList());
        dtos = DTOUtil.getLearningSkillRadarDTOS(majorSkills);
        return dtos;
    }


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
    public ResponseEntity<byte[]> getAvatarById(@PathVariable Long id) {
        Optional<AppMember>  appMemberOptional= appMemberService.findById(id);
        if(appMemberOptional.isEmpty()) return  null;
        BinaryContent binaryContent = appMemberOptional.get().getIcon();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + binaryContent.getFileName() + "\"")
                .body(binaryContent.getContent());
    }

    @Operation( summary = "Get Avatar by User name ",  description = "Get Avatar by User name ...")
    @GetMapping("/get-avatar/user/{user}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getAvatarByUserName(@PathVariable String user) {
        Optional<AppMember>  appMemberOptional= appMemberService.findByUsername(user);
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
                throw  new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(),AppMember.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), AppMember.class.getSimpleName(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),AppMember.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Update Avatar for an App Member ",  description = "Update Avatar for an App Member")
    @RequestMapping(value ="/update-avatar/memberId/{memId}/avatarId/{avatarId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseMessage getPuzzleLevel(@PathVariable Long memId, @PathVariable Long avatarId) {
        AppMember updatedRecord = null;
        ResponseMessage response = new ResponseMessage();

        updatedRecord = appMemberService.updateAvatar(memId, avatarId);
            if(updatedRecord !=null)
                response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),AppMember.class.getSimpleName() ,  updatedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                response = new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() ,AppMember.class.getSimpleName() ,  memId ,SystemMessage.RecordNotFound);

        return response;
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
            if(savedRecord !=null)
                response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),AppMember.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), AppMember.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , AppMember.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
           return response;

    }

    @Operation( summary = "Charge or De charge a wallet for specific  Member ",  description = "Save a record in APPMember_WalletItem Table : application member wallet management ")
    @PostMapping("/id/{id}/wallet/charge")
    @CrossOrigin(origins = "*")
    public ResponseMessage chargeOrDechargeAppMemberWallet(@RequestBody AppMemberWalletDTO dto)  {
        AppMember_WalletItem savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<AppMember_WalletItem> appMember_walletItemOptional = appMemberWalletItemService.findById(dto.getId());
            try {
                if( appMember_walletItemOptional.isEmpty())
                        savedRecord = appMemberService.chargeOrDeChargeAppMemberWallet(dto,"Save");
                 else
                        savedRecord = appMemberService.chargeOrDeChargeAppMemberWallet(dto, "Edit");

                if(savedRecord !=null)
                    response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), AppMember_WalletItem.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
                else
                    response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), AppMember_WalletItem.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);

            } catch (RuntimeException e) {
                throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , AppMember_WalletItem.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
            }

        return response;
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
        Long objectiveId = dto.getObjectiveId();
        Long appMemberId = dto.getAppMemberId();
        Optional<AppMember> appMemberOptional = appMemberService.findById(appMemberId);
        Optional<PLObjective> plObjectiveOptional = plObjectiveService.findById(objectiveId);

        if(appMemberOptional.isEmpty() || plObjectiveOptional.isEmpty() ) return false;

        Optional<LearningSkillTransaction> transactionOptional = learningSkillTransactionService.findByPlObjectiveAndAppMember(plObjectiveOptional.get(),appMemberOptional.get());
        if(transactionOptional.isPresent()) return false;

        return true;
    }

    @Operation( summary = "Apply Reward for specific  Member after playing puzzles ",  description = "Apply Reward for specific  Member after playing puzzles")
    @PostMapping("/apply-reward")
    @CrossOrigin(origins = "*")
    public ResponseMessage applyReward(@RequestBody WalletItemTransactionDTO dto)  {
        WalletTransaction savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        boolean checkIsRewardBefore = checkPLRewardConstraint(dto);

        if(checkIsRewardBefore == false)
            response = new ResponseMessage(ErrorType.UniquenessViolation, WalletTransaction.class.getSimpleName() , Status.ok.name(), -1L,SystemMessage.UserGotThisRewardBefore );

        else {
            savedRecord = walletTransactionService.save(dto, "Save");
            response = new ResponseMessage(ErrorType.SaveSuccess, WalletTransaction.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);

        }
        return response;
    }

    @Operation( summary = "Apply Skill for specific  Member after playing puzzles ",  description = "Apply Skill for specific  Member after playing puzzles")
    @PostMapping("/apply-skill")
    @CrossOrigin(origins = "*")
    public ResponseMessage applySkill(@RequestBody LearningSkillTransactionDTO dto)  {
        LearningSkillTransaction savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        boolean checkIsRewardBefore = checkPLSkillConstraint(dto);

        if(checkIsRewardBefore == false)
            return new ResponseMessage(ErrorType.UniquenessViolation, Status.ok.name() , LearningSkillTransaction.class.getSimpleName() , savedRecord.getId(), "The user got this a learning skill before");
       else {
            savedRecord = learningSkillTransactionService.save(dto, "Save");
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name() ,LearningSkillTransaction.class.getSimpleName() , savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            learningSkillTransactionService.updateAppMemberSkills(savedRecord);
       }
        return response;
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
    @Operation( summary = "Save Client Type for an app member",  description = "Save Client Type for an app member")
    @RequestMapping(value = "/save-client-type/memId/{memId}/ctype/{ctype}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseMessage saveClientType(@PathVariable Long memId,@PathVariable String ctype) {
        Optional<AppMember> appMemberOptional = appMemberService.findById(memId);
        if(appMemberOptional.isEmpty())
            return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() ,AppMember.class.getSimpleName() ,  memId , SystemMessage.RecordNotFound);
        ClientType clientType = clientTypeService.findByValue(ctype);
        if(clientType == null)
            return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() ,AppMember.class.getSimpleName() ,  memId , SystemMessage.RecordNotFound);
        ResponseMessage response = appMemberService.setClientType(appMemberOptional.get(),clientType);
        return response;
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
