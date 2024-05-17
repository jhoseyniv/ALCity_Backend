package com.alcity.service.puzzle;

import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.repository.puzzle.ALCityObjectInPGRepository;
import com.alcity.service.alobject.ActionRendererService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;


@Service
@Transactional
public class ALCityObjectInPGService implements ALCityObjectInPGRepository {

    @Autowired
    @Qualifier("ALCityObjectInPGRepository")
    ALCityObjectInPGRepository alCityObjectInPGRepository;

    @Autowired
    PuzzleObjectActionService puzzleObjectActionService;

    @Autowired
    ALCityObjectService alCityObjectService;

    @Autowired
    ActionRendererService actionRendererService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @Override
    public <S extends ALCityObjectInPG> S save(S entity) {
        return alCityObjectInPGRepository.save(entity);
    }

    @Override
    public <S extends ALCityObjectInPG> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityObjectInPG> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityObjectInPG> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityObjectInPG> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityObjectInPG entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityObjectInPG> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityObjectInPG> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<ALCityObjectInPG> findByCode(String code) {
        return alCityObjectInPGRepository.findByCode(code);
    }

    @Override
    public Optional<ALCityObjectInPG> findByCodeAndTitle(String code, String title) {
        return alCityObjectInPGRepository.findByCodeAndTitle(code,title);
    }

    @Override
    public Collection<ALCityObjectInPG> findByalCityObject(ALCityObject cityObject) {
        return alCityObjectInPGRepository.findByalCityObject(cityObject);
    }

    public void copyActionToFromALCityObjectToPuzzleGroupObject(ALCityObjectInPG alCityObjectInPG){
        ALCityObject alCityObject = alCityObjectInPG.getAlCityObject();
        Collection<PuzzleObjectAction> actions = alCityObjectService.findAllActions(alCityObject);

        Iterator<PuzzleObjectAction> itr = actions.iterator();
        while(itr.hasNext()){
            PuzzleObjectAction action = new PuzzleObjectAction();
            action = itr.next();
            PuzzleObjectAction newAction = new PuzzleObjectAction(POActionOwnerType.Puzzle_Group_Object,alCityObjectInPG.getId(), action.getObjectAction(),action.getActionRenderer(),1L,action.getCreated(),
                    action.getUpdated(),action.getCreatedBy(),action.getUpdatedBy());
            puzzleObjectActionService.save(newAction);

            DTOUtil.copyAttributesActionFromTo(action, AttributeOwnerType.AlCity_Object,AttributeOwnerType.ALCity_Object_In_Puzzle_Group,
                    attributeService,attributeValueService);
        }

    }

}
