package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.puzzle.ALCityObjectInPGRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.RendererService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
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
    @Qualifier("PGRepository")
    PGRepository pgRepository;

    @Autowired
    ALCityObjectService alCityObjectService;

    @Autowired
    RendererService actionRendererService;

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
        return alCityObjectInPGRepository.findById(id);
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
        alCityObjectInPGRepository.deleteById(aLong);
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
    @Autowired
    private AppMemberRepository appMemberRepository;
    public ALCityObjectInPG save(CityObjectInPGDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleGroup> puzzleGroupOptional =  pgRepository.findByTitle(dto.getPuzzleGroup());
        Optional<ALCityObject> alCityObjectOptional =  alCityObjectService.findByTitle(dto.getAlCityObject());
        ALCityObjectInPG alCityObjectInPG=null;
        if(puzzleGroupOptional.isPresent())
        if (code.equalsIgnoreCase("Save")) { //Save
            alCityObjectInPG = new ALCityObjectInPG(dto.getTitle(), dto.getCode(),puzzleGroupOptional.get(),alCityObjectOptional.get(),
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            alCityObjectInPGRepository.save(alCityObjectInPG);
        }else{//edit
            Optional<ALCityObjectInPG> alCityObjectInPGOptional= alCityObjectInPGRepository.findById(dto.getId());
            if(alCityObjectInPGOptional.isPresent()) {
                alCityObjectInPG = alCityObjectInPGOptional.get();
                alCityObjectInPG.setCode(dto.getCode());
                alCityObjectInPG.setTitle(dto.getTitle());
                alCityObjectInPG.setVersion(alCityObjectInPG.getVersion()+1);
                alCityObjectInPG.setUpdated(DateUtils.getNow());
                alCityObjectInPG.setUpdatedBy(createdBy);
                alCityObjectInPGRepository.save(alCityObjectInPG);
            }
        }
        return alCityObjectInPG;
    }

    public void copyActionFromALCityObjectToPuzzleGroupObject(ALCityObjectInPG alCityObjectInPG){
        ALCityObject alCityObject = alCityObjectInPG.getAlCityObject();
        Collection<ObjectAction> actions = alCityObjectService.findAllActions(alCityObject);

        Iterator<ObjectAction> itr = actions.iterator();
        while(itr.hasNext()){
            ObjectAction action = new ObjectAction();
            action = itr.next();
            ObjectAction newAction = new ObjectAction(POActionOwnerType.Puzzle_Group_Object,alCityObjectInPG.getId(), action.getObjectAction(),action.getActionRenderer(),1L,action.getCreated(),
                    action.getUpdated(),action.getCreatedBy(),action.getUpdatedBy());
            puzzleObjectActionService.save(newAction);

            DTOUtil.copyAttributesActionFromTo(action.getId(),newAction.getId(), AttributeOwnerType.AlCity_Object,AttributeOwnerType.ALCity_Object_In_Puzzle_Group,
                    attributeService,attributeValueService);
        }

    }

}
