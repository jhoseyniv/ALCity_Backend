package com.alcity.service.alobject;

import com.alcity.dto.pgimport.PGObjectActionImportDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.repository.alobject.ActionRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class ActionService implements ActionRepository {

    @Autowired
    ActionRepository actionRepository;
    @Autowired
    AttributeService  attributeService;
    @Autowired
    AttributeValueService  attributeValueService;
    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private RendererService rendererService;

    @Override
    public <S extends ObjectAction> S save(S entity) {
        return actionRepository.save(entity);
    }
    public  Collection<ObjectAction> importPGObjectActions(Collection<PGObjectActionImportDTO> dtos,Long pgObjectId) {
        Collection<ObjectAction> actions = new ArrayList<>();
        Iterator<PGObjectActionImportDTO> iterator = dtos.iterator();
        while(iterator.hasNext()){
            PGObjectActionImportDTO dto = iterator.next();
            ObjectAction objectAction = importPGObjectAction(dto,pgObjectId);
            actions.add(objectAction);
        }
       return  actions;
    }
    public ObjectAction importPGObjectAction(PGObjectActionImportDTO dto,Long pgObjectId) {
        ObjectAction pgObjectAction=null;
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<Renderer> rendererOptional = rendererService.findById(dto.getActionId());
        //ObjectActionType objectActionType = ObjectActionType.getByTitle(dto.getActionName());

        pgObjectAction = new ObjectAction(POActionOwnerType.Puzzle_Group_Object, pgObjectId, rendererOptional.get().getObjectAction(),rendererOptional.get(),
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(), createdBy.get());
        actionRepository.save(pgObjectAction);

        return pgObjectAction;
    }

    public ObjectAction save(ActionDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        //Optional<ALCityObject> cityObjectOptional = alCityObjectService.findById(dto.getOwnerObjectid());
        ObjectActionType objectActionType = ObjectActionType.getByTitle(dto.getObjectAction());
        POActionOwnerType  actionOwnerType = POActionOwnerType.getByTitle(dto.getOwnerType());
        Optional<Renderer> rendererOptional = rendererService.findById(dto.getActionRenderId());
        ObjectAction puzzleObjectAction=null;
        if (code.equalsIgnoreCase("Save")) { //Save
           puzzleObjectAction = new ObjectAction(actionOwnerType, dto.getOwnerObjectid(), objectActionType,rendererOptional.get(),
                     1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(), createdBy.get());
            actionRepository.save(puzzleObjectAction);

       }else{//edit
            Optional<ObjectAction> puzzleObjectActionOptional= actionRepository.findById(dto.getId());
           if(puzzleObjectActionOptional.isPresent()) {
                puzzleObjectAction = puzzleObjectActionOptional.get();
                puzzleObjectAction.setPoActionOwnerType(actionOwnerType);
                puzzleObjectAction.setObjectAction(objectActionType);
                puzzleObjectAction.setOwnerObjectid(dto.getOwnerObjectid());
                puzzleObjectAction.setActionRenderer(rendererOptional.get());
                puzzleObjectAction.setVersion(puzzleObjectAction.getVersion()+1);
                puzzleObjectAction.setCreated(DateUtils.getNow());
                puzzleObjectAction.setUpdated(DateUtils.getNow());
                puzzleObjectAction.setCreatedBy(createdBy.get());
                puzzleObjectAction.setUpdatedBy(createdBy.get());
               actionRepository.save(puzzleObjectAction);
            }
        }
        return puzzleObjectAction;
    }

    @Override
    public <S extends ObjectAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ObjectAction> findById(Long id) {
        return actionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ObjectAction> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Iterable<ObjectAction> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        actionRepository.deleteById(aLong);
    }

    @Override
    public void delete(ObjectAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ObjectAction> entities) {

    }

    public void deleteAllActions(Collection<ObjectAction> actions) {
        Iterator<ObjectAction> iterator = actions.iterator();
        while(iterator.hasNext()){
            //find and remove properties
            ObjectAction action = iterator.next();
            Collection<AttributeValue> attributeValues = attributeValueService.findByOwnerId(action.getOwnerObjectid());
            attributeValueService.deleteAll(attributeValues);

            Collection<Attribute> attributes = attributeService.findByOwnerId(action.getOwnerObjectid());
            attributeService.deleteAll(attributes);

            //delete action
            actionRepository.delete(action);
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ObjectAction> findByActionRendererId(Long id) {
        return null;
    }

    @Override
    public Collection<ObjectAction> findByPoActionOwnerType(Long id) {
        return null;
    }

    @Override
    public Collection<ObjectAction> findByOwnerObjectid(Long ownerId) {
        return actionRepository.findByOwnerObjectid(ownerId);
    }

    @Override
    public Collection<ObjectAction> findByOwnerObjectidAndPoActionOwnerType(Long ownerId, POActionOwnerType ownerType) {
        return actionRepository.findByOwnerObjectidAndPoActionOwnerType(ownerId,ownerType);
    }

    public Collection<ObjectAction> findActionsForALCityObjectInPG(PGObject alCityObjectInPG) {
        Collection<ObjectAction> actionsForPuzzleGroupObject = new ArrayList<ObjectAction>();
        actionsForPuzzleGroupObject = actionRepository.findByOwnerObjectid(alCityObjectInPG.getId());

        return actionsForPuzzleGroupObject;
    }

}
