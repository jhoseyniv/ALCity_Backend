package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLRuleEventRepository;
import com.alcity.repository.puzzle.PLRuleRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class PLRuleService implements PLRuleRepository {

    @Autowired
    @Qualifier("PLRuleRepository")
    PLRuleRepository ruleRepository;
    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Override
    public <S extends PLRule> S save(S entity) {
        return ruleRepository.save(entity);
    }

    @Override
    public <S extends PLRule> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRule> findById(Long id) {
        return ruleRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLRule> findAll() {
        return null;
    }

    @Override
    public Iterable<PLRule> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        ruleRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLRule entity) {

    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;
    @Autowired
    PLRuleEventRepository PLRuleEventRepository;

    public Collection<PLRule> copyAll(Collection<PLRule> rules,PuzzleLevel puzzleLevel) {
        Collection<PLRule> copiedRules = new ArrayList<>();
        Iterator<PLRule> iterator = rules.iterator();
        while(iterator.hasNext()){
            PLRule rule = iterator.next();
            PLRule copyRule = copy(rule,puzzleLevel);
            copiedRules.add(copyRule);
        }

        return copiedRules;
    }

    public PLRule copy(PLRule rule,PuzzleLevel puzzleLevel) {
        Collection<PLRulePostAction> postActions = DTOUtil.getPlRulePostActions(plRulePostActionService,rule.getId());

        PLRule newRule = new PLRule("Copy Of Rule "+rule.getTitle(),rule.getOrdering()+1,
                rule.getCondition(),rule.getIgnoreRemaining(),puzzleLevel,rule.getPlRuleEvent(),rule.getSubEvent(),
                rule.getVersion(),rule.getCreated(),rule.getUpdated(),rule.getCreatedBy(),rule.getUpdatedBy());
        ruleRepository.save(newRule);
        plRulePostActionService.copyAll(postActions,newRule.getId());
        return newRule;
    }

    public PLRule save(PLRuleDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLRule plRule=null;
        PuzzleLevel puzzleLevel = null;
        Optional<PuzzleLevel>  puzzleLevelOptional = puzzleLevelRepository.findById(dto.getPuzzleLevelId());
        if(puzzleLevelOptional.isPresent())
            puzzleLevel = puzzleLevelOptional.get();

        PLRuleEvent plRuleEvent = null;
        Optional<PLRuleEvent>  plRuleEventOptional = PLRuleEventRepository.findById(dto.getPLRuleEventId());
        if(plRuleEventOptional.isPresent())
            plRuleEvent = plRuleEventOptional.get();
        StringBuffer condition = new StringBuffer(dto.getCondition());

        if (code.equalsIgnoreCase("Save")) { //Save
            plRule = new PLRule(dto.getTitle(), dto.getOrdering(), condition,dto.getIgnoreRemaining(),puzzleLevel,
                    plRuleEvent,dto.getPLRuleSubEventName(),1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            ruleRepository.save(plRule);
        }else{//edit
            Optional<PLRule> plRuleOptional= ruleRepository.findById(dto.getId());
            if(plRuleOptional.isPresent()) {
                plRule = plRuleOptional.get();
                plRule.setTitle(dto.getTitle());
                plRule.setCondition(condition);
                plRule.setPlRuleEvent(plRuleEvent);
                plRule.setSubEvent(dto.getPLRuleSubEventName());
                plRule.setIgnoreRemaining(dto.getIgnoreRemaining());
                plRule.setOrdering(dto.getOrdering());
                plRule.setPuzzleLevel(puzzleLevel);
                plRule.setCreated(DateUtils.getNow());
                plRule.setUpdated(DateUtils.getNow());
                plRule.setCreatedBy(createdBy.get());
                plRule.setUpdatedBy(createdBy.get());
                plRule.setVersion(puzzleLevel.getVersion()+1);
                ruleRepository.save(plRule);
            }
        }
        return plRule;
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRule> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLRule> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PLRule> findByCondition(StringBuffer condition) {
        return null;
    }
}
