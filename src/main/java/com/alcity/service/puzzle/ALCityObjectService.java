package com.alcity.service.puzzle;

import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.alobject.ObjectCategoryRepository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.puzzle.ALCityObjectRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class ALCityObjectService implements ALCityObjectRepository {

    @Autowired
    @Qualifier("ALCityObjectRepository")
    ALCityObjectRepository alCityObjectRepository ;

    @Autowired
    ActionService puzzleObjectActionService ;
    @Autowired
    ObjectCategoryRepository objectCategoryRepository ;

    @Override
    public <S extends ALCityObject> S save(S entity) {
        return alCityObjectRepository.save(entity);
    }

    @Override
    public <S extends ALCityObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityObject> findById(Long id) {
        return alCityObjectRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityObject> findAll() {
        return alCityObjectRepository.findAll();
    }

    @Override
    public Collection<ALCityObject> findALCityObjectByObjectCategory(ObjectCategory category) {
        return alCityObjectRepository.findALCityObjectByObjectCategory(category);
    }

    @Override
    public Optional<ALCityObject> findByIcon(BinaryContent icon) {
        return alCityObjectRepository.findByIcon(icon);
    }

    @Override
    public Optional<ALCityObject> findByPic(BinaryContent pic) {
        return alCityObjectRepository.findByPic(pic);
    }

    @Override
    public Iterable<ALCityObject> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        alCityObjectRepository.deleteById(aLong);
    }

    @Override
    public void delete(ALCityObject object)  {
        try {
            alCityObjectRepository.delete(object);
        }catch (Exception e )
        {
            throw new ViolateForeignKeyException(object.getTitle(), object.getId(), ALCityObject.class.toString());
        }

    }



    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityObject> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<ALCityObject> findByTitle(String title) {
        return alCityObjectRepository.findByTitle(title);
    }

    public  Collection<ObjectAction> findAllActions(ALCityObject cityObject){
        Collection<ObjectAction> actions = new ArrayList<ObjectAction>();
        actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(cityObject.getId(), POActionOwnerType.ALCity_Object);

        return actions;
    }
    @Autowired
    private AppMemberRepository appMemberRepository;


    @Autowired
    private BinaryContentRepository binaryContentRepository;

    public ALCityObject save(CityObjectDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> pic = binaryContentRepository.findById(dto.getPictureId());
        ObjectCategory objectCategory =  objectCategoryRepository.findByValue(dto.getCategory());
        ALCityObject alCityObject=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            alCityObject = new ALCityObject(1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy,dto.getTitle(), objectCategory,pic.get(),icon.get());
            try {
                alCityObjectRepository.save(alCityObject);
            }
            catch (RuntimeException e) {
                    throw new UniqueConstraintException(dto.getTitle(), dto.getId(), "title must be Unique");
            }
        }else{//edit
            Optional<ALCityObject> alCityObjectOptional= alCityObjectRepository.findById(dto.getId());
            if(alCityObjectOptional.isPresent()) {
                alCityObject = alCityObjectOptional.get();
                alCityObject.setObjectCategory(objectCategory);
                alCityObject.setTitle(dto.getTitle());
                alCityObject.setPic(pic.get());
                alCityObject.setIcon(icon.get());
                alCityObject.setVersion(alCityObject.getVersion()+1);
                alCityObject.setCreated(DateUtils.getNow());
                alCityObject.setUpdated(DateUtils.getNow());
                alCityObject.setCreatedBy(createdBy);
                alCityObject.setUpdatedBy(createdBy);
                alCityObjectRepository.save(alCityObject);
            }
        }
        return alCityObject;
    }

}
