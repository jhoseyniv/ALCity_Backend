package com.alcity.api;

import com.alcity.dto.alobject.RendererDTO;
import com.alcity.dto.appmember.AppMemberEnergyDTO;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.EnergyConfig;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.EnergyConfigService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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


}
