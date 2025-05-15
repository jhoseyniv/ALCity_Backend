package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PLRulePostActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.PLRulePostActionOwnerType;
import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLRulePostActionRepository;
import com.alcity.repository.puzzle.PLRuleRepository;
import com.alcity.service.alobject.AttributeService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

    @Autowired
    private AttributeService attributeService;

    @Override
    public <S extends PLRulePostAction> S save(S entity) {
        return plRulePostActionRepository.save(entity);
    }

    public Collection<PLRulePostAction> copyAll(Collection<PLRulePostAction> postActions,Long newOwner) {
        Collection<PLRulePostAction> copiedPostActions = new ArrayList<>();
        Iterator<PLRulePostAction> iterator = postActions.iterator();
        while(iterator.hasNext()){
            PLRulePostAction postAction = iterator.next();
            PLRulePostAction copiedPostAction = copy(postAction,newOwner);
            copiedPostActions.add(copiedPostAction);
        }
        return copiedPostActions;
    }

    public PLRulePostAction copy(PLRulePostAction postAction,Long newOwner) {
        PLRulePostAction newPostAction = new PLRulePostAction(newOwner,postAction.getOwnerType(),postAction.getPlRulePostActionType(),
                postAction.getOrdering()+1,postAction.getActionName(), postAction.getObjectId(), postAction.getVariable(), postAction.getValueExperssion(),
                postAction.getSubAction(), postAction.getAlertType(), postAction.getAlertMessage(), postAction.getActionKey(),
                postAction.getVersion(), postAction.getCreated(), postAction.getUpdated(), postAction.getCreatedBy(),postAction.getUpdatedBy());
        plRulePostActionRepository.save(newPostAction);
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(postAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeService.copyAttributes(parameters, newPostAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        return newPostAction;
    }
    public PLRulePostAction copyActionTree(PLRulePostAction postAction,Long newOwner) {
        Collection<PLRulePostAction> plRulePostActions = DTOUtil.getPlRulePostActions(this,postAction.getId());

        PLRulePostAction newPostAction = new PLRulePostAction(newOwner,postAction.getOwnerType(),postAction.getPlRulePostActionType(),
                postAction.getOrdering()+1,postAction.getActionName(), postAction.getObjectId(), postAction.getVariable(), postAction.getValueExperssion(),
                postAction.getSubAction(), postAction.getAlertType(), postAction.getAlertMessage(), postAction.getActionKey(),
                postAction.getVersion(), postAction.getCreated(), postAction.getUpdated(), postAction.getCreatedBy(),postAction.getUpdatedBy());
        plRulePostActionRepository.save(newPostAction);
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(postAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeService.copyAttributes(parameters, newPostAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        return newPostAction;
    }

    public PLRulePostAction save(PLRulePostActionDTO dto, String code) {
       Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
       PLRulePostAction postAction=null;
        PLRulePostActionOwnerType ownerType = PLRulePostActionOwnerType.getByTitle(dto.getOwnerType());
        PLRulePostActionType  plRulePostActionType = PLRulePostActionType.getByTitle(dto.getPlRulePostActionType());

        if(ownerType ==null ) return  null;

        if (code.equalsIgnoreCase("Save")) { //Save
            postAction = new PLRulePostAction(dto.getOwnerId(),ownerType, plRulePostActionType, dto.getOrdering(), dto.getActionName(), dto.getObjectId(), dto.getVariable(),
                                    dto.getValueExperssion(),dto.getSubAction(),dto.getAlertType(),dto.getAlertMessage(), dto.getActionKey(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            plRulePostActionRepository.save(postAction);
        }else{//edit
            Optional<PLRulePostAction> plRulePostActionOptional= plRulePostActionRepository.findById(dto.getId());
            if(plRulePostActionOptional.isPresent()) {
                postAction = plRulePostActionOptional.get();
                postAction.setActionName(dto.getActionName());
                postAction.setPlRulePostActionType(plRulePostActionType);
                postAction.setAlertMessage(dto.getAlertMessage());
                postAction.setOrdering(dto.getOrdering());
                postAction.setOwnerId(dto.getOwnerId());
                postAction.setOwnerType(ownerType);
                postAction.setObjectId(dto.getObjectId());
                postAction.setValueExperssion(dto.getValueExperssion());
                postAction.setSubAction(dto.getSubAction());
                postAction.setVariable(dto.getVariable());
                postAction.setActionKey(dto.getActionKey());
                postAction.setCreated(DateUtils.getNow());
                postAction.setUpdated(DateUtils.getNow());
                postAction.setCreatedBy(createdBy.get());
                postAction.setUpdatedBy(createdBy.get());
                postAction.setVersion(postAction.getVersion()+1);
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
        plRulePostActionRepository.deleteById(aLong);
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

    @Override
    public ArrayList<PLRulePostAction> findByOwnerId(Long ownerId) {
        return plRulePostActionRepository.findByOwnerId(ownerId);
    }

}
