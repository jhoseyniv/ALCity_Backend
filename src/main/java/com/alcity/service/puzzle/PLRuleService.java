package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PLRule;
import com.alcity.repository.puzzle.PLRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLRuleService implements PLRuleRepository {

    @Autowired
    @Qualifier("PLRuleRepository")
    PLRuleRepository ruleRepository;

    @Override
    public <S extends PLRule> S save(S entity) {
        return ruleRepository.save(entity);
    }

    @Override
    public <S extends PLRule> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRule> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLRule> findAll() {
        return null;
    }

    @Override
    public Iterable<PLRule> findAllById(Iterable<Long> longs) {
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
    public void delete(PLRule entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRule> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLRule> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PLRule> findByCondition(String condition) {
        return null;
    }
}
