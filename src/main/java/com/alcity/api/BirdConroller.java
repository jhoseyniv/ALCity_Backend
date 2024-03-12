package com.alcity.api;

import com.alcity.entity.base.UserGender;
import com.alcity.service.base.UserGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/commodities")
public class BirdConroller {

   @Autowired
    private UserGenderService userGenderService;


    @GetMapping("/")
    public List<UserGender> getCommodities(Model model) {
        List<UserGender> birds = userGenderService.findAll();
        return birds;
    }

    @RequestMapping(value = "/ids/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<UserGender> getBirdById(@PathVariable Long id) {
        Optional<UserGender> bird = userGenderService.findById(id);
        return bird;
    }

    @GetMapping( "/bnames/{bname}")
    public BirdDTO getBirdByName(@PathVariable String bvalue) {
       // BaseTable bird = userGenderService.findByValue(bvalue);
        //must be implementd
        BirdDTO birdBean= null;
        return birdBean;
    }




}