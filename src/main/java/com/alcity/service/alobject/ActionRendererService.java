package com.alcity.service.alobject;

import com.alcity.entity.alobject.ActionRenderer;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.repository.alobject.ActionRendererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ActionRendererService implements ActionRendererRepository {

    @Autowired
    ActionRendererRepository actionRendererRepository;

    @Override
    public <S extends ActionRenderer> S save(S entity) {
        return actionRendererRepository.save(entity);
    }

    @Override
    public <S extends ActionRenderer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ActionRenderer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ActionRenderer> findAll() {
        return null;
    }

    @Override
    public Iterable<ActionRenderer> findAllById(Iterable<Long> longs) {
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
    public void delete(ActionRenderer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ActionRenderer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ActionRenderer> findByHandler(String handler) {
        return null;
    }

    @Override
    public Collection<ActionRenderer> findByClientTypeId(Long id) {
        return null;
    }
}
