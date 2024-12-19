package com.alcity.api;

import com.alcity.dto.base.AdvertisementDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.alenum.ADSType;
import com.alcity.entity.base.Advertisement;
import com.alcity.entity.base.ClientType;
import com.alcity.service.base.AdvertisementService;
import com.alcity.service.base.ClientTypeService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Advertisement APIs", description = "All APIs for Advertisement... ")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/ads")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;


    @Operation( summary = "Fetch Terms and Condition",  description = "fetch terms and condition their data from data source")
    @GetMapping("/term-cond")
    @CrossOrigin(origins = "*")
    public AdvertisementDTO getTermsAndCond(Model model) {
        Advertisement termAndCond = advertisementService.findTermAndCond();
        AdvertisementDTO dto = DTOUtil.getAdvertisementDTO(termAndCond);
        return dto;
    }

}
