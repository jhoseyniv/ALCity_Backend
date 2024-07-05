package com.alcity.api;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.dto.user.ApplicationMemberDTO;
import com.alcity.dto.user.ApplicationMemberWalletDTO;
import com.alcity.dto.user.WalletItemDTO;
import com.alcity.dto.user.WalletItemTransactionDTO;
import com.alcity.entity.users.AppMember;
import com.alcity.entity.users.AppMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.WalletItemService;
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
public class UserController {


    @Autowired
    private ApplicationMemberService applicationMemberService;


    @GetMapping("/members/all")
    public Collection<AppMember> getApplicationMembers(Model model) {
        Collection<AppMember> applicationMemberCollection = applicationMemberService.findAll();
        return applicationMemberCollection;
    }

    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<AppMember> getApplicationMemberById(@PathVariable Long id) {
        Optional<AppMember> member = applicationMemberService.findById(id);
        return member;
    }

    @RequestMapping(value = "/member/{id}/wallet/", method = RequestMethod.GET)
    @ResponseBody
    public Collection<ApplicationMemberWalletDTO> getApplicationMemberWalletDataById(@PathVariable Long id) {
        Collection<ApplicationMemberWalletDTO> applicationMemberWalletDTOS = new ArrayList<>();
        Optional<AppMember> member = applicationMemberService.findById(id);
        Collection<AppMember_WalletItem> applicationMember_walletItems = member.get().getApplicationMember_walletItems();
        Collection<WalletItemTransactionDTO> transactionDTOS = new ArrayList<>();

        Iterator<AppMember_WalletItem> itr = applicationMember_walletItems.iterator();
        while(itr.hasNext()) {
            AppMember_WalletItem applicationMember_walletItem = itr.next();
            ApplicationMemberWalletDTO applicationMemberWalletDTO = new ApplicationMemberWalletDTO();
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
    public Collection<WalletItemDTO> getWalletItemsByUserId(@PathVariable Long id) {
        Collection<WalletItem> walletItemCollection = walletItemService.findAll();
        Collection<WalletItemDTO> dtos = new ArrayList<WalletItemDTO>();
        dtos = DTOUtil.getWalletItemDTOS(walletItemCollection);
        return dtos;
    }

    @RequestMapping(value = "/wallet-item/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WalletItemDTO getWalletItemById(@PathVariable Long id) {
        Optional<WalletItem> walletItemOptional = walletItemService.findById(id);
        if(walletItemOptional.isEmpty()) return null;
        return DTOUtil.getWalletItemDTO(walletItemOptional.get());
    }

    @Operation( summary = "Login to System ",  description = "Login Action")
    @PostMapping("/user/login")
    public ALCityResponseObject login(@RequestBody ApplicationMemberDTO memberDTO)  {
        ALCityResponseObject responseObject = new ALCityResponseObject();

        AppMember member = applicationMemberService.findByUsername(memberDTO.getUsername());
        if(member==null)
            return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "data Not Found!");


        if(!member.getPassword().equals(memberDTO.getPassword()))
            return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "data Not Found!");
        applicationMemberService.login(member.getUsername(), member.getPassword());
        return responseObject;
    }


}
