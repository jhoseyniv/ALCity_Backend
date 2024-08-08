package com.alcity.service.alobject;

import com.alcity.dto.Interpreter.object.RecordData;
import com.alcity.dto.puzzle.ALCityObjectDTO;
import com.alcity.dto.puzzle.PuzzleObjectActionDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
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
import java.util.Iterator;
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
    public <S extends PuzzleObjectAction> S save(S entity) {
        return puzzleObjectActionRepository.save(entity);
    }
    public PuzzleObjectAction save(PuzzleObjectActionDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<ALCityObject> cityObjectOptional = alCityObjectService.findById(dto.getOwnerObjectid());
        ObjectAction objectAction = ObjectAction.getByTitle(dto.getObjectAction());
        POActionOwnerType  actionOwnerType = POActionOwnerType.getByTitle(dto.getOwnerType());
        Optional<Renderer> rendererOptional = rendererService.findById(dto.getActionRenderId());
        PuzzleObjectAction puzzleObjectAction=null;
        if (code.equalsIgnoreCase("Save")) { //Save
           puzzleObjectAction = new PuzzleObjectAction(actionOwnerType, dto.getOwnerObjectid(), objectAction,rendererOptional.get(),
                     1L,DateUtils.getNow(),DateUtils.getNow(),createdBy, createdBy);
            puzzleObjectActionRepository.save(puzzleObjectAction);
            DTOUtil.copyAttributesActionFromTo(dto.getActionRenderId(), puzzleObjectAction.getId(),AttributeOwnerType.Action_Renderer_Parameter,AttributeOwnerType.AlCity_Object,
                    attributeService,attributeValueService);

            copyParametersFromActionToALCityObjectAction(dto.getActionRenderId(), dto.getObjectActionId() );

       }else{//edit
            Optional<PuzzleObjectAction> puzzleObjectActionOptional= puzzleObjectActionRepository.findById(dto.getId());
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
    public <S extends PuzzleObjectAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleObjectAction> findById(Long id) {
        return puzzleObjectActionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleObjectAction> findAll() {
        return puzzleObjectActionRepository.findAll();
    }

    @Override
    public Iterable<PuzzleObjectAction> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleObjectAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleObjectAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleObjectAction> findByActionRendererId(Long id) {
        return null;
    }

    @Override
    public Collection<PuzzleObjectAction> findByPoActionOwnerType(Long id) {
        return null;
    }

    @Override
    public Collection<PuzzleObjectAction> findByOwnerObjectid(Long ownerId) {
        return null;
    }

    @Override
    public Collection<PuzzleObjectAction> findByOwnerObjectidAndPoActionOwnerType(Long ownerId, POActionOwnerType ownerType) {
        return puzzleObjectActionRepository.findByOwnerObjectidAndPoActionOwnerType(ownerId,ownerType);
    }

    public Collection<PuzzleObjectAction> findActionsForALCityObjectInPG(ALCityObjectInPG alCityObjectInPG) {
       // Collection<PuzzleObjectAction> actionsForAlCityObject = new ArrayList<PuzzleObjectAction>();
        Collection<PuzzleObjectAction> actionsForPuzzleGroupObject = new ArrayList<PuzzleObjectAction>();
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
