package com.alcity.service.appmember;


import com.alcity.entity.appmember.LearningSkillTransaction;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.repository.appmember.LearningSkillTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningSkillTransactionService implements LearningSkillTransactionRepository {
    @Override
    public <S extends LearningSkillTransaction> S save(S entity) {
        return null;
    }

    @Override
    public <S extends LearningSkillTransaction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkillTransaction> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Collection<LearningSkillTransaction> findAll() {
        return null;
    }

    @Override
    public LearningSkillTransaction findByTransactionDate(String label) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }



    @Override
    public Collection<LearningSkillTransaction> findByAmount(Float amount) {
        return null;
    }

    @Override
    public Iterable<LearningSkillTransaction> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningSkillTransaction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkillTransaction> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
