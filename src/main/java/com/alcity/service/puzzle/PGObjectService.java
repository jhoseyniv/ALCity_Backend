package com.alcity.service.puzzle;

import com.alcity.dto.pgimport.PGObjectImportDTO;
import com.alcity.dto.pgimport.PGObjectVariableImportDTO;
import com.alcity.dto.puzzle.PGObjectDTO;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.puzzle.PGObjectRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.RendererService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;


@Service
@Transactional
public class PGObjectService implements PGObjectRepository {

    @Qualifier("PGObjectRepository")
    @Autowired
    PGObjectRepository objectInPGRepository;

    @Autowired
    ActionService puzzleObjectActionService;

    @Autowired
    ActionService objectActionService;

    @Autowired
    @Qualifier("PGRepository")
    PGRepository pgRepository;

    @Lazy
    @Autowired
    BaseObjectService objectService;

    @Autowired
    RendererService actionRendererService;

    @Autowired
    private ActionService actionService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @Override
    public <S extends PGObject> S save(S entity) {
        return objectInPGRepository.save(entity);
    }

    @Override
    public <S extends PGObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PGObject> findById(Long id) {
        if(id == null){ return Optional.empty(); }
        return objectInPGRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PGObject> findAll() {
        return null;
    }

    @Override
    public Iterable<PGObject> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long objInPG) {
        //find  actions defined for this object
        Collection<ObjectAction> actionsInPG = actionService.findByOwnerObjectid(objInPG);
        actionService.deleteAllActions(actionsInPG);

        //find and remove attributes (variables and properties for this object)
        Collection<AttributeValue> attributeValues = attributeValueService.findByOwnerId(objInPG);
        attributeValueService.deleteAll(attributeValues);

        Collection<Attribute> attributes = attributeService.findByOwnerId(objInPG);
        attributeService.deleteAll(attributes);

        objectInPGRepository.deleteById(objInPG);
    }

    @Override
    public void delete(PGObject entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PGObject> entities) {
        objectInPGRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PGObject> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PGObject> findByCode(String code) {
        return objectInPGRepository.findByCode(code);
    }

    @Override
    public Optional<PGObject> findByCodeAndTitle(String code, String title) {
        return objectInPGRepository.findByCodeAndTitle(code,title);
    }

    @Override
    public Collection<PGObject> findByalCityObject(BaseObject cityObject) {
        return objectInPGRepository.findByalCityObject(cityObject);
    }

    @Override
    public Collection<PGObject> findByPuzzleGroup(PuzzleGroup puzzleGroup) {
        return objectInPGRepository.findByPuzzleGroup(puzzleGroup);
    }

    public Optional<PGObject> findByPuzzleGroupAndAlCityObject(PuzzleGroup puzzleGroup, Long cityObjectId) {
        Collection<PGObject> objectInPGS = objectInPGRepository.findByPuzzleGroup(puzzleGroup);
        Optional<PGObject> matchValueOptional = objectInPGS.stream().filter(alCityObjectInPG -> alCityObjectInPG.getAlCityObject().getId()==cityObjectId).findFirst();

        return matchValueOptional;
    }

    @Autowired
    private AppMemberRepository appMemberRepository;

    public PGObject importObjInPG(PGObjectImportDTO dto, PuzzleGroup puzzleGroup) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<BaseObject> alCityObjectOptional = objectService.findById(dto.getObjectId());
        PGObject alCityObjectInPG=null;
        Random random = new Random(); // Create a Random object
        long generatedLong = random.nextLong(); // Generate a random long
        String code= dto.getTitle() + String.valueOf(generatedLong);
        alCityObjectInPG = new PGObject(dto.getTitle(), code,puzzleGroup,alCityObjectOptional.get(),
                1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        objectInPGRepository.save(alCityObjectInPG);
        Collection<PGObjectVariableImportDTO> variableImportDTOS = dto.getVariables();
       // attributeService.importPGObjectVariables(variableImportDTOS,alCityObjectInPG.getId(), AttributeOwnerType.Puzzle_Group_Object_Variable);
        objectActionService.importPGObjectActions(dto.getActions(),alCityObjectInPG.getId());


        return alCityObjectInPG;
    }
        public PGObject save(PGObjectDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleGroup> puzzleGroupOptional =  pgRepository.findByTitle(dto.getPuzzleGroup());
        Optional<BaseObject> alCityObjectOptional =  objectService.findById(dto.getAlCityObjectId());
        PGObject alCityObjectInPG=null;
        if(puzzleGroupOptional.isPresent())
        if (code.equalsIgnoreCase("Save")) { //Save
            alCityObjectInPG = new PGObject(dto.getTitle(), dto.getCode(),puzzleGroupOptional.get(),alCityObjectOptional.get(),
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            objectInPGRepository.save(alCityObjectInPG);
        }else{//edit
            Optional<PGObject> alCityObjectInPGOptional= objectInPGRepository.findById(dto.getId());
            if(alCityObjectInPGOptional.isPresent()) {
                alCityObjectInPG = alCityObjectInPGOptional.get();
                alCityObjectInPG.setCode(dto.getCode());
                alCityObjectInPG.setTitle(dto.getTitle());
                alCityObjectInPG.setVersion(alCityObjectInPG.getVersion()+1);
                alCityObjectInPG.setUpdated(DateUtils.getNow());
                alCityObjectInPG.setUpdatedBy(createdBy.get());
                objectInPGRepository.save(alCityObjectInPG);
            }
        }
        return alCityObjectInPG;
    }


}
