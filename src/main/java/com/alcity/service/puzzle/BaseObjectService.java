package com.alcity.service.puzzle;

import com.alcity.dto.search.ObjectSearchCriteriaDTO;
import com.alcity.dto.search.SearchResultCityObjectDTO;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.customexception.RecordNotFoundException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.alobject.ObjectCategoryRepository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.puzzle.BaseObjectRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DateUtils;
import com.alcity.utility.PLDTOUtil;
import com.alcity.utility.ToolBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class BaseObjectService implements BaseObjectRepository {
    @Autowired
    BaseObjectRepository  baseObjectRepository;

    @Autowired
    ActionService puzzleObjectActionService ;
    @Autowired
    ObjectCategoryRepository objectCategoryRepository ;
    @Autowired
    PGObjectService cityObjectInPGService ;
    @Autowired
    PGService pgService ;

    @Override
    public <S extends BaseObject> S save(S entity) {
        return baseObjectRepository.save(entity);
    }

    @Override
    public <S extends BaseObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BaseObject> findById(Long id) {
        if(id==null) return Optional.empty();
        return baseObjectRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<BaseObject> findAll() {
        return baseObjectRepository.findAll();
    }

    @Override
    public Collection<BaseObject> findALCityObjectByObjectCategory(ObjectCategory category) {
        return baseObjectRepository.findALCityObjectByObjectCategory(category);
    }

    @Override
    public Collection<BaseObject> findALCityObjectByTitleIgnoreCaseContains(String criteria) {
        return baseObjectRepository.findALCityObjectByTitleIgnoreCaseContains(criteria);
    }

    @Override
    public Collection<BaseObject> findALCityObjectByObjectCategoryAndTitleIgnoreCase(ObjectCategory category, String title) {
        return baseObjectRepository.findALCityObjectByObjectCategoryAndTitleIgnoreCase(category,title);
    }

//    public Collection<ALCityObject> findALCityObjectByTitleContains(String criteria) {
//        return objectRepository.findALCityObjectByTitleIgnoreCaseContains(criteria);
//    }
//
//    public Collection<ALCityObject> findALCityObjectByObjectCategoryAndTitle(ObjectCategory category, String title) {
//        return objectRepository.findALCityObjectByObjectCategoryAndTitleIgnoreCase(category,title);
//    }
    public Collection<BaseObject> searchCityObjectSByCriteria(ObjectSearchCriteriaDTO criteria) {
        Optional<ObjectCategory> categoryOptional = objectCategoryRepository.findById(criteria.getObjectCategoryId());
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(criteria.getPuzzleGroupdId());
        Collection<BaseObject> matchValues=null;

        if(puzzleGroupOptional.isEmpty() && categoryOptional.isEmpty() && (criteria.getTitle().equals("") || criteria.getTitle()==null))
            matchValues = baseObjectRepository.findAll();

            //000
        else if(puzzleGroupOptional.isEmpty() && categoryOptional.isEmpty() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))) {
            //001
            matchValues = baseObjectRepository.findALCityObjectByTitleIgnoreCaseContains(criteria.getTitle());
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(), criteria.getTitle())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isEmpty() && categoryOptional.isPresent() && (criteria.getTitle().equals("") || criteria.getTitle()==null))
            matchValues = baseObjectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            //010
        else if(puzzleGroupOptional.isEmpty() && categoryOptional.isPresent() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))) {
            //011
            matchValues = baseObjectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //100
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = getALCityObjectByPG(pgs);
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //101
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = getALCityObjectByPG(pgs);
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.title)).collect(Collectors.toList());

        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //110
            Collection<BaseObject> list1 =  baseObjectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            Collection<BaseObject>  list2 = getALCityObjectByPG(pgs);
            matchValues = list1.stream().filter(cityObject ->list2.contains(cityObject)).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //111
            Collection<BaseObject> cityObjects =  baseObjectRepository.findALCityObjectByObjectCategory(categoryOptional.get());
            Collection<BaseObject> list1 = cityObjects.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            Collection<BaseObject> list2 = getALCityObjectByPG(pgs);
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
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = PLDTOUtil.getSearchResultCityObjectsInPGDTOS(pgs);
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isEmpty() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //101
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = PLDTOUtil.getSearchResultCityObjectsInPGDTOS(pgs);
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() == null || criteria.getTitle().equals(""))){
            //110
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            matchValues = PLDTOUtil.getSearchResultCityObjectsInPGDTOS(pgs);
            matchValues = matchValues.stream().filter(cityObject -> cityObject.getCategoryId().equals(criteria.getObjectCategoryId())).collect(Collectors.toList());
        }
        else if(puzzleGroupOptional.isPresent() && categoryOptional.isPresent() && (criteria.getTitle() != null || !criteria.getTitle().equals(""))){
            //111
            Collection<PGObject> pgs =  cityObjectInPGService.findByPuzzleGroup(puzzleGroupOptional.get());
            Collection<BaseObject> list2 = getALCityObjectByPG(pgs);
            matchValues = matchValues.stream().filter(cityObject -> ToolBox.compareCaseInsensitive(cityObject.getTitle(),criteria.getTitle())).collect(Collectors.toList());
            matchValues = matchValues.stream().filter(cityObject -> cityObject.getCategoryId().equals(criteria.getObjectCategoryId())).collect(Collectors.toList());
        }

        return matchValues;
    }
    public Collection<BaseObject> getALCityObjectByPG(Collection<PGObject> pgs){
        Collection<BaseObject> objects=new ArrayList<BaseObject>();
        Iterator<PGObject> itr = pgs.iterator();
        while(itr.hasNext()){
            PGObject cityObjectInPG = itr.next();
            BaseObject object = cityObjectInPG.getAlCityObject();
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
    public Optional<BaseObject> findByIcon(BinaryContent icon) {
        return baseObjectRepository.findByIcon(icon);
    }

    @Override
    public Optional<BaseObject> findByPic(BinaryContent pic) {
        return baseObjectRepository.findByPic(pic);
    }

    @Override
    public Iterable<BaseObject> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        baseObjectRepository.deleteById(aLong);
    }

    @Override
    public void delete(BaseObject object)  {
        try {
            baseObjectRepository.delete(object);
        }catch (Exception e )
        {
            throw new ViolateForeignKeyException(-1, "error", BaseObject.class.toString(),object.getId());
        }

    }



    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BaseObject> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<BaseObject> findByTitle(String title) {
        return baseObjectRepository.findByTitle(title);
    }

    public  Collection<ObjectAction> findAllActions(BaseObject cityObject){
        Collection<ObjectAction> actions = new ArrayList<ObjectAction>();
        actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(cityObject.getId(), POActionOwnerType.Object);

        return actions;
    }
    @Autowired
    private AppMemberRepository appMemberRepository;


    @Autowired
    private BinaryContentRepository binaryContentRepository;

    public BaseObject save(CityObjectDTO dto, String code) throws Exception {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> pic = binaryContentRepository.findById(dto.getPictureId());
        ObjectCategory objectCategory =  objectCategoryRepository.findByValue(dto.getCategory());
        BaseObject baseObject=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            try {
                baseObject = new BaseObject(1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get(),
                    dto.getTitle(), objectCategory,dto.getIs3dObject(),pic.get(),icon.get());
            }
            catch (Exception e) {
                throw new RecordNotFoundException(dto.getId(),"Some Records not found  pic id , icon id" + BaseObject.class , e.getMessage());
            }
        }else{//edit
            Optional<BaseObject> baseObjectOptional= baseObjectRepository.findById(dto.getId());
            if(baseObjectOptional.isPresent()) {
                baseObject = baseObjectOptional.get();
                baseObject.setObjectCategory(objectCategory);
                baseObject.setIs3dObject(dto.getIs3dObject());
                baseObject.setTitle(dto.getTitle());
                baseObject.setPic(pic.get());
                baseObject.setIcon(icon.get());
                baseObject.setVersion(baseObject.getVersion()+1);
                baseObject.setCreated(DateUtils.getNow());
                baseObject.setUpdated(DateUtils.getNow());
                baseObject.setCreatedBy(createdBy.get());
                baseObject.setUpdatedBy(createdBy.get());
            }
        }
        baseObjectRepository.save(baseObject);

        return baseObject;
    }

}
