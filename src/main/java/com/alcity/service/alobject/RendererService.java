package com.alcity.service.alobject;

import com.alcity.dto.alobject.RendererDTO;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.alobject.RendererRepository;
import com.alcity.repository.base.ClientTypeRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class RendererService implements RendererRepository {

    @Autowired
    RendererRepository rendererRepository;

    @Override
    public <S extends Renderer> S save(S entity) {
        return rendererRepository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private ClientTypeRepository clientTypeRepository;

    public Renderer save(RendererDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Renderer renderer=null;
        Optional<Renderer>  rendererOptional = rendererRepository.findById(dto.getId());
        ClientType clientType =  clientTypeRepository.findByValue(dto.getClientType());
        ObjectActionType objectAction = ObjectActionType.getByTitle(dto.getObjectAction());

        if (code.equalsIgnoreCase("Save")) { //Save
            renderer = new Renderer(dto.getHandler(), clientType, objectAction
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            rendererRepository.save(renderer);
        }else{//edit
            rendererOptional = rendererRepository.findById(dto.getId());
            if(rendererOptional.isPresent()) {
                renderer = rendererOptional.get();
                renderer.setHandler(dto.getHandler());
                renderer.setObjectAction(objectAction);
                renderer.setClientType(clientType);
                renderer.setVersion(renderer.getVersion()+1);
                renderer.setUpdated(createdBy.get().getUsername());
                renderer.setUpdated(DateUtils.getNow());
                rendererRepository.save(renderer);
            }
        }
        return renderer;
    }

    @Override
    public <S extends Renderer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Renderer> findById(Long id) {
        if(id == null) return Optional.empty();
        return rendererRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Renderer> findAll() {
        return rendererRepository.findAll();
    }

    @Override
    public Optional<Renderer> findByHandlerAndObjectAction(String handler, ObjectActionType action) {
        return rendererRepository.findByHandlerAndObjectAction(handler,action);
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
        rendererRepository.deleteById(aLong);
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

    @Override
    public Optional<Renderer> findByHandler(String handler) {
        return rendererRepository.findByHandler(handler);
    }
}
