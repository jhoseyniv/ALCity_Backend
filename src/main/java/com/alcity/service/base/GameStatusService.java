package com.alcity.service.base;


import com.alcity.entity.base.GameStatus;
import com.alcity.repository.base.GameStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class GameStatusService implements GameStatusRepository {


    @Autowired
    GameStatusRepository gameStatusRepository;


    @Override
    public <S extends GameStatus> S save(S entity) {
        return gameStatusRepository.save(entity);
    }

    @Override
    public <S extends GameStatus> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<GameStatus> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<GameStatus> findAll() {
        return null;
    }

    @Override
    public Iterable<GameStatus> findAllById(Iterable<Long> longs) {
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
    public void delete(GameStatus entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends GameStatus> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public GameStatus findByLabel(String label) {
        return null;
    }

    @Override
    public GameStatus findByValue(String value) {
        return null;
    }
}
