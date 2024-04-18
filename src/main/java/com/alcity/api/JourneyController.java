package com.alcity.api;


import com.alcity.entity.journey.Journey;
import com.alcity.service.Journey.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;
    @GetMapping("/all")
    public Collection<Journey> getJourneis(Model model) {
        Collection<Journey> journeyCollection = journeyService.findAll();
        return journeyCollection;
    }
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Journey> getJourneyById(@PathVariable Long id) {
        Optional<Journey> journey = journeyService.findById(id);
        return journey;
    }

}
