package com.alcity.service.puzzle;

import com.alcity.dto.plimpexport.*;
import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.dto.plimpexport.rulemport.PLRuleImport;
import com.alcity.dto.puzzle.PLCopyDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PuzzleLevelStepMappingDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.appmember.PLObjectiveTransaction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.PLPrivacyRepository;
import com.alcity.repository.journey.JourneyStepRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.PLObjectiveTransactionService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.test.importstruct.PLImportDTO_New;
import com.alcity.test.ruleimport_new.PLRuleImport_New;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PuzzleLevelService implements PuzzleLevelRepository {


    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;

    @Autowired
    JourneyStepRepository journeyStepRepository;

    @Autowired
    PLGameInstanceService gameInstanceService;
    @Autowired
    private PLObjectiveTransactionService learningSkillTransactionService;


    @Override
    public <S extends PuzzleLevel> S save(S entity) {
        return puzzleLevelRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevel> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevel> findById(Long aLong) {
        if(aLong == null) return Optional.empty();
        return puzzleLevelRepository.findById(aLong);
    }

    @Override
    public Collection<PuzzleLevel> findAll() {
        return puzzleLevelRepository.findAll();
    }
    public Collection<PuzzleLevel> findAllMatchesToAge(Integer age) {
        Collection<PuzzleLevel> puzzleLevels = puzzleLevelRepository.findAll();
        Collection<PuzzleLevel> newpuzzleLevels =puzzleLevels.stream().filter(puzzleLevel -> puzzleLevel.getFromAge() <= age && age <= puzzleLevel.getToAge()).collect(Collectors.toList());
        return newpuzzleLevels;
    }
    public JourneyStep getFirstItem(Collection<JourneyStep> steps){
        Iterator<JourneyStep> itr = steps.iterator();
        if(itr.hasNext())  return itr.next();
        return  null;
    }

    public Collection<PuzzleLevelStepMappingDTO> getJourneyStepsMatchWithPuzzleLvels(Collection<PuzzleLevel> puzzleLevels) {
        Collection<PuzzleLevelStepMappingDTO> puzzleLevelStepMappingDTOS = new ArrayList<>();
        Iterator<PuzzleLevel> itr = puzzleLevels.iterator();
        while (itr.hasNext()) {
            PuzzleLevel puzzleLevel = itr.next();
            PuzzleGroup puzzleGroup = puzzleLevel.getPuzzleGroup();

            Collection<JourneyStep> steps = puzzleGroup.getJourneyStepCollection();
            JourneyStep step = getFirstItem(steps);
            PuzzleLevelStepMappingDTO  dto =  DTOUtil.puzzleLevelJourneyStepMapping(puzzleLevel,step) ;
            puzzleLevelStepMappingDTOS.add(dto);
        }

        return  puzzleLevelStepMappingDTOS;
    }
    public Long getJourneyIdMappedWithPuzzleLevel(PuzzleLevel puzzleLevel) {
        PuzzleGroup puzzleGroup = puzzleLevel.getPuzzleGroup();
        Collection<JourneyStep> steps = puzzleGroup.getJourneyStepCollection();
        JourneyStep step = getFirstItem(steps);
        if(step == null || step.getJourney() == null) return  -1L;
        Long journeyId = step.getJourney().getId();
        return  journeyId;
    }


    @Override
    public Collection<PuzzleLevel> findByTitle(String title) {
        return puzzleLevelRepository.findByTitle(title);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public Optional<PuzzleLevel> findByCode(String code) {
        return puzzleLevelRepository.findByCode(code);
    }

    @Override
    public Optional<PuzzleLevel> findByPicture(BinaryContent pic) {
        return puzzleLevelRepository.findByPicture(pic);
    }

    @Override
    public Optional<PuzzleLevel> findByIcon(BinaryContent icon) {
        return puzzleLevelRepository.findByIcon(icon);
    }

    @Override
    public Collection<PuzzleLevel> findByPuzzleLevelPrivacy(PLPrivacy privacy) {
        return puzzleLevelRepository.findByPuzzleLevelPrivacy(privacy);
    }

    @Override
    public Iterable<PuzzleLevel> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        puzzleLevelRepository.deleteById(aLong);
    }

    @Override
    public void delete(PuzzleLevel entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Qualifier("PLPrivacyRepository")
    @Autowired
    private PLPrivacyRepository plPrivacyRepository;

    @Qualifier("PGRepository")
    @Autowired
    private PGRepository pgRepository;
    @Autowired
    private BinaryContentService binaryContentService;
    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private PLGroundService plGroundService;

    @Autowired
    private PLCellService plCellService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private PLObjectiveService plObjectiveService;
    @Autowired
    private PLRuleService plRuleService;
    @Autowired
    PLLearningTopicService plLearningTopicService;
    @Autowired
    BinaryContentRepository binaryContentRepository;

    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Autowired
    @Lazy
    private InstanceService instanceInPLService;

    public PuzzleLevel getPuzzleLevelFromJson(PLImportDTO plData) {
        Optional<AppMember> creatorOptional = appMemberService.findByUsername("admin");
       PuzzleLevel puzzleLevel = new PuzzleLevel();

//        AppMember creator,String approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore,Float firstStarScore,Float secondStarScore,Float thirdStartScore,
//                PuzzleGroup puzzleGroup, PLDifficulty puzzleDifficulty, PLStatus puzzleLevelStatus, PLPrivacy puzzleLevelPrivacy,BinaryContent picture ,BinaryContent icon,
//                Long version, String created, String updated, AppMember createdBy, AppMember updatedBy

        return puzzleLevel;
    }

    public PuzzleLevel importPuzzleLevel(PLImportDTO dto) throws IOException, ClassNotFoundException {
        // import puzzle level header
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<BinaryContent> iconOptional = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> picOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> boardGraphicOptional = binaryContentRepository.findById(dto.getBoardGraphicId());

       // Optional<PLGround> plGroundOptional = plGroundService.findById(238L);
       // BoardGraphicDTO boardGraphicDTO = PLDTOUtil.getBoardGraphicJSON(plGroundOptional.get());
      //  byte[] boardGraphic = ImageUtil.convertObjectToBytes(boardGraphicDTO);

        PLDifficulty plDifficulty =  PLDifficulty.getByTitle(dto.getPuzzleLevelDifficulty());
        PLStatus  plStatus =  PLStatus.getByTitle(dto.getPuzzleLevelStatus());
        PLPrivacy plPrivacy =  plPrivacyRepository.findByValue(dto.getPuzzleLevelPrivacy());
        Optional<PuzzleGroup>  puzzleGroupOptional = pgRepository.findById(dto.getPuzzleGroupId());
        Random random = new Random(); // Create a Random object
        long generatedLong = random.nextLong(); // Generate a random long
        String pcode= dto.getCode() + String.valueOf(generatedLong);

        PuzzleLevel importedPuzzleLevel = new PuzzleLevel(createdBy.get(),dto.getApproveDate(), dto.getOrdering(),
                dto.getTitle(),pcode,dto.getFromAge(),dto.getToAge(),
                dto.getMaxScore(), dto.getFirstStarScore(), dto.getSecondStarScore(), dto.getThirdStartScore(),
                puzzleGroupOptional.get(),plDifficulty,plStatus,plPrivacy, iconOptional.get(),picOptional.get() ,
                1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        puzzleLevelRepository.save(importedPuzzleLevel);

        // import puzzle level ground
        CameraSetupData cameraSetupImport = dto.getCameraSetup();
        PositionDTO position = cameraSetupImport.getPosition();
        PositionDTO rotation = cameraSetupImport.getRotation();
        FeaturesData features = cameraSetupImport.getFeatures();
        InitialValuesDTO initialValuesDTO = cameraSetupImport.getInitialValues();
        BoardCenterDTO boardCenterDTO = initialValuesDTO.getBoardCenter();
        BoardCenterDTO initialPanOffset = initialValuesDTO.getInitialPanOffset();
        //byte[] boardGraphic=boardGraphicOptional.get().getContent();
        Optional<BinaryContent> skyBox = binaryContentRepository.findById(initialValuesDTO.getSkyboxID());
        Optional<BinaryContent> background = binaryContentRepository.findById(initialValuesDTO.getBackgroundID());

        PLGround importPLGround = new PLGround(dto.getRows(), dto.getCols(),
                position.getX(), position.getY(), position.getZ(), rotation.getX(), rotation.getY(), rotation.getZ(),
                features.getZoom(), features.getPan(), features.getRotation(),importedPuzzleLevel, initialValuesDTO.getZoom(),initialValuesDTO.getZoomLimit(),
                boardCenterDTO.getX(),boardCenterDTO.getY(),boardCenterDTO.getZ(),initialValuesDTO.getPanLimit(),
                initialPanOffset.getX(),initialPanOffset.getY(),initialPanOffset.getZ(),skyBox.get(),background.get(),initialValuesDTO.getBackgroundScale()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        plGroundService.save(importPLGround);

        Collection<PLGround> plGrounds = new ArrayList<>();
        plGrounds.add(importPLGround);

        importedPuzzleLevel.setPlGrounds(plGrounds);
        //import puzzle level objectives
        Collection<PLObjectiveData> objectives = dto.getObjectives();
        Collection<PLObjective> importedObjectives = plObjectiveService.importObjectives(objectives, importedPuzzleLevel);
        importedPuzzleLevel.setPlObjectives(importedObjectives);

        //import puzzle level variables
        Collection<AttributeData> variables = dto.getVariables();
        Collection<Attribute> copiedAttributes = attributeService.importPLVariables(variables, importedPuzzleLevel, AttributeOwnerType.Puzzle_Level_Variable);

        //import puzzle level cells
        Collection<PLCellImport> cells = dto.getCells();
        Collection<PLCell> importedCells = instanceInPLService.importCells(cells, importedPuzzleLevel);
        importPLGround.setPlCells(importedCells);

        //import puzzle level instances
         Collection<Instance> importInstances = instanceInPLService.importObjects(dto.getObjects(), importedPuzzleLevel);

        //import puzzle level rules
        Collection<PLRuleImport> rules = dto.getRules();
        Collection<PLRule> importedRules = plRuleService.importRules(rules, importedPuzzleLevel);
        importedPuzzleLevel.setRules(importedRules);

        //import puzzle learning topics
        Collection<PLLearningTopicData> topics = dto.getLearningTopics();
        Collection<PLLearningTopic> importedTopics = plLearningTopicService.importLearningTopics(topics, importedPuzzleLevel);
        importedPuzzleLevel.setLearningTopics(importedTopics);

        return importedPuzzleLevel;
    }
    public PuzzleLevel importPuzzleLevel_New(PLImportDTO_New dto) throws IOException, ClassNotFoundException {
        // import puzzle level header
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<BinaryContent> iconOptional = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> picOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> boardGraphicOptional = binaryContentRepository.findById(dto.getBoardGraphicId());

        // Optional<PLGround> plGroundOptional = plGroundService.findById(238L);
        // BoardGraphicDTO boardGraphicDTO = PLDTOUtil.getBoardGraphicJSON(plGroundOptional.get());
        //  byte[] boardGraphic = ImageUtil.convertObjectToBytes(boardGraphicDTO);

        PLDifficulty plDifficulty =  PLDifficulty.getByTitle(dto.getPuzzleLevelDifficulty());
        PLStatus  plStatus =  PLStatus.getByTitle(dto.getPuzzleLevelStatus());
        PLPrivacy plPrivacy =  plPrivacyRepository.findByValue(dto.getPuzzleLevelPrivacy());
        Optional<PuzzleGroup>  puzzleGroupOptional = pgRepository.findById(dto.getPuzzleGroupId());
        Random random = new Random(); // Create a Random object
        long generatedLong = random.nextLong(); // Generate a random long
        String pcode= dto.getCode() + String.valueOf(generatedLong);

        PuzzleLevel importedPuzzleLevel = new PuzzleLevel(createdBy.get(),dto.getApproveDate(), dto.getOrdering(),
                dto.getTitle(),pcode,dto.getFromAge(),dto.getToAge(),
                dto.getMaxScore(), dto.getFirstStarScore(), dto.getSecondStarScore(), dto.getThirdStartScore(),
                puzzleGroupOptional.get(),plDifficulty,plStatus,plPrivacy, iconOptional.get(),picOptional.get() ,
                1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        puzzleLevelRepository.save(importedPuzzleLevel);

        // import puzzle level ground
        CameraSetupData cameraSetupImport = dto.getCameraSetup();
        PositionDTO position = cameraSetupImport.getPosition();
        PositionDTO rotation = cameraSetupImport.getRotation();
        FeaturesData features = cameraSetupImport.getFeatures();
        InitialValuesDTO initialValuesDTO = cameraSetupImport.getInitialValues();
        BoardCenterDTO boardCenterDTO = initialValuesDTO.getBoardCenter();
        BoardCenterDTO initialPanOffset = initialValuesDTO.getInitialPanOffset();
        //byte[] boardGraphic=boardGraphicOptional.get().getContent();
        Optional<BinaryContent> skyBox = binaryContentRepository.findById(initialValuesDTO.getSkyboxID());
        Optional<BinaryContent> background = binaryContentRepository.findById(initialValuesDTO.getBackgroundID());

        PLGround importPLGround = new PLGround(dto.getRows(), dto.getCols(),
                position.getX(), position.getY(), position.getZ(), rotation.getX(), rotation.getY(), rotation.getZ(),
                features.getZoom(), features.getPan(), features.getRotation(),importedPuzzleLevel, initialValuesDTO.getZoom(),initialValuesDTO.getZoomLimit(),
                boardCenterDTO.getX(),boardCenterDTO.getY(),boardCenterDTO.getZ(),initialValuesDTO.getPanLimit(),
                initialPanOffset.getX(),initialPanOffset.getY(),initialPanOffset.getZ(),skyBox.get(),background.get(),initialValuesDTO.getBackgroundScale()
                , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        plGroundService.save(importPLGround);

        Collection<PLGround> plGrounds = new ArrayList<>();
        plGrounds.add(importPLGround);

        importedPuzzleLevel.setPlGrounds(plGrounds);
        //import puzzle level objectives
        Collection<PLObjectiveData> objectives = dto.getObjectives();
        Collection<PLObjective> importedObjectives = plObjectiveService.importObjectives(objectives, importedPuzzleLevel);
        importedPuzzleLevel.setPlObjectives(importedObjectives);

        //import puzzle level variables
        Collection<AttributeData> variables = dto.getVariables();
        Collection<Attribute> copiedAttributes = attributeService.importPLVariables(variables, importedPuzzleLevel, AttributeOwnerType.Puzzle_Level_Variable);

        //import puzzle level cells
        Collection<PLCellImport> cells = dto.getCells();
        Collection<PLCell> importedCells = instanceInPLService.importCells(cells, importedPuzzleLevel);
        importPLGround.setPlCells(importedCells);

        //import puzzle level instances
        Collection<Instance> importInstances = instanceInPLService.importObjects_New(dto.getObjects(), importedPuzzleLevel);

        //import puzzle level rules
        Collection<PLRuleImport_New> rules = dto.getRules();
        Collection<PLRule> importedRules = plRuleService.importRules_New(rules, importedPuzzleLevel);
        importedPuzzleLevel.setRules(importedRules);

        //import puzzle learning topics
        Collection<PLLearningTopicData> topics = dto.getLearningTopics();
        Collection<PLLearningTopic> importedTopics = plLearningTopicService.importLearningTopics(topics, importedPuzzleLevel);
        importedPuzzleLevel.setLearningTopics(importedTopics);

        return importedPuzzleLevel;
    }


    @Transactional
    public void deletePuzzleLevel(PuzzleLevel puzzleLevel) {

        //delete puzzle learning topics
        Collection<PLLearningTopic> learningTopics = puzzleLevel.getLearningTopics();
        plLearningTopicService.deleteAll(learningTopics);

        //delete puzzle level rules
        Collection<PLRule> rules = puzzleLevel.getRules();

         plRuleService.deleteRules(rules);

        //delete puzzle level instances
        Collection<Instance> instances = puzzleLevel.getInstances();
        instanceInPLService.deleteInstances(instances);


        //delete puzzle level variables
        Collection<AttributeValue>  attributeValues= attributeValueService.findByOwnerId(puzzleLevel.getId());
        attributeValueService.deleteAll(attributeValues);
        Collection<Attribute> attributes = attributeService.findByOwnerId(puzzleLevel.getId());
        attributeService.deleteAll(attributes);

        //delete puzzle level objectives
        Collection<PLObjective> objectives = puzzleLevel.getPlObjectives();
        Iterator<PLObjective> iterator = objectives.iterator();
        while (iterator.hasNext()) {
            PLObjective objective = iterator.next();
           Collection<PLObjectiveTransaction> transactions = learningSkillTransactionService.findByPlObjective(objective);
           learningSkillTransactionService.deleteAll(transactions);
        }
        plObjectiveService.deleteAll(objectives);


        // this is ...........
        //delete puzzle cells
        PLGround plGround = puzzleLevel.getPlGrounds().iterator().next();
        plCellService.deleteAll(plGround.getPlCells());


        //delete puzzle level Grounds
        plGroundService.deleteAll(puzzleLevel.getPlGrounds());

        Collection<PLGameInstance> gameInstances = puzzleLevel.getPlGameInstances();
        gameInstanceService.deleteAll(gameInstances);

        //delete puzzle level
        puzzleLevelRepository.deleteById(puzzleLevel.getId());

    }


    public PuzzleLevel copy(PuzzleLevel puzzleLevel,PLCopyDTO dto) {
        // copy puzzle level header

        PuzzleLevel copyPuzzleLevel = new PuzzleLevel(puzzleLevel.getCreator(),puzzleLevel.getApproveDate(), puzzleLevel.getOrdering(),
               dto.getTitle(),dto.getCode(),dto.getFromAge(),dto.getToAge(),
               puzzleLevel.getMaxScore(), puzzleLevel.getFirstStarScore(), puzzleLevel.getSecondStarScore(), puzzleLevel.getThirdStartScore(),
               puzzleLevel.getPuzzleGroup(),puzzleLevel.getPuzzleDifficulty(),puzzleLevel.getPuzzleLevelStatus(),puzzleLevel.getPuzzleLevelPrivacy(),
               puzzleLevel.getPicture(),puzzleLevel.getIcon() ,
                1L, DateUtils.getNow(), DateUtils.getNow(), puzzleLevel.getCreatedBy(), puzzleLevel.getUpdatedBy());
        puzzleLevelRepository.save(copyPuzzleLevel);

        // copy puzzle level ground
        if(dto.getPLGround()) {
            PLGround plGround = puzzleLevel.getPlGrounds().iterator().next();
            PLGround copyPLGround = new PLGround(plGround.getNumRows(), plGround.getNumColumns(),
                    plGround.getxPosition(), plGround.getyPosition(), plGround.getzPosition(),
                    plGround.getxRotation(), plGround.getyRotation(), plGround.getzRotation(),
                    plGround.getZoom(), plGround.getPan(), plGround.getRotation(),
                    copyPuzzleLevel,
                    plGround.getInitValueZoom(), plGround.getInitValueZoomLimit(), plGround.getBoardCenterX(), plGround.getBoardCenterY(),
                    plGround.getBoardCenterZ(), plGround.getPanLimit(),
                    plGround.getInitPanOffsetX(), plGround.getInitPanOffsetY(), plGround.getInitPanOffsetZ(),
                    plGround.getSkybox(),plGround.getBackground(),plGround.getBackgroundScale(),
                    1L, DateUtils.getNow(), DateUtils.getNow(), plGround.getCreatedBy(), plGround.getUpdatedBy());
            plGroundService.save(copyPLGround);
        }

        //copy puzzle level objectives
        if(dto.getObjectives()) {
            Collection<PLObjective> objectives = puzzleLevel.getPlObjectives();
            Collection<PLObjective> copiedObjectives = plObjectiveService.copyObjectives(objectives, copyPuzzleLevel);
        }

        //copy puzzle level instances
        if(dto.getInstances()) {
            Collection<Instance> copiedInstances = instanceInPLService.copyInstancesFromSourcePLToTargetPL(puzzleLevel, copyPuzzleLevel);
        }

        //copy puzzle level variables
        if(dto.getVariables()) {
            Collection<Attribute> variables = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(puzzleLevel.getId(), AttributeOwnerType.Puzzle_Level_Variable);
            Collection<Attribute> copiedAttributes = attributeService.copyALLAttributesFromPLToPL(variables, puzzleLevel, copyPuzzleLevel, AttributeOwnerType.Puzzle_Level_Variable);
        }

        //copy puzzle level rules
        if(dto.getRules()) {
            Collection<PLRule> rules = puzzleLevel.getRules();
            Collection<PLRule> copiedRules = plRuleService.copyAll(rules, copyPuzzleLevel);
        }
        if(dto.getLearningTopics()) {
            Collection<PLLearningTopic> topics = puzzleLevel.getLearningTopics();
            Collection<PLLearningTopic> copyTopics = plLearningTopicService.copyAll(topics, copyPuzzleLevel);
        }
        return puzzleLevel;
    }

    public PuzzleLevel save(PLDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PuzzleLevel puzzleLevel=null;
        PLDifficulty plDifficulty =  PLDifficulty.getByTitle(dto.getPuzzleLevelDifficulty());
        PLStatus  plStatus =  PLStatus.getByTitle(dto.getPuzzleLevelStatus());
        PLPrivacy plPrivacy =  plPrivacyRepository.findByValue(dto.getPuzzleLevelPrivacy());
        Optional<BinaryContent> pictureOptional =  binaryContentService.findById(dto.getPicId());
        Optional<BinaryContent> iconOptional =  binaryContentService.findById(dto.getIconId());
        //Optional<BinaryContent> boardGraphicOptional = binaryContentRepository.findById(dto.getPicId());
        Random random = new Random(); // Create a Random object
        long generatedLong = random.nextLong(); // Generate a random long
        String pcode= dto.getCode() + String.valueOf(generatedLong);

        PuzzleGroup puzzleGroup = null;
        Optional<PuzzleGroup>  puzzleGroupOptional = pgRepository.findById(dto.getPuzzleGroupId());
        if(puzzleGroupOptional.isPresent())
                    puzzleGroup = puzzleGroupOptional.get();
        if (code.equalsIgnoreCase("Save")) { //Save
            puzzleLevel = new PuzzleLevel(createdBy.get(),dto.getApproveDate(), dto.getOrdering(), dto.getTitle(),pcode,dto.getFromAge(),dto.getToAge(),
                                dto.getMaxScore(), dto.getFirstStarScore(), dto.getSecondStarScore(), dto.getThirdStartScore(), puzzleGroup,plDifficulty,plStatus,plPrivacy,
                    pictureOptional.get(),iconOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            puzzleLevelRepository.save(puzzleLevel);
        }else{//edit
            Optional<PuzzleLevel> puzzleLevelOptional= puzzleLevelRepository.findById(dto.getId());
            if(puzzleLevelOptional.isPresent()) {
                puzzleLevel = puzzleLevelOptional.get();
                puzzleLevel.setApproveDate(dto.getApproveDate());
                puzzleLevel.setOrdering(dto.getOrdering());
                puzzleLevel.setCode(dto.getCode());
                puzzleLevel.setFromAge(dto.getFromAge());
                puzzleLevel.setToAge(dto.getToAge());
                puzzleLevel.setMaxScore(dto.getMaxScore());
                puzzleLevel.setFirstStarScore(dto.getFirstStarScore());
                puzzleLevel.setSecondStarScore(dto.getSecondStarScore());
                puzzleLevel.setThirdStartScore(dto.getThirdStartScore());

                puzzleLevel.setPuzzleDifficulty(plDifficulty);
                puzzleLevel.setPuzzleLevelStatus(plStatus);
                puzzleLevel.setPuzzleLevelPrivacy(plPrivacy);
                puzzleLevel.setPicture(pictureOptional.get());
                puzzleLevel.setIcon(iconOptional.get());

                puzzleLevel.setTitle(dto.getTitle());
                puzzleLevel.setVersion(puzzleLevel.getVersion()+1);
                puzzleLevel.setPuzzleGroup(puzzleGroup);
                puzzleLevelRepository.save(puzzleLevel);
            }
        }


        return puzzleLevel;
    }
    public Collection<PuzzleLevel> getPublicPuzzleLevelByAppMember(AppMember member) {
            PLPrivacy publicPL = plPrivacyRepository.findByValue("Public");
            Collection<PuzzleLevel> puzzleLevels = puzzleLevelRepository.findByPuzzleLevelPrivacy(publicPL);
            Collection<PuzzleLevel> filterdByAge = puzzleLevels.stream().filter(PuzzleLevel -> PuzzleLevel.getFromAge() <=member.getAge()  && member.getAge() <= PuzzleLevel.getToAge()).collect(Collectors.toList());
         return  filterdByAge;
    }
   public JourneyStep getPuzzleLevelMappedStep(Long id){
        JourneyStep journeyStep = null;
        PuzzleLevel puzzleLevel = new PuzzleLevel();
           Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelRepository.findById(id);
           if(puzzleLevelOptional.isPresent()) {
               puzzleLevel = puzzleLevelOptional.get();
               PuzzleGroup puzzleGroup = puzzleLevel.getPuzzleGroup();
               Collection<JourneyStep> journeyStepCollection = puzzleGroup.getJourneyStepCollection();
               Iterator<JourneyStep> itr = journeyStepCollection.iterator();
               if(itr.hasNext()) return itr.next();
           }
      return journeyStep;
    }
    }
