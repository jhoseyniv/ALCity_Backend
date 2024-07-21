package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.alobject.ObjectCategoryDTO;
import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.appmember.AppMemberWalletDTO;
import com.alcity.dto.appmember.WalletItemDTO;
import com.alcity.dto.appmember.WalletItemTransactionDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletTransaction;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.WalletItemService;
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

    @RequestMapping(value = "/id/{id}/wallet/", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<AppMemberWalletDTO> getApplicationMemberWalletDataById(@PathVariable Long id) {
        Collection<AppMemberWalletDTO> applicationMemberWalletDTOS = new ArrayList<>();
        Optional<AppMember> member = appMemberService.findById(id);
        Collection<AppMember_WalletItem> applicationMember_walletItems = member.get().getApplicationMember_walletItems();
        Collection<WalletItemTransactionDTO> transactionDTOS = new ArrayList<>();

        Iterator<AppMember_WalletItem> itr = applicationMember_walletItems.iterator();
        while(itr.hasNext()) {
            AppMember_WalletItem applicationMember_walletItem = itr.next();
            AppMemberWalletDTO applicationMemberWalletDTO = new AppMemberWalletDTO();
            WalletItem walletItem = applicationMember_walletItem.getWalletItem();
            applicationMemberWalletDTO.setWalletItemTitle(walletItem.getLabel());
            applicationMemberWalletDTO.setWalletItemId(walletItem.getId());
            applicationMemberWalletDTO.setAmount(applicationMember_walletItem.getAmount());

            WalletItemTransactionDTO transactionDTO = new WalletItemTransactionDTO();
            Collection<WalletTransaction>  transactions = applicationMember_walletItem.getWalletTransactionSet();
            Iterator<WalletTransaction> itrTransactions = transactions.iterator();
            while (itrTransactions.hasNext()) {
                WalletTransaction walletTransaction = itrTransactions.next();
                transactionDTO.setIncTransaction(walletTransaction.getIncTransaction());
                transactionDTO.setDescription(walletTransaction.getDescription());
                transactionDTO.setAmount(walletTransaction.getAmount());
                transactionDTO.setTransactionDate(walletTransaction.getTransactionDate());
                transactionDTOS.add(transactionDTO);
            }
            applicationMemberWalletDTO.setWalletItemTransactionDTOSet(transactionDTOS);
            applicationMemberWalletDTOS.add(applicationMemberWalletDTO);
        }

        return applicationMemberWalletDTOS;
    }

    @Autowired
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

    @Operation( summary = "Login to System ",  description = "Login Action")
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject login(@RequestBody AppMemberDTO memberDTO)  {
        ALCityResponseObject responseObject = new ALCityResponseObject();

        AppMember member = appMemberService.findByUsername(memberDTO.getUsername());
        if(member==null)
            return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "data Not Found!");


        if(!member.getPassword().equals(memberDTO.getPassword()))
            return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "data Not Found!");
        appMemberService.login(member.getUsername(), member.getPassword());
        return responseObject;
    }


}
