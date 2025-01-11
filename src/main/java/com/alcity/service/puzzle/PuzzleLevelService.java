package com.alcity.service.puzzle;

import com.alcity.dto.alobject.AttributeValueDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PuzzleLevelStepMappingDTO;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.PLPrivacyRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PuzzleLevelService implements PuzzleLevelRepository {


    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;

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
       // getJourneyStepsMatchWithPuzzleLvels(newpuzzleLevels);
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
    @Override
    public Collection<PuzzleLevel> findByTitle(String title) {
        return puzzleLevelRepository.findByTitle(title);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }




    @Override
    public PuzzleLevel findByCode(String code) {
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

    public PuzzleLevel save(PLDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        PuzzleLevel puzzleLevel=null;
        PLDifficulty plDifficulty =  PLDifficulty.getByTitle(dto.getPuzzleLevelDifficulty());
        PLStatus  plStatus =  PLStatus.getByTitle(dto.getPuzzleLevelStatus());
        PLPrivacy plPrivacy =  plPrivacyRepository.findByValue(dto.getPuzzleLevelPrivacy());
        PuzzleGroup puzzleGroup = null;
        Optional<PuzzleGroup>  puzzleGroupOptional = pgRepository.findById(dto.getPuzzleGroupId());
        if(puzzleGroupOptional.isPresent())
                    puzzleGroup = puzzleGroupOptional.get();

        if (code.equalsIgnoreCase("Save")) { //Save
            puzzleLevel = new PuzzleLevel(dto.getApproveDate(), dto.getOrdering(), dto.getTitle(),dto.getCode(),dto.getFromAge(),dto.getToAge(),
                                dto.getMaxScore(), dto.getFirstMaxScore(), dto.getSecondMaxScore(), dto.getThirdMaxScore(), puzzleGroup,plDifficulty,plStatus,plPrivacy
                                    , 1L, "1714379790", "1714379790", createdBy, createdBy);
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
                puzzleLevel.setPuzzleDifficulty(plDifficulty);
                puzzleLevel.setPuzzleLevelStatus(plStatus);
                puzzleLevel.setPuzzleLevelPrivacy(plPrivacy);
                puzzleLevel.setTitle(dto.getTitle());
                puzzleLevel.setVersion(puzzleLevel.getVersion()+1);
                puzzleLevel.setPuzzleGroup(puzzleGroup);
                puzzleLevelRepository.save(puzzleLevel);
            }
        }


        return puzzleLevel;
    }

    }
