package com.alcity.service.Journey;

import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyLearningSkill;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.repository.journey.JourneyLearningSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class JourneyLearningSkillService implements JourneyLearningSkillRepository {

    @Autowired
    JourneyLearningSkillRepository journeyLearningSkillRepository;


    @Override
    public <S extends JourneyLearningSkill> S save(S entity) {
        return journeyLearningSkillRepository.save(entity);
    }

    @Override
    public <S extends JourneyLearningSkill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<JourneyLearningSkill> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<JourneyLearningSkill> findAll() {
        return null;
    }

    @Override
    public Iterable<JourneyLearningSkill> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(JourneyLearningSkill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends JourneyLearningSkill> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<JourneyLearningSkill> findByRequiredAmount(Float amount) {
        return null;
    }

    @Override
    public Optional<JourneyLearningSkill> findByJourneyAndLearningSkill(Journey journey, LearningSkill learningSkill) {
        return journeyLearningSkillRepository.findByJourneyAndLearningSkill(journey,learningSkill);
    }
}
