package com.alcity.service.alobject;

import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.repository.alobject.ActionRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
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

//    @Autowired
//    private ALCityObjectService alCityObjectService;
    @Autowired
    private RendererService rendererService;

    @Override
    public <S extends ObjectAction> S save(S entity) {
        return actionRepository.save(entity);
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
//            DTOUtil.copyActionParametersFromTo(dto.getActionRenderId(), puzzleObjectAction.getId(),AttributeOwnerType.Action_Handler_Parameter,AttributeOwnerType.Object_Action_Handler_Parameter,
//                    attributeService,attributeValueService);

      //      copyParametersFromActionToALCityObjectAction(dto.getActionRenderId(), dto.getObjectActionId() );

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
        return actionRepository.findByOwnerObjectidAndPoActionOwnerType(ownerId,ownerType);
    }

    public Collection<ObjectAction> findActionsForALCityObjectInPG(ALCityObjectInPG alCityObjectInPG) {
       // Collection<PuzzleObjectAction> actionsForAlCityObject = new ArrayList<PuzzleObjectAction>();
        Collection<ObjectAction> actionsForPuzzleGroupObject = new ArrayList<ObjectAction>();
        actionsForPuzzleGroupObject = actionRepository.findByOwnerObjectid(alCityObjectInPG.getId());
       // actionsForAlCityObject = puzzleObjectActionRepository.findByOwnerObjectid(alCityObjectInPG.getAlCityObject().getId());
      //  actionsForPuzzleGroupObject.addAll(actionsForAlCityObject);

        return actionsForPuzzleGroupObject;
    }

 //   public void copyParametersFromActionToALCityObjectAction(Long actionId , Long puzzleObjectActionId){

      //  Collection<RecordData> parameters =  DTOUtil.getAttributeForOwnerById(attributeService,actionId, AttributeOwnerType.Action_Handler_Parameter);;
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

 //   }



}
