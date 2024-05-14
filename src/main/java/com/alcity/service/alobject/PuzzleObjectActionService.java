package com.alcity.service.alobject;

import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ActionRenderer;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.repository.alobject.PuzzleObjectActionRepository;
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

    @Override
    public <S extends PuzzleObjectAction> S save(S entity) {
        return puzzleObjectActionRepository.save(entity);
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
    public void copyRendersToAlcityObject(PuzzleObjectAction action){
        ActionRenderer actionRenderer = action.getActionRenderer();
        Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerType(actionRenderer.getId(), AttributeOwnerType.Action_Renderer_Parameter);
        Iterator<Attribute> itr = parameters.iterator();
        while(itr.hasNext()){
            Attribute att = new Attribute();
            att = itr.next();
            Attribute newRecord = new Attribute(att.getName(),action.getOwnerObjectid(),AttributeOwnerType.AlCity_Object,att.getDataType(),
                    att.getVersion(),att.getCreated(),att.getUpdated(),att.getCreatedBy(),att.getUpdatedBy());
            attributeService.save(newRecord);

            attributeValues = att.getAttributeValues();
            Iterator<AttributeValue> itrAttributeValuesIterator = attributeValues.iterator();
            while(itrAttributeValuesIterator.hasNext()) {
                    AttributeValue attValue = new AttributeValue();
                attValue = itrAttributeValuesIterator.next();
                AttributeValue newAttributeValue = new AttributeValue(attValue.getBooleanValue(),attValue.getIntValue(),attValue.getLongValue(),attValue.getStringValue(),
                        attValue.getObjectValue(),attValue.getDoubleValue(),attValue.getBinaryContentId(),newRecord,newRecord,
                        attValue.getVersion(),attValue.getCreated(),attValue.getUpdated(),attValue.getCreatedBy(),attValue.getUpdatedBy());

                //for log info
                if(att.getName().equalsIgnoreCase("CODE"))   newAttributeValue.setStringValue("NEW CODE FOR ALCITY Object");
                if(att.getName().equalsIgnoreCase("aSync"))     newAttributeValue.setStringValue("True");

                attributeValueService.save(newAttributeValue);
            }


        }

    }


}
