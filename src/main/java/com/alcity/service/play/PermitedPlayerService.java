package com.alcity.service.play;


import com.alcity.entity.play.PermitedPlayer;
import com.alcity.repository.play.PermitedPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PermitedPlayerService  implements PermitedPlayerRepository {

    @Autowired
    PermitedPlayerRepository permitedPlayerRepository;
    @Override
    public <S extends PermitedPlayer> S save(S entity) {
        return permitedPlayerRepository.save(entity);
    }

    @Override
    public <S extends PermitedPlayer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PermitedPlayer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PermitedPlayer> findAll() {
        return null;
    }

    @Override
    public Iterable<PermitedPlayer> findAllById(Iterable<Long> longs) {
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
    public void delete(PermitedPlayer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PermitedPlayer> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
