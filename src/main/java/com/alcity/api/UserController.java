package com.alcity.api;

import com.alcity.dto.user.ApplicationMemberWalletDTO;
import com.alcity.dto.user.WalletItemTransactionDTO;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.WalletItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ApplicationMemberService applicationMemberService;


    @GetMapping("/members")
    public Collection<ApplicationMember> getApplicationMembers(Model model) {
        Collection<ApplicationMember> applicationMemberCollection = applicationMemberService.findAll();
        return applicationMemberCollection;
    }

    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<ApplicationMember> getApplicationMemberById(@PathVariable Long id) {
        Optional<ApplicationMember> member = applicationMemberService.findById(id);
        return member;
    }
    @RequestMapping(value = "/member/{id}/wallet/", method = RequestMethod.GET)
    @ResponseBody
    public Collection<ApplicationMemberWalletDTO> getApplicationMemberWalletDataById(@PathVariable Long id) {
        Collection<ApplicationMemberWalletDTO> applicationMemberWalletDTOS = new ArrayList<>();
        Optional<ApplicationMember> member = applicationMemberService.findById(id);
        Collection<ApplicationMember_WalletItem> applicationMember_walletItems = member.get().getApplicationMember_walletItems();
        Collection<WalletItemTransactionDTO> transactionDTOS = new ArrayList<>();

        Iterator<ApplicationMember_WalletItem> itr = applicationMember_walletItems.iterator();
        while(itr.hasNext()) {
            ApplicationMember_WalletItem applicationMember_walletItem = itr.next();
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
    @GetMapping("/wallet-items")
    public Collection<WalletItem> getWalletItems(Model model) {
        Collection<WalletItem> walletItemCollection = walletItemService.findAll();
        return walletItemCollection;
    }
    @RequestMapping(value = "/wallet-items/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<WalletItem> getWalletItemById(@PathVariable Long id) {
        Optional<WalletItem> walletItem = walletItemService.findById(id);
        return walletItem;
    }



}