package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PLObjective;
import com.alcity.repository.puzzle.PLObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PLObjectiveService implements PLObjectiveRepository {

    @Autowired
    @Qualifier("PLObjectiveRepository")
    PLObjectiveRepository objectiveRepository;

    @Override
    public <S extends PLObjective> S save(S entity) {
        return objectiveRepository.save(entity);
    }

    @Override
    public <S extends PLObjective> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLObjective> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLObjective> findAll() {
        return null;
    }

    @Override
    public Collection<PLObjective> findPuzzleLevelObjectiveByPuzzleLevelId(Long plId) {
        return objectiveRepository.findPuzzleLevelObjectiveByPuzzleLevelId(plId);
    }



    @Override
    public Iterable<PLObjective> findAllById(Iterable<Long> longs) {
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
    public void delete(PLObjective entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLObjective> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLObjective> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PLObjective> findByCondition(StringBuffer condition) {
        return null;
    }
}
