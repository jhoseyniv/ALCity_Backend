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
    private PuzzleLevelStatusService puzzleLevelStatusService;
    @GetMapping("/genders")
    public List<UserGender> getGenders(Model model) {
        List<UserGender> genders = userGenderService.findAll();
        return genders;
    }

    @Autowired
    private ClientTypeService clientTypeService;
    @GetMapping("/client-types")
    public Collection<ClientType> getClientTypes(Model model) {
        Collection<ClientType> clientTypes = clientTypeService.findAll();
        return clientTypes;
    }



    @Autowired
    private DataTypeService dataTypeService;
    @GetMapping("/data-types")
    public Collection<DataType> getDataTypes(Model model) {
        Collection<DataType> dataTypes = dataTypeService.findAll();
        return dataTypes;
    }

    @Autowired
    private MemberTypeService memberTypeService;
    @GetMapping("/member-types")
    public Collection<MemberType> getMemberType(Model model) {
        Collection<MemberType> memberTypes = memberTypeService.findAll();
        return memberTypes;
    }


    @Autowired
    private PuzzleDifficultyService puzzleDifficultyService;

    @GetMapping("/pl-difficulties")
    public Collection<PuzzleLevelDifficulty> getPuzzleDiffcultyType(Model model) {
        Collection<PuzzleLevelDifficulty> puzzleLevelDifficultiesCollection = puzzleDifficultyService.findAll();
        return puzzleLevelDifficultiesCollection;
    }

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;
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

    @Autowired
    private UserGenderService userGenderService;
    @RequestMapping(value = "genders/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<UserGender> getGenderById(@PathVariable Long id) {
        Optional<UserGender> gender = userGenderService.findById(id);
        return gender;
    }

    @Autowired
    private GameStatusService gameStatusService;

    @GetMapping("/game-status")
    public Collection<GameStatus> getGameStatus(Model model) {
        Collection<GameStatus> gameStatusCollection = gameStatusService.findAll();
        return gameStatusCollection;
    }

    @Autowired
    private PuzzleLevelPrivacyService puzzleLevelPrivacyService;

    @GetMapping("/pl-privacy")
    public Collection<PuzzleLevelPrivacy> getPuzzleLevelPrivacy(Model model) {
        Collection<PuzzleLevelPrivacy> puzzleLevelPrivacyCollection = puzzleLevelPrivacyService.findAll();
        return puzzleLevelPrivacyCollection;
    }

    @Autowired
    private WalletItemTypeService walletItemTypeService;

    @GetMapping("/wallet-types")
    public Collection<WalletItemType> getWalletItemTypes(Model model) {
        Collection<WalletItemType> walletItemTypeCollection = walletItemTypeService.findAll();
        return walletItemTypeCollection;
    }
    @Autowired
    private BinaryContentTypeService binaryContentTypeService;

    @GetMapping("/binary-types")
    public Collection<BinaryContentType> getBinaryTypes(Model model) {
        Collection<BinaryContentType> binaryContentTypeCollection = binaryContentTypeService.findAll();
        return binaryContentTypeCollection;
    }

    @Autowired
    private BinaryContentService binaryContentService;

    @RequestMapping(value="/binary-content/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<BinaryContent> getBinaryContentById(@PathVariable Long id) {
        Optional<BinaryContent> binaryContentCollection = binaryContentService.findById(id);
        return binaryContentCollection;
    }



}