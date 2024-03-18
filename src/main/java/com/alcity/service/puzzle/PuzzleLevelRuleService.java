package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PuzzleLevelRule;
import com.alcity.repository.puzzle.PuzzleLevelRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelRuleService  implements PuzzleLevelRuleRepository {

    @Autowired
    PuzzleLevelRuleRepository puzzleLevelRuleRepository;

    @Override
    public <S extends PuzzleLevelRule> S save(S entity) {
        return puzzleLevelRuleRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelRule> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelRule> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelRule> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelRule> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelRule entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelRule> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleLevelRule> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PuzzleLevelRule> findByCondition(String condition) {
        return null;
    }
}
