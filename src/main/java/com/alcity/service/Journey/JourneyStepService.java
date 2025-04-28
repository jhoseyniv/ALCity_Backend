package com.alcity.service.Journey;

import com.alcity.dto.journey.JourneyStepRecord;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.journey.JourneyRepository;
import com.alcity.repository.journey.JourneyStepRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class JourneyStepService implements JourneyStepRepository {

    @Autowired
    JourneyStepRepository journeyStepRepository;
    @Autowired
    PGRepository PGRepository;
    @Override
    public <S extends JourneyStep> S save(S entity) {
        return journeyStepRepository.save(entity);
    }

    @Override
    public <S extends JourneyStep> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<JourneyStep> findById(Long id) {
        return journeyStepRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<JourneyStep> findAll() {
        return null;
    }

    @Override
    public Iterable<JourneyStep> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        journeyStepRepository.deleteById(aLong);
    }
    @Autowired
    AppMemberRepository appMemberRepository;
    @Autowired
    JourneyRepository journeyRepository;

    public JourneyStep save(JourneyStepRecord dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        JourneyStep journeyStep=null;
        Optional<JourneyStep> journeyStepOptional= journeyStepRepository.findByTitle(dto.getTitle());
        Optional<PuzzleGroup> puzzleGroup =PGRepository.findById(dto.getPuzzleGroupId());
        Optional<Journey>  journey = journeyRepository.findById(dto.getJourneyId());

        if (code.equalsIgnoreCase("Save")) { //Save
            journeyStep = new JourneyStep(dto.getTitle() ,dto.getOrdering(),dto.getXpos(),dto.getYpos(),journey.get(),puzzleGroup.get()
                    , 1L,DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            journeyStepRepository.save(journeyStep);
        }else{//edit
            journeyStepOptional= journeyStepRepository.findById(dto.getId());
            if(journeyStepOptional.isPresent()) {
                journeyStep = journeyStepOptional.get();
                journeyStep.setTitle(dto.getTitle());
                journeyStep.setOrdering(dto.getOrdering());
                journeyStep.setXpos(dto.getXpos());
                journeyStep.setYpos(dto.getYpos());
                journeyStep.setPuzzleGroup(puzzleGroup.get());
                journeyStep.setVersion(journeyStep.getVersion()+1);
                journeyStep.setUpdated(DateUtils.getNow());
                journeyStep.setUpdatedBy(createdBy.get());
                journeyStepRepository.save(journeyStep);
            }
        }
        return journeyStep;
    }

    @Override
    public void delete(JourneyStep entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends JourneyStep> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<JourneyStep> findByXpos(Integer xpos) {
        return null;
    }

    @Override
    public Collection<JourneyStep> findByYpos(Integer ypos) {
        return null;
    }

    @Override
    public Collection<JourneyStep> findByJourney(Journey journey) {
        return journeyStepRepository.findByJourney(journey);
    }

    @Override
    public Optional<JourneyStep> findByTitle(String title) {
        return journeyStepRepository.findByTitle(title);
    }



}
