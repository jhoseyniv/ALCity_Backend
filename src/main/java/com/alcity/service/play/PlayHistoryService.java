package com.alcity.service.play;


import com.alcity.entity.play.PlayHistory;
import com.alcity.repository.play.PlayHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PlayHistoryService implements PlayHistoryRepository {

    @Autowired
    PlayHistoryRepository playHistoryRepository;

    @Override
    public <S extends PlayHistory> S save(S entity) {
        return playHistoryRepository.save(entity);
    }

    @Override
    public <S extends PlayHistory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PlayHistory> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PlayHistory> findAll() {
        return null;
    }

    @Override
    public Iterable<PlayHistory> findAllById(Iterable<Long> longs) {
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
    public void delete(PlayHistory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PlayHistory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PlayHistory> findByplayScore(Float score) {
        return null;
    }
}
