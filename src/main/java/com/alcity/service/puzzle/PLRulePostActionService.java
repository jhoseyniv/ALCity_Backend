package com.alcity.service.puzzle;

import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.dto.plimpexport.rulemport.PLRulePostActionImport;
import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;
import com.alcity.dto.puzzle.PLRulePostActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.PLRulePostActionOwnerType;
import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLRulePostActionRepository;
import com.alcity.repository.puzzle.PLRuleRepository;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
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

    @Autowired
    private AttributeValueService attributeValueService;

    @Override
    public <S extends PLRulePostAction> S save(S entity) {
        return plRulePostActionRepository.save(entity);
    }

    public PLRulePostAction importPostAction(PLRulePostActionImport dto,Long newOwner) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLRulePostActionOwnerType ownerType = PLRulePostActionOwnerType.getByTitle(dto.getPostActionOwnerType());
        PLRulePostActionType plRulePostActionType = PLRulePostActionType.getByTitle(dto.getPostActionType());
        PLRulePostAction importedPostAction = new PLRulePostAction(newOwner,ownerType,plRulePostActionType,
                dto.getOrdering(),dto.getActionName(), dto.getObjectId(), dto.getVariable(), dto.getValueExpression(),
                dto.getSubAction(), dto.getAlertType(), dto.getAlertMessage(), dto.getActionKey(),
                1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(),createdBy.get());
        plRulePostActionRepository.save(importedPostAction);

        if(dto.getParameters().size()>0){
            System.out.println("parameter is defined.............");
            Collection<AttributeData> recordDataImports = dto.getParameters();
            //Collection<AttributeDTOSave> dtos = new ArrayList<>();
            attributeService.importPLRulePostActionParam(recordDataImports,importedPostAction,AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        }

        return importedPostAction;
    }

    public PLRulePostAction importPostAction_New(PostActionTreeImport_New dto, Long newOwner,Integer  ordering) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        if(dto.elseActions!=null ){
            System.out.println("else action is defined.............");
        }
        PLRulePostActionOwnerType ownerType = PLRulePostActionOwnerType.getByTitle(dto.getPostActionOwnerType());
        PLRulePostActionType plRulePostActionType = PLRulePostActionType.getByTitle(dto.getActionType());
        PLRulePostAction importedPostAction = new PLRulePostAction(newOwner,ownerType,plRulePostActionType,
                ordering,dto.getActionName(), dto.getObjectId(), dto.getVariable(), dto.getValueExperssion(),
                dto.getSubAction(), dto.getAlertType(), dto.getAlertMessage(), dto.getActionKey(),
                1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(),createdBy.get());
        plRulePostActionRepository.save(importedPostAction);
        if(dto.getParameters().size()>0){
            System.out.println("parameter is defined.............");
            Collection<AttributeData> recordDataImports = dto.getParameters();
            //Collection<AttributeDTOSave> dtos = new ArrayList<>();
            attributeService.importPLRulePostActionParam_New(recordDataImports,importedPostAction,AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        }

        return importedPostAction;
    }


    public PLRulePostAction importPLRulePostActionTree(PostActionTreeImport root,Long ruleId) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLRulePostAction newPostAction =null;
        DTOUtil.preOrderTraversal(this,root,ruleId);
        return newPostAction;
    }
    public PLRulePostAction importPLRulePostActionTree_New(PostActionTreeImport_New root,Long ruleId) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLRulePostAction newPostAction =null;
        //for root of trees action owner_type will be pl rule
        root.setPostActionOwnerType(PLRulePostActionOwnerType.Puzzle_Level_Rule.name());
        Integer ordering = 0;
        DTOUtil.preOrderTraversal_New(this,root,ruleId,ordering);
        return newPostAction;
    }

    public Collection<PLRulePostAction> importPLRulePostActionsTrees_New(Collection<PostActionTreeImport_New> postActionTreeImports, Long ruleId) {
        Collection<PLRulePostAction> importedPostActions = new ArrayList<>();
        Iterator<PostActionTreeImport_New> iterator = postActionTreeImports.iterator();
        Integer ordering = 0;
        while(iterator.hasNext()){
            PostActionTreeImport_New postActionTreeImport = iterator.next();
            postActionTreeImport.setOrdering(ordering++);
            // for root of trees- owner_id will be Rule_id
            PLRulePostAction importedPostAction = importPLRulePostActionTree_New(postActionTreeImport,ruleId);
            importedPostActions.add(importedPostAction);
        }
        return importedPostActions;
    }


    public Collection<PLRulePostAction> importPLRulePostActionsTrees(Collection<PostActionTreeImport> postActionTreeImports, Long ruleId) {
        Collection<PLRulePostAction> importedPostActions = new ArrayList<>();
        Iterator<PostActionTreeImport> iterator = postActionTreeImports.iterator();
        while(iterator.hasNext()){
            PostActionTreeImport postActionTreeImport = iterator.next();
            // for root of trees- owner_id will be Rule_id
            PLRulePostAction importedPostAction = importPLRulePostActionTree(postActionTreeImport,ruleId);
            importedPostActions.add(importedPostAction);
        }
        return importedPostActions;
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
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(postAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeService.copyAttributes(parameters, newPostAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
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
        if(id == null) return Optional.empty();
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

    private void deleteChildren(Long parentId)  {
        // Find all children of the parent node
        Collection<PLRulePostAction> childs = plRulePostActionRepository.findByOwnerId(parentId);
        Iterator<PLRulePostAction>  iterator = childs.iterator();
        while (iterator.hasNext())
        {
              PLRulePostAction childPostAction = iterator.next();
              // Recursively delete children of this child
              deleteChildren(childPostAction.getId());
              // Delete the child node
             plRulePostActionRepository.deleteById(childPostAction.getId());
        }
    }



//    public void deletePostActionWithChilds(PLRulePostAction postAction) {
//        // Find all children of the parent node
//        Collection<PLRulePostAction> childs = plRulePostActionRepository.findByOwnerId(postAction.getId());
//        Iterator<PLRulePostAction>  iterator = childs.iterator();
//        while (iterator.hasNext()) {
//            PLRulePostAction childPostAction = iterator.next();
//            // Recursively delete children of this child
//            deleteChildren(childPostAction.getId());
//            // Delete the child node
//            plRulePostActionRepository.deleteById(childPostAction.getId());
//            Collection<AttributeValue>  attributeValues= attributeValueService.findByOwnerId(childPostAction.getId());
//            attributeValueService.deleteAll(attributeValues);
//            Collection<Attribute> attributes = attributeService.findByOwnerId(childPostAction.getId());
//            attributeService.deleteAll(attributes);
//        }
//    }
    public void preOrderTraversal(PLRulePostAction node) {
        Collection<PLRulePostAction> children = plRulePostActionRepository.findByOwnerId(node.getId());
        Iterator<PLRulePostAction> childIterator = children.iterator();
        PLRulePostAction child = new PLRulePostAction();
        while(childIterator.hasNext()){
            child = childIterator.next();
            preOrderTraversal(child) ;
        }
        plRulePostActionRepository.deleteById(node.getId());
        Collection<Attribute> attributes = new ArrayList<Attribute>();
        attributes = attributeService.findByOwnerId(node.getId());
        attributeService.deleteAll(attributes);

        Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
        attributeValues = attributeValueService.findByOwnerId(node.getId());
        attributeValueService.deleteAll(attributeValues);

    }


    public void deleteActionTree(PLRulePostAction root) {
        Collection<PLRulePostAction> childs = plRulePostActionRepository.findByOwnerId(root.getId());
        Iterator<PLRulePostAction> iterator = childs.iterator();

        while(iterator.hasNext()){
            PLRulePostAction child = iterator.next();
            preOrderTraversal(child) ;
        }
        plRulePostActionRepository.delete(root);

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
