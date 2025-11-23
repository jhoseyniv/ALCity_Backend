package com.alcity.service.alobject;

import com.alcity.dto.pgimport.PGObjectActionImportDTO;
import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.repository.alobject.ActionRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import com.alcity.utility.ToolBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    public ObjectAction isImportActionIsInBasedObjectActions(PGObjectActionImportDTO dto , Collection<ObjectAction> baseObjectActions){
        return baseObjectActions.stream().filter(objectAction -> objectAction.getActionRenderer().getObjectAction().name().equals(dto.getActionName())).findFirst().get();
    }

    @Override
    public <S extends ObjectAction> S save(S entity) {
        return actionRepository.save(entity);
    }

    public  ObjectAction importPGObjectAction(PGObjectActionImportDTO dto,Collection<Attribute> parameters,Long pgObjectId) throws IOException {
        ObjectAction pgObjectAction=null;
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        ObjectActionType objectActionType = ObjectActionType.getByTitle(dto.getActionName());
        Optional<Renderer> rendererOptional = rendererService.findByHandler(dto.getHandler());

        pgObjectAction = new ObjectAction(POActionOwnerType.Puzzle_Group_Object, pgObjectId, objectActionType,rendererOptional.get(),
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(), createdBy.get());
        actionRepository.save(pgObjectAction);

        Collection<AttributeData>  importParameters = dto.getParameters();
        Iterator<AttributeData> iterator = importParameters.iterator();
        while(iterator.hasNext()){
            Collection<AttributeValue> attributeValues = new ArrayList<>();
            AttributeData importParam = iterator.next();
            Optional<Attribute> attributeOptional = parameters.stream().filter(att -> att.getName().equals(importParam.getName())).findFirst();
            if(attributeOptional.isPresent()){
                Attribute attribute =attributeOptional.get();
                AttributeValue attributeValue = DTOUtil.getAttributeValueFromPLVariableImport_New(importParam, attribute, createdBy.get(),pgObjectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                attributeValueService.save(attributeValue);
                attributeValues =attribute.getAttributeValues();
                attributeValues.add(attributeValue);
                attribute.setAttributeValues(attributeValues);
                attributeService.save(attribute);
            }
        }


        return pgObjectAction;
    }

    public  Collection<ObjectAction> importPGObjectActions(Collection<PGObjectActionImportDTO> dtos,Collection<ObjectAction> baseObjectActions,Long pgObjectId) throws IOException {
        Collection<ObjectAction> importedActions = new ArrayList<>();
        Iterator<PGObjectActionImportDTO> iterator = dtos.iterator();
        while(iterator.hasNext()){
            PGObjectActionImportDTO pgoImportAction = iterator.next();
            ObjectAction action = isImportActionIsInBasedObjectActions(pgoImportAction,baseObjectActions);
            // if action != null so action is exist in the base object
            if(action != null){
                //check number of import actions params with base object action params
                checkImportActionParams(pgoImportAction,action);
                Collection<Attribute> attributes = attributeService.findByOwnerId(action.getActionRenderer().getId());
                ObjectAction objectAction = importPGObjectAction(pgoImportAction,attributes,pgObjectId);
                importedActions.add(objectAction);
            }else{
                //this action is not defined in base object
                ToolBox.SendMessageToImportLogs(pgoImportAction.getActionName() + " : is not in base object action : ",DateUtils.getNow());
            }
        }
       return  importedActions;
    }


    public void checkImportActionParams( PGObjectActionImportDTO importAction,ObjectAction action) throws IOException {
        ObjectAction updatedAction = new ObjectAction();

        //check number of parameters

        if( importAction.getParameters()==null )  {
            ToolBox.SendMessageToImportLogs( " params is  0 " + importAction.getActionName() +" at date : ",DateUtils.getNow());
            return;
        }

        Integer numberOfParameterDTO = importAction.getParameters().size();
        Collection<Attribute> attributes = attributeService.findByOwnerId(action.getActionRenderer().getId());
        Integer numberOfParameterAction = attributes.size();
        if(!numberOfParameterDTO.equals(numberOfParameterAction))
            ToolBox.SendMessageToImportLogs(action.getActionRenderer().getHandler() + " params is:" + numberOfParameterAction + " but  number of Import action:"+ importAction.getActionName() +" is : "+ numberOfParameterDTO,DateUtils.getNow());

        //check existence of parameters
        Iterator<AttributeData> iterator = importAction.getParameters().iterator();
        while(iterator.hasNext()) {
            AttributeData importParam = iterator.next();
            Boolean isPresent = attributes.stream().filter(att -> att.getName().equals(importParam.getName())).findFirst().isPresent();
            if (!isPresent)
                ToolBox.SendMessageToImportLogs(importParam.getName() + " params is in :" + importAction.getActionName() + " is not in base object action : ", DateUtils.getNow());
        }
    }

    public ObjectAction savePGObjectAction( PGObjectActionImportDTO dto,Long pgObjectId) {
        ObjectAction pgObjectAction=null;
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<ObjectAction> objectActionOptional = actionRepository.findById(dto.getId());
         ObjectAction objectAction = objectActionOptional.get();
        if(objectActionOptional.isEmpty()) return  null;

        pgObjectAction = new ObjectAction(POActionOwnerType.Puzzle_Group_Object, pgObjectId, objectAction.getObjectAction(),objectAction.getActionRenderer(),
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(), createdBy.get());
        actionRepository.save(pgObjectAction);

        attributeService.importPGOActionParams_New(dto.getParameters(),pgObjectAction, AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
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
        if(id == null) {return Optional.empty();}
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
            Collection<AttributeValue> attributeValues = attributeValueService.findByOwnerId(action.getId());
            attributeValueService.deleteAll(attributeValues);

            Collection<Attribute> attributes = attributeService.findByOwnerId(action.getId());
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
