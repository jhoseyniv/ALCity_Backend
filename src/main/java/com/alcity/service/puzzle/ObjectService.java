package com.alcity.service.puzzle;

import com.alcity.dto.search.ObjectSearchCriteriaDTO;
import com.alcity.dto.search.SearchResultCityObjectDTO;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleGroup;
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
import com.alcity.repository.puzzle.ObjectRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DateUtils;
import com.alcity.utility.PLDTOUtil;
import com.alcity.utility.ToolBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class ObjectService implements ObjectRepository {
    @Autowired
    ObjectRepository objectRepository ;

    @Autowired
    ActionService puzzleObjectActionService ;
    @Autowired
    ObjectCategoryRepository objectCategoryRepository ;
    @Autowired
    ObjectInPGService cityObjectInPGService ;
    @Autowired
    PGService pgService ;

    @Override
    public <S extends ALCityObject> S save(S entity) {
        return objectRepository.save(entity);
    }

    @Override
    public <S extends ALCityObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityObject> findById(Long id) {
        return objectRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityObject> findAll() {
        return objectRepository.findAll();
    }

    @Override
    public Collection<ALCityObject> findALCityObjectByObjectCategory(ObjectCategory category) {
        return objectRepository.findALCityObjectByObjectCategory(category);
    }

    @Override
    public Collection<ALCityObject> findALCityObjectByTitleIgnoreCaseContains(String criteria) {
        return objectRepository.findALCityObjectByTitleIgnoreCaseContains(criteria);
    }

    @Override
    public Collection<ALCityObject> findALCityObjectByObjectCategoryAndTitleIgnoreCase(ObjectCategory category, String title) {
        return objectRepository.findALCityObjectByObjectCategoryAndTitleIgnoreCase(category,title);
    }

//    public Collection<ALCityObject> findALCityObjectByTitleContains(String criteria) {
//        return objectRepository.findALCityObjectByTitleIgnoreCaseContains(criteria);
//    }
//
//    public Collection<ALCityObject> findALCityObjectByObjectCategoryAndTitle(ObjectCategory category, String title) {
//        return objectRepository.findALCityObjectByObjectCategoryAndTitleIgnoreCase(category,title);
//    }
    public Collection<ALCityObject> searchCityObjectSByCriteria(ObjectSearchCriteriaDTO criteria) {
        Optional<ObjectCategory> categoryOptional = objectCategoryRepository.findById(criteria.getObjectCategoryId());
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(criteria.getPuzzleGroupdId());
        Collection<ALCityObject> matchValues=null;

        if(puzzleGroupOptional.isEmpty() && categoryOptional.isEmpty() && (criteria.getTitle().equals("") || criteria.getTitle()==null))
            matchValues = objectRepository.findAll();

            //000
        else if(puzzleGroupOptional.isEmpty() && categoryOptional.isEmpty() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))) {
            //001
            matchValues = objectRepository.findALCityObjectByTitleIgnoreCaseContains(criteria.getTitle());
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(), criteria.getTitle())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isEmpty() && categoryOptional.isPresent() && (criteria.getTitle().equals("") || criteria.getTitle()==null))
            matchValues = objectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            //010
        else if(puzzleGroupOptional.isEmpty() && categoryOptional.isPresent() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))) {
            //011
            matchValues = objectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //100
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = getALCityObjectByPG(pgs);
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //101
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = getALCityObjectByPG(pgs);
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.title)).collect(Collectors.toList());

        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //110
            Collection<ALCityObject> list1 =  objectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            Collection<ALCityObject>  list2 = getALCityObjectByPG(pgs);
            matchValues = list1.stream().filter(cityObject ->list2.contains(cityObject)).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //111
            Collection<ALCityObject> cityObjects =  objectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            Collection<ALCityObject> list1 = cityObjects.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            Collection<ALCityObject> list2 = getALCityObjectByPG(pgs);
            matchValues = list1.stream().filter(cityObject ->list2.contains(cityObject)).collect(Collectors.toList());
        }

        return matchValues;
    }
    public Collection<SearchResultCityObjectDTO> searchCityObjectInPGByCriteria(ObjectSearchCriteriaDTO criteria) {
        Optional<ObjectCategory> categoryOptional = objectCategoryRepository.findById(criteria.getObjectCategoryId());
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(criteria.getPuzzleGroupdId());
        Collection<SearchResultCityObjectDTO> matchValues=null;

        if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //100
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = PLDTOUtil.getSearchResultCityObjectsInPGDTOS(pgs);
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //101
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = PLDTOUtil.getSearchResultCityObjectsInPGDTOS(pgs);
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //110
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = PLDTOUtil.getSearchResultCityObjectsInPGDTOS(pgs);
            matchValues = matchValues.stream().filter(cityObject -> cityObject.getCategoryId().equals(criteria.getObjectCategoryId())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //111
            Collection<ALCityObjectInPG> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            Collection<ALCityObject> list2 = getALCityObjectByPG(pgs);
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
            matchValues = matchValues.stream().filter(cityObject -> cityObject.getCategoryId().equals(criteria.getObjectCategoryId())).collect(Collectors.toList());
        }

        return matchValues;
    }
    public Collection<ALCityObject> getALCityObjectByPG(Collection<ALCityObjectInPG> pgs){
        Collection<ALCityObject> objects=new ArrayList<ALCityObject>();
        Iterator<ALCityObjectInPG> itr = pgs.iterator();
        while(itr.hasNext()){
            ALCityObjectInPG  cityObjectInPG = itr.next();
            ALCityObject object = cityObjectInPG.getAlCityObject();
            objects.add(object);
        }
        return objects;
    }

