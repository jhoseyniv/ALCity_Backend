package com.alcity.api;

import com.alcity.dto.appmember.AppMemberJourneyDetailDTO;
import com.alcity.dto.appmember.AppMemberJourneyDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.play.PlayHistory;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.customexception.ALCityAcessRight;
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

    @Operation( summary = "Get all journeys for an Application Member with scores",  description = "get all journeys for an Application Member and scores ...")
    @RequestMapping(value = "/id/{id}/jourenys", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AppMemberJourneyDTO> getApplicationMemberJourneysById(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<Journey> journeys = journeyService.findAll();
        Collection<AppMemberJourneyDTO> dtos = appMemberService.getAppMemberJourneysByScores(memberOptional.get(),journeys);
        return dtos;
    }
    @Operation( summary = "Get play history for an Application Member",  description = "get all play history for an Application Member ...")
    @RequestMapping(value = "/id/{id}/playhistory", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PlayHistoryDTO> getApplicationMemberPlayHistoryById(@PathVariable Long id) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Collection<PlayHistory>  histories= memberOptional.get().getPlayHistories();
        Collection<PlayHistoryDTO> dtos = DTOUtil.getPlayHistoryDTOS(histories);
        return dtos;
    }

    @Operation( summary = "Get a journey information for an Application Member with scores in detail",  description = "get a journey for an Application Member and scores ...")
    @RequestMapping(value = "/id/{id}/joureny/jid/{jid}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public AppMemberJourneyDetailDTO getApplicationMemberJourneyById(@PathVariable Long id, @PathVariable Long jid) {
        Optional<AppMember> memberOptional = appMemberService.findById(id);
        Optional<Journey> journeyOptional = journeyService.findById(jid);
        AppMemberJourneyDetailDTO dto = appMemberService.getAppMemberJourneyByScore(memberOptional.get(),journeyOptional.get());
        return dto;
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
    @PostMapping("/save/guest")
    @CrossOrigin(origins = "*")
    public AppMemberDTO saveGuestUser()  {
        AppMember savedRecord = null;
        AppMemberDTO appMemberDTO = new AppMemberDTO();

            try {
                savedRecord = appMemberService.saveGuestUser();
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
    public Collection<AppMemberWalletDTO> getApplicationMemberWalletDataById(@PathVariable Long id) {
        Optional<AppMember> member = appMemberService.findById(id);
        Collection<AppMember_WalletItem> applicationMember_walletItems = member.get().getApplicationMember_walletItems();
        Collection<AppMemberWalletDTO> dtos = DTOUtil.getAppMemberWalletDTOS(applicationMember_walletItems);
        return dtos;
    }

 /*   @Autowired
    private WalletItemService walletItemService;
    @RequestMapping(value = "/id/{id}/wallet-item/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<WalletItemDTO> getWalletItemsByUserId(@PathVariable Long id) {
        Collection<WalletItem> walletItemCollection = walletItemService.findAll();
        Collection<WalletItemDTO> dtos = new ArrayList<WalletItemDTO>();
        dtos = DTOUtil.getWalletItemDTOS(walletItemCollection);
        return dtos;
    }
*/
    @Operation( summary = "Login to System ",  description = "Login Action")
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ALCityAcessRight login(@RequestBody AppMemberDTO memberDTO)  {
        AppMember member = appMemberService.findByUsername(memberDTO.getUsername());
        if(member==null)
            return  new ALCityAcessRight(-1L, memberDTO.getUsername(), -1, "data Not Found!");
        if(!member.getPassword().equals(memberDTO.getPassword()))
            return  new ALCityAcessRight(-1L, memberDTO.getUsername(), -1, "data Not Found!");
        appMemberService.login(member.getUsername(), member.getPassword());
        ALCityAcessRight acessRight = new ALCityAcessRight(member.getId(), member.getUsername(),0,"Login Success...");
        return acessRight;
    }


}
