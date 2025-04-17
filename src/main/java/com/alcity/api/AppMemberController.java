package com.alcity.api;

import com.alcity.dto.appmember.AppMemberJourneyDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.entity.appmember.AppMemberJourneyInfo;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.play.PlayHistory;
import com.alcity.service.Journey.JourneyService;
import com.alcity.o3rdparty.ALCityAcessRight;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.appmember.AppMemberWalletDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private JourneyService journeyService;

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
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Optional<Journey> journeyOptional = journeyService.findById(jid);
        AppMemberJourneyInfo journeyInfoWithScores =null;
        if(memberOptional.isEmpty()  || journeyOptional.isEmpty()) return  null;
        AppMemberJourneyInfo journeyInfo = appMemberService.getAppMemberJourneyInfo(memberOptional.get(),journeyOptional.get());
        journeyInfoWithScores =appMemberService.getAppMemberJourneyInfoWithScores(memberOptional.get(),journeyInfo);
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
    @Operation( summary = "delete an  Application Member ",  description = "delete an Application Member .....")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteWalletItemById(@PathVariable Long id) {
        Optional<AppMember> existingRecord = appMemberService.findById(id);
        if(existingRecord.isPresent()){
            try {
                appMemberService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getUsername(), existingRecord.get().getId(), AppMember.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "Update Avatar for an App Member ",  description = "Update Avatar for an App Member")
  //  @PostMapping("/update-avatar")
    @RequestMapping(value ="/update-avatar/memberId/{memId}/avatarId/{avatarId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ALCityResponseObject getPuzzleLevel(@PathVariable Long memId,@PathVariable Long avatarId) {
        AppMember updatedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        updatedRecord = appMemberService.updateAvatar(memId, avatarId);
            if(updatedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", updatedRecord.getId(), "Record Updated Successfully!");
            else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
    }


    @Operation( summary = "Save an App Member ",  description = "Save an App Member ")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveAppMember(@RequestBody AppMemberDTO dto)  {
        AppMember savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = appMemberService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getUsername(), dto.getId(), "title must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = appMemberService.save(dto, "Edit");
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

    @Operation( summary = "Charge or Decharge a wallet for specific  Member ",  description = "Save a record in APPMember_WalletItem Table : application member wallet management ")
    @PostMapping("/id/{id}/wallet/charge")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject chargeOrDechargeAppMemberWallet(@RequestBody AppMemberWalletDTO dto)  {
        AppMember_WalletItem savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = appMemberService.chargeOrDeChargeAppMemberWallet(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getAppMemberUsername(), dto.getId(), "title must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Wallet Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = appMemberService.chargeOrDeChargeAppMemberWallet(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Wallet Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

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
                throw new UniqueConstraintException("dto.getUsername()", -1L, "user name must be Unique");
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
