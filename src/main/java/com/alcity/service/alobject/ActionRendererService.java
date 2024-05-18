package com.alcity.service.alobject;

import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.repository.alobject.ActionRendererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ActionRendererService implements ActionRendererRepository {

    @Autowired
    ActionRendererRepository actionRendererRepository;

    @Override
    public <S extends Renderer> S save(S entity) {
        return actionRendererRepository.save(entity);
    }

    @Override
    public <S extends Renderer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Renderer> findById(Long id) {
        return
                actionRendererRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Renderer> findAll() {
        return actionRendererRepository.findAll();
    }

    @Override
    public Optional<Renderer> findByHandlerAndObjectAction(String handler, ObjectAction action) {
        return actionRendererRepository.findByHandlerAndObjectAction(handler,action);
    }

    @Override
    public Iterable<Renderer> findAllById(Iterable<Long> longs) {
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
    public void delete(Renderer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Renderer> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public Collection<Renderer> findByClientTypeId(Long id) {
        return null;
    }
}
