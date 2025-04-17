package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.dto.puzzle.PLRulePostActionDTO;
import com.alcity.entity.alobject.PLRulePostActionType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.alobject.PLRulePostActionTypeRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLRulePostActionRepository;
import com.alcity.repository.puzzle.PLRuleRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLRulePostActionService implements PLRulePostActionRepository {

    @Autowired
    @Qualifier("PLRulePostActionRepository")

    PLRulePostActionRepository plRulePostActionRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    @Qualifier("PLRuleRepository")
    PLRuleRepository ruleRepository;

    @Qualifier("PLRulePostActionTypeRepository")
    @Autowired
    PLRulePostActionTypeRepository plRulePostActionTypeRepository;

    @Override
    public <S extends PLRulePostAction> S save(S entity) {
        return plRulePostActionRepository.save(entity);
    }
    public PLRulePostAction save(PLRulePostActionDTO dto, String code) {
       Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
       PLRulePostAction postAction=null;
       PuzzleLevel puzzleLevel = null;
        Optional<PLRule>  plRuleOptional = ruleRepository.findById(dto.getPuzzleLevelRuleId());
        Optional<PLRulePostActionType>  plRulePostActionTypeOptional = plRulePostActionTypeRepository.findByValue(dto.getPlRulePostActionType());

        if(plRuleOptional.isEmpty() || plRulePostActionTypeOptional.isEmpty()) return  null;

        if (code.equalsIgnoreCase("Save")) { //Save
            postAction = new PLRulePostAction(plRuleOptional.get(), plRulePostActionTypeOptional.get(), dto.getOrdering(), dto.getActionName(), dto.getObjectId(), dto.getVariable(),
                                    dto.getValueExperssion(),dto.getAlertType(),dto.getAlertMessage(),1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            plRulePostActionRepository.save(postAction);
        }else{//edit
            Optional<PLRulePostAction> plRulePostActionOptional= plRulePostActionRepository.findById(dto.getId());
            if(plRulePostActionOptional.isPresent()) {
                postAction = plRulePostActionOptional.get();
                postAction.setActionName(dto.getActionName());
                postAction.setPlRulePostActionType(plRulePostActionTypeOptional.get());
                postAction.setAlertMessage(dto.getAlertMessage());
                postAction.setOrdering(dto.getOrdering());
                postAction.setPuzzleLevelRule(plRuleOptional.get());
                postAction.setObjectId(dto.getObjectId());
                postAction.setValueExperssion(dto.getValueExperssion());
                postAction.setVariable(dto.getVariable());
                postAction.setCreated(DateUtils.getNow());
                postAction.setUpdated(DateUtils.getNow());
                postAction.setCreatedBy(createdBy.get());
                postAction.setUpdatedBy(createdBy.get());
                postAction.setVersion(puzzleLevel.getVersion()+1);
                plRulePostActionRepository.save(postAction);
            }
        }
        return postAction;
    }

    @Override
    public <S extends PLRulePostAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRulePostAction> findById(Long id) {
        return plRulePostActionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLRulePostAction> findAll() {
        return null;
    }

    @Override
    public Iterable<PLRulePostAction> findAllById(Iterable<Long> longs) {
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
    public void delete(PLRulePostAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRulePostAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLRulePostAction> findByOrdering(Integer ordering) {
        return null;
    }

 }
