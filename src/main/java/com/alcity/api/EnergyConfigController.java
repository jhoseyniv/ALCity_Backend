package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.alobject.RendererDTO;
import com.alcity.dto.appmember.AppMemberEnergyDTO;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.*;
import com.alcity.service.appmember.*;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Tag(name = "Energy Config", description = "The Energy  API's ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/ec")

public class EnergyConfigController {
    @Autowired
    private AppMemberService memberService;

    @Autowired
    EnergyConfigService energyConfigService;

    @Autowired
    AppMember_WalletItemService appMemberWalletItemService;

    @Autowired
    AppMemberService appMemberService;

    @Autowired
    WalletItemChangeRateService walletItemChangeRateService;

    @Autowired
    WalletItemService walletItemService;



    @Operation( summary = "Fetch An Energy Timer Size by Minute  ",  description = "Fetch An Energy Timer Size by Minute")
    @RequestMapping(value = "/timer-min/", method = RequestMethod.GET)
    @ResponseBody
    public Integer get() {
        Optional<EnergyConfig> energyConfigOptional = energyConfigService.findByExpireIsFalse();
        if (energyConfigOptional.isPresent())
            return energyConfigOptional.get().getTimeToRefill();
        return 40;
    }
    @Operation( summary = "Fetch Minimum Energy setting Score for Users   ",  description = "Fetch Minimum Energy Score for Users ")
    @RequestMapping(value = "/energy-level/", method = RequestMethod.GET)
    @ResponseBody
    public Integer getEnergyLevel() {
        Optional<EnergyConfig> energyConfigOptional = energyConfigService.findByExpireIsFalse();
        if (energyConfigOptional.isPresent())
            return energyConfigOptional.get().getEnergy();
        return 8;
    }

    @Operation( summary = "Fetch expiration time to refill energy for a app member by id  ",  description = "Fetch expiration time to refill energy for a app member by id")
    @RequestMapping(value = "/member/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AppMemberEnergyDTO getAppMemberEnergyInfo(@PathVariable Long id) {
        Optional<AppMember> appMemberOptional = memberService.findById(id);
        return appMemberOptional.map(member -> new AppMemberEnergyDTO(
                member.getEnergy(),
                member.getRefillEnergyExpirationTime())).orElse(null);
    }

    @Operation( summary = "Fetch  rest of seconds for expiration time to refill energy for a app member by id  ",  description = "Fetch expiration time to refill energy for a app member by id")
    @RequestMapping(value = "/member-seconds/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Long> getRemainingEnergyRefillSeconds(@PathVariable Long id) {

        return memberService.findById(id)
                .map(member -> {
                    ZonedDateTime expiration = member.getRefillEnergyExpirationTime();
                    if (expiration == null) {
                        return ResponseEntity.ok(0L);
                    }

                    long secondsRemaining = ChronoUnit.SECONDS.between(ZonedDateTime.now(), expiration);
                    return ResponseEntity.ok(Math.max(secondsRemaining, 0)); // اگر منفی بود صفر
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation( summary = "Fetch  Change rate of 1 energy to AL-Coin as base currency  ",  description = "Fetch  Change rate of 1 energy to AL-Coin as base currency")
    @RequestMapping(value = "/change-energy-rate", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Float> getChangeEnergyRate() {
        return ResponseEntity.ok(energyConfigService.getChangeRateForEnergyToALCoin());
    }

    @Operation( summary = "buying energy by a app member and number of energy",  description = "number of energy unit for buying by a app member")
    @RequestMapping(value = "/member/id/{id}/buy-energy/{num}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public ResponseMessage numberOfEnergyForBuy(@PathVariable Long id  , @PathVariable Integer num) {
        Optional<WalletItem> alCoinOptional = walletItemService.findByValue("AL Coin");
        Optional<AppMember> appMemberOptional = appMemberService.findById(id);
        Optional<EnergyConfig> energyConfigOptional = energyConfigService.findByExpireIsFalse();
        Integer maxEnergy = energyConfigOptional.get().getEnergy();
        if(alCoinOptional.isPresent() && appMemberOptional.isPresent()) {
            Optional<AppMember_WalletItem>  appMember_walletItemOptional = appMemberWalletItemService.findByApplicationMemberAndWalletItem(appMemberOptional.get(), alCoinOptional.get());
            Integer currentEnergy = appMemberOptional.get().getEnergy();

            if (appMember_walletItemOptional.isPresent()) {
                Float changeRate = energyConfigService.getChangeRateForEnergyToALCoin();
                Float requiredALCoin = changeRate * num;
                AppMember_WalletItem walletItem = appMember_walletItemOptional.get();
                Integer newEnergy = currentEnergy + num;
                if(requiredALCoin > walletItem.getAmount())
                    return new ResponseMessage(ErrorType.Not_Enough_AL_Coin_To_Buy_Energy, Status.info.name(), AppMember_WalletItem.class.getSimpleName() , 0L, SystemMessage.Not_Enough_AL_Coin_To_Buy_Energy);
                else if( newEnergy > maxEnergy ) {
                    return new ResponseMessage(ErrorType.Your_Energy_Is_Full_Or_Grater_Than_You_Want_To_Buy, Status.info.name(), AppMember_WalletItem.class.getSimpleName() , 0L, SystemMessage.Your_Energy_Is_Full_Or_Grater_Than_You_Want_To_Buy);
                }
                else {
                    walletItem.setAmount(walletItem.getAmount() - requiredALCoin);
                    appMemberOptional.get().setEnergy(newEnergy);
                    return new ResponseMessage(ErrorType.Your_Energy_Refill_Successfully, Status.info.name(), AppMember_WalletItem.class.getSimpleName(), 0L, SystemMessage.Your_Energy_Refill_Successfully);
                }
            }
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.info.name(), ObjectiveTransaction.class.getSimpleName() , 0L, SystemMessage.RecordNotFound);

    }

}

