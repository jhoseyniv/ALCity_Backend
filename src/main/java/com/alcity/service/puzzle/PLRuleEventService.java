package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLRuleEventDTO;
import com.alcity.dto.puzzle.PLRulePostActionDTO;
import com.alcity.entity.alenum.PLRuleEventType;
import com.alcity.entity.alenum.PLRulePostActionOwnerType;
import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLRuleEventRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLRuleEventService implements PLRuleEventRepository {

    @Autowired
    @Qualifier("PLRuleEventRepository")
    PLRuleEventRepository plRuleEventRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;

    @Override
    public <S extends PLRuleEvent> S save(S entity) {
        return plRuleEventRepository.save(entity);
    }
    public PLRuleEvent save(PLRuleEventDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLRuleEvent event=null;
        PLRuleEventType eventType = PLRuleEventType.getByTitle(dto.getPlRuleEventType());

        if(eventType ==null ) return  null;

        if (code.equalsIgnoreCase("Save")) { //Save
            event = new PLRuleEvent(dto.getName(),eventType, eventType.ordinal(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            plRuleEventRepository.save(event);
        }else{//edit
            Optional<PLRuleEvent> eventOptional= plRuleEventRepository.findById(dto.getId());
            if(eventOptional.isPresent()) {
                event = eventOptional.get();
                event.setName(dto.getName());
                event.setPlRuleEventType(eventType);
                event.setEventId(eventType.ordinal());
                event.setCreated(DateUtils.getNow());
                event.setUpdated(DateUtils.getNow());
                event.setCreatedBy(createdBy.get());
                event.setUpdatedBy(createdBy.get());
                event.setVersion(event.getVersion()+1);
                plRuleEventRepository.save(event);
            }
        }
        return event;
    }

    @Override
    public <S extends PLRuleEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRuleEvent> findById(Long id) {
        return
                plRuleEventRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLRuleEvent> findAll() {
        return plRuleEventRepository.findAll();
    }

    @Override
    public Iterable<PLRuleEvent> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        plRuleEventRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLRuleEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRuleEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<PLRuleEvent> findByName(String name) {
        return plRuleEventRepository.findByName(name);
    }

}
