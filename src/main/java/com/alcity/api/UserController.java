package com.alcity.api;

import com.alcity.entity.base.UserGender;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.WalletItem;
import com.alcity.service.base.UserGenderService;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.WalletItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WalletItemService walletItemService;

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


    @GetMapping("/wallet-items")
    public Collection<WalletItem> getWalletItems(Model model) {
        Collection<WalletItem> walletItemCollection = walletItemService.findAll();
        return walletItemCollection;
    }

}
