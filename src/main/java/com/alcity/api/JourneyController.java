package com.alcity.api;


import com.alcity.entity.journey.Journey;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.users.WalletItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @GetMapping("/")
    public Collection<Journey> getJourneis(Model model) {
        Collection<Journey> journeyCollection = journeyService.findAll();
        return journeyCollection;
    }



}
