package com.alcity.service.play;


import com.alcity.entity.play.PermittedPlayer;
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
    public <S extends PermittedPlayer> S save(S entity) {
        return permitedPlayerRepository.save(entity);
    }

    @Override
    public <S extends PermittedPlayer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PermittedPlayer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PermittedPlayer> findAll() {
        return null;
    }

    @Override
    public Iterable<PermittedPlayer> findAllById(Iterable<Long> longs) {
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
    public void delete(PermittedPlayer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PermittedPlayer> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