//    public Collection<ALCityObject>  findObjectsBySearchCriteria(ObjectSearchCriteriaDTO criteria){
//        Collection<ALCityObject> matchValues=null;
//        Optional<ObjectCategory> objectCategoryOptional = objectCategoryRepository.findById(criteria.getObjectCategoryId());
//        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(criteria.getPuzzleGroupdId());
//        matchValues = findALCityObjectByObjectCategoryAndTitle(objectCategoryOptional.get(),criteria.getTitle());
//
//        return matchValues;
//    }
    @Override
    public Optional<ALCityObject> findByIcon(BinaryContent icon) {
        return objectRepository.findByIcon(icon);
    }

    @Override
    public Optional<ALCityObject> findByPic(BinaryContent pic) {
        return objectRepository.findByPic(pic);
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
        objectRepository.deleteById(aLong);
    }

    @Override
    public void delete(ALCityObject object)  {
        try {
            objectRepository.delete(object);
        }catch (Exception e )
        {
            throw new ViolateForeignKeyException(-1, "error", ALCityObject.class.toString(),object.getId());
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
        return objectRepository.findByTitle(title);
    }

    public  Collection<ObjectAction> findAllActions(ALCityObject cityObject){
        Collection<ObjectAction> actions = new ArrayList<ObjectAction>();
        actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(cityObject.getId(), POActionOwnerType.Object);

        return actions;
    }
    @Autowired
    private AppMemberRepository appMemberRepository;


    @Autowired
    private BinaryContentRepository binaryContentRepository;

    public ALCityObject save(CityObjectDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> pic = binaryContentRepository.findById(dto.getPictureId());
        ObjectCategory objectCategory =  objectCategoryRepository.findByValue(dto.getCategory());
        ALCityObject alCityObject=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            alCityObject = new ALCityObject(1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get(),
                    dto.getTitle(), objectCategory,dto.getIs3dObject(),pic.get(),icon.get());
            try {
                objectRepository.save(alCityObject);
            }
            catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + ALCityObject.class , "Error",dto.getId() );
            }
        }else{//edit
            Optional<ALCityObject> alCityObjectOptional= objectRepository.findById(dto.getId());
            if(alCityObjectOptional.isPresent()) {
                alCityObject = alCityObjectOptional.get();
                alCityObject.setObjectCategory(objectCategory);
                alCityObject.setIs3dObject(dto.getIs3dObject());
                alCityObject.setTitle(dto.getTitle());
                alCityObject.setPic(pic.get());
                alCityObject.setIcon(icon.get());
                alCityObject.setVersion(alCityObject.getVersion()+1);
                alCityObject.setCreated(DateUtils.getNow());
                alCityObject.setUpdated(DateUtils.getNow());
                alCityObject.setCreatedBy(createdBy.get());
                alCityObject.setUpdatedBy(createdBy.get());
                objectRepository.save(alCityObject);
            }
        }
        return alCityObject;
    }

}
