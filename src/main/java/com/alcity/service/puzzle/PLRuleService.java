package com.alcity.service.puzzle;


import com.alcity.dto.plimpexport.rulemport.PLRuleImport;
import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;
import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLRuleRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.test.ruleimport_new.PLRuleImport_New;
import com.alcity.test.ruleimport_new.PostActionTreeImport_New;
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

//    public void deletePlRule( PLRule rule) {
//        Collection<PLRulePostAction>  postActions = plRulePostActionService.findByOwnerId(rule.getId()) ;
//        Iterator<PLRulePostAction> iterator = postActions.iterator();
//        while(iterator.hasNext()) {
//            PLRulePostAction postAction = iterator.next();
//            plRulePostActionService.deletePostActionWithChilds(postAction);
//        }
//        ruleRepository.delete(rule);
//    }
//    public void deleteAllPlRules( Collection<PLRule> rules) {
//        Iterator<PLRule> iterator = rules.iterator();
//        while (iterator.hasNext()){
//            deletePlRule(iterator.next());
//        }
//
//    }
    public void deleteRule(PLRule rule) {
        if(rule.getTitle().equalsIgnoreCase("show first filled cell")){
            System.out.println("this is here");
        }
        Collection<PLRulePostAction> postActions = plRulePostActionService.findByOwnerId(rule.getId());
        Iterator<PLRulePostAction> iterator = postActions.iterator();
        while(iterator.hasNext()){
            plRulePostActionService.deleteActionTree(iterator.next());
        }
        ruleRepository.delete(rule);

    }


    public void deleteRules(Collection<PLRule> rules) {
        Iterator<PLRule> iterator = rules.iterator();
        while(iterator.hasNext()){
            PLRule rule = iterator.next();
             deleteRule(rule);
        }
    }




    @Override
    public void delete(PLRule entity) {

    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;
    @Autowired
    PLRuleEventService plRuleEventService;


    public PLRule importRule(PLRuleImport importRule,PuzzleLevel puzzleLevel) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");

        Collection<PostActionTreeImport> postActionTreeImports = importRule.getActionTreesImport();
        Optional<PLRuleEvent> plRuleEvent = plRuleEventService.findByName(importRule.getEvent());
        PLRule newRule = new PLRule(importRule.getTitle(),importRule.getOrdering(),
                importRule.getCondition(),importRule.getIgnoreRemaining(),puzzleLevel,plRuleEvent.get(),importRule.getSubEvent(),
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
        ruleRepository.save(newRule);
        plRulePostActionService.importPLRulePostActionsTrees(postActionTreeImports,newRule.getId());
        return newRule;
    }
    public PLRule importRule_New(PLRuleImport_New importRule,PuzzleLevel puzzleLevel) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");

        Collection<PostActionTreeImport_New> postActionTreeImports = importRule.getActions();
        Optional<PLRuleEvent> plRuleEvent = plRuleEventService.findByName(importRule.getEvent());
        PLRule newRule = new PLRule(importRule.getTitle(),importRule.getOrdering(),
                importRule.getCondition(),importRule.getIgnoreRemaining(),puzzleLevel,plRuleEvent.get(),importRule.getSubEvent(),
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
        ruleRepository.save(newRule);
        plRulePostActionService.importPLRulePostActionsTrees_New(postActionTreeImports,newRule.getId());
        return newRule;
    }

    public Collection<PLRule> importRules_New(Collection<PLRuleImport_New> plRuleImports, PuzzleLevel puzzleLevel) {
        Collection<PLRule> importedRules = new ArrayList<>();
        Iterator<PLRuleImport_New> iterator = plRuleImports.iterator();
        while(iterator.hasNext()){
            PLRuleImport_New plRuleImport = iterator.next();
            PLRule importedRule = importRule_New(plRuleImport,puzzleLevel);
            importedRules.add(importedRule);
        }
        return importedRules;
    }

    public Collection<PLRule> importRules(Collection<PLRuleImport> plRuleImports, PuzzleLevel puzzleLevel) {
        Collection<PLRule> importedRules = new ArrayList<>();
        Iterator<PLRuleImport> iterator = plRuleImports.iterator();
        while(iterator.hasNext()){
            PLRuleImport plRuleImport = iterator.next();
            PLRule importedRule = importRule(plRuleImport,puzzleLevel);
            importedRules.add(importedRule);
        }
        return importedRules;
    }



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
        Optional<PLRuleEvent>  plRuleEventOptional = plRuleEventService.findById(dto.getPLRuleEventId());
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
