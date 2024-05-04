package com.alcity.service.learning;

import com.alcity.customexception.RecordNotFoundException;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.repository.learning.LearningSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningSkillService implements LearningSkillRepository {

    @Autowired
    LearningSkillRepository learningSkillRepository;
    @Override
    public <S extends LearningSkill> S save(S entity) {
        return learningSkillRepository.save(entity);
    }

    @Override
    public <S extends LearningSkill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkill> findById(Long id) {
        return learningSkillRepository.findById(id);
                   }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningSkill> findAll() {
        return learningSkillRepository.findAll();
    }

    @Override
    public Collection<LearningSkill> findByValueContains(String criteria) {
        return learningSkillRepository.findByValueContains(criteria);
    }

    @Override
    public Iterable<LearningSkill> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningSkill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkill> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LearningSkill findByLabel(String label) {

        return learningSkillRepository.findByLabel(label);
    }

    @Override
    public LearningSkill findByValue(String value) {
        return learningSkillRepository.findByValue(value);
    }
}
