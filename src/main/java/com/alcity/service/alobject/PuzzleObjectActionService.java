package com.alcity.service.alobject;

import com.alcity.dto.Interpreter.object.RecordData;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.repository.alobject.PuzzleObjectActionRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.puzzle.ALCityObjectService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleObjectActionService implements PuzzleObjectActionRepository {

    @Autowired
    PuzzleObjectActionRepository puzzleObjectActionRepository;
    @Autowired
    AttributeService  attributeService;
    @Autowired
    AttributeValueService  attributeValueService;
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private ALCityObjectService alCityObjectService;
    @Autowired
    private RendererService rendererService;

    @Override
    public <S extends ObjectAction> S save(S entity) {
        return puzzleObjectActionRepository.save(entity);
    }
    public ObjectAction save(ActionDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<ALCityObject> cityObjectOptional = alCityObjectService.findById(dto.getOwnerObjectid());
        ObjectActionType objectAction = ObjectActionType.getByTitle(dto.getObjectAction());
        POActionOwnerType  actionOwnerType = POActionOwnerType.getByTitle(dto.getOwnerType());
        Optional<Renderer> rendererOptional = rendererService.findById(dto.getActionRenderId());
        ObjectAction puzzleObjectAction=null;
        if (code.equalsIgnoreCase("Save")) { //Save
           puzzleObjectAction = new ObjectAction(actionOwnerType, dto.getOwnerObjectid(), objectAction,rendererOptional.get(),
                     1L,DateUtils.getNow(),DateUtils.getNow(),createdBy, createdBy);
            puzzleObjectActionRepository.save(puzzleObjectAction);
            DTOUtil.copyAttributesActionFromTo(dto.getActionRenderId(), puzzleObjectAction.getId(),AttributeOwnerType.Action_Renderer_Parameter,AttributeOwnerType.AlCity_Object,
                    attributeService,attributeValueService);

            copyParametersFromActionToALCityObjectAction(dto.getActionRenderId(), dto.getObjectActionId() );

       }else{//edit
            Optional<ObjectAction> puzzleObjectActionOptional= puzzleObjectActionRepository.findById(dto.getId());
           if(puzzleObjectActionOptional.isPresent()) {
                puzzleObjectAction = puzzleObjectActionOptional.get();
                puzzleObjectAction.setPoActionOwnerType(actionOwnerType);
                puzzleObjectAction.setObjectAction(objectAction);
                puzzleObjectAction.setOwnerObjectid(dto.getOwnerObjectid());
               puzzleObjectAction.setActionRenderer(rendererOptional.get());
                puzzleObjectAction.setVersion(puzzleObjectAction.getVersion()+1);
                puzzleObjectAction.setCreated(DateUtils.getNow());
                puzzleObjectAction.setUpdated(DateUtils.getNow());
                puzzleObjectAction.setCreatedBy(createdBy);
                puzzleObjectAction.setUpdatedBy(createdBy);
                puzzleObjectActionRepository.save(puzzleObjectAction);
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
        return puzzleObjectActionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ObjectAction> findAll() {
        return puzzleObjectActionRepository.findAll();
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
        puzzleObjectActionRepository.deleteById(aLong);
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
        return null;
    }

    @Override
    public Collection<ObjectAction> findByOwnerObjectidAndPoActionOwnerType(Long ownerId, POActionOwnerType ownerType) {
        return puzzleObjectActionRepository.findByOwnerObjectidAndPoActionOwnerType(ownerId,ownerType);
    }

    public Collection<ObjectAction> findActionsForALCityObjectInPG(ALCityObjectInPG alCityObjectInPG) {
       // Collection<PuzzleObjectAction> actionsForAlCityObject = new ArrayList<PuzzleObjectAction>();
        Collection<ObjectAction> actionsForPuzzleGroupObject = new ArrayList<ObjectAction>();
        actionsForPuzzleGroupObject = puzzleObjectActionRepository.findByOwnerObjectid(alCityObjectInPG.getId());
       // actionsForAlCityObject = puzzleObjectActionRepository.findByOwnerObjectid(alCityObjectInPG.getAlCityObject().getId());
      //  actionsForPuzzleGroupObject.addAll(actionsForAlCityObject);

        return actionsForPuzzleGroupObject;
    }

    public void copyParametersFromActionToALCityObjectAction(Long actionId , Long puzzleObjectActionId){

        Collection<RecordData> parameters =  DTOUtil.getAttributeForOwnerById(attributeService,actionId, AttributeOwnerType.Action_Renderer_Parameter);;
//        Collection<PuzzleObjectAction> actions = alCityObjectService.findAllActions(alCityObject);
//
//        Iterator<PuzzleObjectAction> itr = actions.iterator();
//        while(itr.hasNext()){
//            PuzzleObjectAction action = new PuzzleObjectAction();
//            action = itr.next();
//            PuzzleObjectAction newAction = new PuzzleObjectAction(POActionOwnerType.Puzzle_Group_Object,alCityObjectInPG.getId(), action.getObjectAction(),action.getActionRenderer(),1L,action.getCreated(),
//                    action.getUpdated(),action.getCreatedBy(),action.getUpdatedBy());
//            puzzleObjectActionService.save(newAction);
//
//            DTOUtil.copyAttributesActionFromTo(action.getId(),newAction.getId(), AttributeOwnerType.AlCity_Object,AttributeOwnerType.ALCity_Object_In_Puzzle_Group,
//                    attributeService,attributeValueService);
//        }

    }



}
