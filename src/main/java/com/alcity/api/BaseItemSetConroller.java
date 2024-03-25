package com.alcity.api;

import com.alcity.entity.base.*;
import com.alcity.service.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/base")
public class BaseItemSetConroller {

    @Autowired
    private UserGenderService userGenderService;

    @Autowired
    private ClientTypeService clientTypeService;

    @Autowired
    private DataTypeService dataTypeService;

    @Autowired
    private MemberTypeService memberTypeService;

    @Autowired
    private PuzzleDifficultyService puzzleDifficultyService;

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PuzzleLevelStatusService puzzleLevelStatusService;




    @GetMapping("/genders")
    public List<UserGender> getGenders(Model model) {
        List<UserGender> genders = userGenderService.findAll();
        return genders;
    }
    @GetMapping("/clients")
    public Collection<ClientType> getClientTypes(Model model) {
        Collection<ClientType> clientTypes = clientTypeService.findAll();
        return clientTypes;
    }

    @GetMapping("/datatypes")
    public Collection<DataType> getDataTypes(Model model) {
        Collection<DataType> dataTypes = dataTypeService.findAll();
        return dataTypes;
    }

    @GetMapping("/membertypes")
    public Collection<MemberType> getMemberType(Model model) {
        Collection<MemberType> memberTypes = memberTypeService.findAll();
        return memberTypes;
    }

    @GetMapping("/pl_difficulty")
    public Collection<PuzzleLevelDifficulty> getPuzzleDiffcultyType(Model model) {
        Collection<PuzzleLevelDifficulty> puzzleLevelDifficultiesCollection = puzzleDifficultyService.findAll();
        return puzzleLevelDifficultiesCollection;
    }

    @GetMapping("/pl-category")
    public Collection<PuzzleCategory> getPuzzleCategories(Model model) {
        Collection<PuzzleCategory> puzzleCategoryCollection = puzzleCategoryService.findAll();
        return puzzleCategoryCollection;
    }

    @GetMapping("/pl-status")
    public Collection<PuzzleLevelStatus> getPuzzleLevelStatus(Model model) {
        Collection<PuzzleLevelStatus> puzzleLevelStatusCollection = puzzleLevelStatusService.findAll();
        return puzzleLevelStatusCollection;
    }




    @RequestMapping(value = "genders/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<UserGender> getGenderById(@PathVariable Long id) {
        Optional<UserGender> gender = userGenderService.findById(id);
        return gender;
    }

}