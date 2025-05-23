package com.alcity.service.puzzle;

import com.alcity.dto.importjson.PLDataImport;
import com.alcity.dto.puzzle.PLCopyDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PuzzleLevelStepMappingDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.PLPrivacyRepository;
import com.alcity.repository.journey.JourneyStepRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PuzzleLevelService implements PuzzleLevelRepository {


    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;
    @Autowired
    JourneyStepRepository journeyStepRepository;

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
    private AttributeService attributeService;
    @Autowired
    private PLObjectiveService plObjectiveService;
    @Autowired
    private PLRuleService plRuleService;
    @Autowired
    PLLearningTopicService plLearningTopicService;

    @Autowired
    @Lazy
    private InstanceInPLService instanceInPLService;

    public PuzzleLevel getPuzzleLevelFromJson(PLDataImport plData) {
        Optional<AppMember> creatorOptional = appMemberService.findByUsername("admin");
       PuzzleLevel puzzleLevel = new PuzzleLevel();

//        AppMember creator,String approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore,Float firstStarScore,Float secondStarScore,Float thirdStartScore,
//                PuzzleGroup puzzleGroup, PLDifficulty puzzleDifficulty, PLStatus puzzleLevelStatus, PLPrivacy puzzleLevelPrivacy,BinaryContent picture ,BinaryContent icon,
//                Long version, String created, String updated, AppMember createdBy, AppMember updatedBy

        return puzzleLevel;
    }


    public void saveJsonInDatabase(PLDataImport plData) {
        PuzzleLevel puzzleLevel = getPuzzleLevelFromJson(plData);
    }


    public PuzzleLevel copy(PuzzleLevel puzzleLevel,PLCopyDTO dto) {
        // copy puzzle level header

        PuzzleLevel copyPuzzleLevel = new PuzzleLevel(puzzleLevel.getCreator(),puzzleLevel.getApproveDate(), puzzleLevel.getOrdering(),
               dto.getTitle(),dto.getCode(),dto.getFromAge(),dto.getToAge(),
               puzzleLevel.getMaxScore(), puzzleLevel.getFirstStarScore(), puzzleLevel.getSecondStarScore(), puzzleLevel.getThirdStartScore(),
               puzzleLevel.getPuzzleGroup(),puzzleLevel.getPuzzleDifficulty(),puzzleLevel.getPuzzleLevelStatus(),puzzleLevel.getPuzzleLevelPrivacy(),
               puzzleLevel.getPicture(),puzzleLevel.getIcon() , 1L, DateUtils.getNow(), DateUtils.getNow(), puzzleLevel.getCreatedBy(), puzzleLevel.getUpdatedBy());
        puzzleLevelRepository.save(copyPuzzleLevel);

        // copy puzzle level ground
        if(dto.getPLGround()) {
            PLGround plGround = puzzleLevel.getPlGrounds().iterator().next();
            PLGround copyPLGround = new PLGround(plGround.getNumRows(), plGround.getNumColumns(),
                    plGround.getxPosition(), plGround.getyPosition(), plGround.getzPosition(),
                    plGround.getxRotation(), plGround.getyRotation(), plGround.getzRotation(),
                    plGround.getZoom(), plGround.getPan(), plGround.getRotation(),
                    copyPuzzleLevel, plGround.getBoardGraphic()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), plGround.getCreatedBy(), plGround.getUpdatedBy());
            plGroundService.save(copyPLGround);
        }
        //copy puzzle level objectives
        if(dto.getObjectives()) {
            Collection<PLObjective> objectives = puzzleLevel.getPlObjectives();
            Collection<PLObjective> copiedObjectives = plObjectiveService.copyObjectives(objectives, copyPuzzleLevel);
        }

        //copy puzzle level instances
        if(dto.getInstances()) {
            Collection<ALCityInstanceInPL> copiedInstances = instanceInPLService.copyInstancesFromSourcePLToTargetPL(puzzleLevel, copyPuzzleLevel);
        }

        //copy puzzle level variables
        if(dto.getVariables()) {
            Collection<Attribute> variables = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(puzzleLevel.getId(), AttributeOwnerType.Puzzle_Level_Variable);
            Collection<Attribute> copiedAttributes = attributeService.copyALLAttributesFromPLToPL(variables, puzzleLevel, copyPuzzleLevel, AttributeOwnerType.Puzzle_Level_Variable);
        }

        //copy puzzle level rules
        if(dto.getRules()) {
            Collection<PLRule> rules = puzzleLevel.getPuzzleLevelRuleCollection();
            Collection<PLRule> copiedRules = plRuleService.copyAll(rules, copyPuzzleLevel);
        }
        if(dto.getLearningTopics()) {
            Collection<LearningTopicInPL> topics = puzzleLevel.getLearningTopicInPLCollection();
            Collection<LearningTopicInPL> copyTopics = plLearningTopicService.copyAll(topics, copyPuzzleLevel);
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
        PuzzleGroup puzzleGroup = null;
        Optional<PuzzleGroup>  puzzleGroupOptional = pgRepository.findById(dto.getPuzzleGroupId());
        if(puzzleGroupOptional.isPresent())
                    puzzleGroup = puzzleGroupOptional.get();
        if (code.equalsIgnoreCase("Save")) { //Save
            puzzleLevel = new PuzzleLevel(createdBy.get(),dto.getApproveDate(), dto.getOrdering(), dto.getTitle(),dto.getCode(),dto.getFromAge(),dto.getToAge(),
                                dto.getMaxScore(), dto.getFirstStarScore(), dto.getSecondStarScore(), dto.getThirdStartScore(), puzzleGroup,plDifficulty,plStatus,plPrivacy,
                    pictureOptional.get(),iconOptional.get() , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
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
