package com.alcity.service.puzzle;

import com.alcity.dto.plimport.PLObjectImport;
import com.alcity.dto.plimport.object.InstanceDataImport;
import com.alcity.dto.plimport.object.PositionImport;
import com.alcity.dto.puzzle.CityObjectInPLDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.InstanceInPLRepository;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.*;
import java.util.function.Function;

@Service
@Transactional
public class InstanceInPLService implements InstanceInPLRepository {

    @Autowired
    InstanceInPLRepository instanceInPLRepository;
    @Autowired
    private AttributeService attributeService;

    @Override
    public <S extends ALCityInstanceInPL> S save(S entity) {
        return instanceInPLRepository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private ObjectInPGService objectInPGService;
    @Autowired
    private ObjectService objectService;


    public ALCityInstanceInPL save(CityObjectInPLDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelService.findById(dto.getPuzzleLevelId());
        Optional<ALCityObjectInPG> alCityObjectInPGOptional =  objectInPGService.findById(dto.getAlCityObjectInPGId());
        if(puzzleLevelOptional.isEmpty() || alCityObjectInPGOptional.isEmpty()) return null;

        ALCityInstanceInPL instance=null;
            if (code.equalsIgnoreCase("Save")) { //Save
                instance = new ALCityInstanceInPL(dto.getName(), dto.getRow(),dto.getCol(),dto.getZorder(),alCityObjectInPGOptional.get(),
                        puzzleLevelOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
                instanceInPLRepository.save(instance);
            }else{//edit
                Optional<ALCityInstanceInPL> alCityInstanceInPLOptional= instanceInPLRepository.findById(dto.getId());
                if(alCityInstanceInPLOptional.isPresent()) {
                    instance = alCityInstanceInPLOptional.get();
                    instance.setName(dto.getName());
                    instance.setRow(dto.getRow());
                    instance.setCol(dto.getCol());
                    instance.setzOrder(dto.getZorder());
                    instance.setAlCityObjectInPG(alCityObjectInPGOptional.get());
                    instance.setPuzzleLevel(puzzleLevelOptional.get());
                    instanceInPLRepository.save(instance);
                }
            }
        return instance;
    }


    @Transactional
    public ALCityInstanceInPL copyInstanceByPosition(ALCityInstanceInPL source ,PuzzleLevel target , Integer x, Integer y,Integer z) {
        //create a copy from instance only
        ALCityInstanceInPL instanceCopy = new ALCityInstanceInPL("instance_img_" + x + "_" + y + "_"+ z , x ,y,z,source.getAlCityObjectInPG(),target,
                1L,DateUtils.getNow(),DateUtils.getNow(),source.getCreatedBy(),source.getUpdatedBy());
        instanceInPLRepository.save(instanceCopy);

        //create a copy from instance variables only
        Collection<Attribute> variables = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(source.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        Collection<Attribute> copiedVariables = attributeService.copyALLAttributesFromInstanceToInstance(variables,source ,instanceCopy,AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);

        //create a copy from instance properties only
        Collection<Attribute> properties = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(source.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        Collection<Attribute> copiedProperties = attributeService.copyALLAttributesFromInstanceToInstance(properties ,source,instanceCopy,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

        return instanceCopy;
    }

    public Collection<ALCityInstanceInPL> importObjects(Collection<PLObjectImport> objectImports , PuzzleLevel importedPL) {
        Collection<ALCityInstanceInPL> importedInstances = new ArrayList<>();
        Iterator<PLObjectImport> iterator = objectImports.iterator();
        while(iterator.hasNext()) {
            PLObjectImport objectImport = iterator.next();
            Optional<ALCityObject> cityObjectOptional = objectService.findById(objectImport.getId());
            Optional<ALCityObjectInPG> alCityObjectInPGOptional = objectInPGService.findByPuzzleGroupAndAlCityObject(importedPL.getPuzzleGroup(),cityObjectOptional.get().getId());

            Collection<ALCityInstanceInPL> instances = importInstances(alCityObjectInPGOptional.get(),objectImport.getInstances(),importedPL);
            importedInstances.addAll(instances);
        }
        return importedInstances;
    }
    public Collection<ALCityInstanceInPL> deleteInstances(Collection<PLObjectImport> objectImports , PuzzleLevel importedPL) {
        Collection<ALCityInstanceInPL> importedInstances = new ArrayList<>();
        Iterator<PLObjectImport> iterator = objectImports.iterator();
        while(iterator.hasNext()) {
            PLObjectImport objectImport = iterator.next();
            Optional<ALCityObject> cityObjectOptional = objectService.findById(objectImport.getId());
            Optional<ALCityObjectInPG> alCityObjectInPGOptional = objectInPGService.findByPuzzleGroupAndAlCityObject(importedPL.getPuzzleGroup(),cityObjectOptional.get().getId());

            Collection<ALCityInstanceInPL> instances = importInstances(alCityObjectInPGOptional.get(),objectImport.getInstances(),importedPL);
            importedInstances.addAll(instances);
        }
        return importedInstances;
    }
    public Collection<ALCityInstanceInPL> importInstances(ALCityObjectInPG alCityObjectInPG,Collection<InstanceDataImport> instanceDataImports , PuzzleLevel importedPL) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Collection<ALCityInstanceInPL> importedInstances = new ArrayList<>();
        Iterator<InstanceDataImport> iterator = instanceDataImports.iterator();
        ALCityInstanceInPL importedInstance = null;
        while(iterator.hasNext()) {
            InstanceDataImport instanceDataImport = iterator.next();
            PositionImport position = instanceDataImport.getPosition();
            importedInstance = new ALCityInstanceInPL(instanceDataImport.getName(),position.getX(),position.getY(),position.getZ(),
                    alCityObjectInPG,importedPL,1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
            instanceInPLRepository.save(importedInstance);
            attributeService.importPLInstanceVariables(instanceDataImport.getVariables(),importedInstance,AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
            attributeService.importPLInstanceVariables(instanceDataImport.getProperties(),importedInstance,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

            importedInstances.add(importedInstance);
        }
        return importedInstances;
    }


    public Collection<ALCityInstanceInPL> copyInstancesFromSourcePLToTargetPL(PuzzleLevel source ,PuzzleLevel target) {
        Collection<ALCityInstanceInPL> copiedInstances = new ArrayList<>();
        Collection<ALCityInstanceInPL> sourceInstances = source.getPuzzleGroupObjectInstanceCollection();
        Iterator<ALCityInstanceInPL> iterator = sourceInstances.iterator();

        while(iterator.hasNext()) {
            ALCityInstanceInPL instance = iterator.next();
            copyInstanceByPosition(instance,target,instance.getRow(),instance.getCol(),instance.getzOrder());
        }
        return copiedInstances;
    }

    @Transactional
    public Collection<ALCityInstanceInPL> copyOneInstanceToOthers(ALCityInstanceInPL instance, PLGround plGround) {
        ALCityResponseObject responseObject = new ALCityResponseObject();
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Collection<ALCityInstanceInPL> copiedInstances = new ArrayList<>();
        Integer instanceXPos = instance.getRow();
        Integer instanceYPos = instance.getCol();
        Integer instanceZPos = instance.getzOrder();
        Integer numOfRows = plGround.getNumRows();
        Integer numOfCols = plGround.getNumColumns();
        for(int height=1;height<=1;height++)
        for(int row=1; row<=numOfRows;row++)
            for(int col=1; col<=numOfCols;col++){
                System.out.println("row=" + row + " col=" + col + "height=" + height);

                if(instanceYPos == col && instanceXPos == row && instanceZPos == height) {
                    //do nothing
                }else {
                    //copy instance to this location
                    ALCityInstanceInPL instanceCopy = copyInstanceByPosition(instance,plGround.getPuzzleLevel(),row,col,height);
                    copiedInstances.add(instanceCopy);
                }
            }
        return copiedInstances;
    }


    @Override
    public Optional<ALCityInstanceInPL> findById(Long id) {
        return instanceInPLRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends ALCityInstanceInPL> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<ALCityInstanceInPL> findAll() {
        return null;
    }

    @Override
    public List<ALCityInstanceInPL> findAllById(Iterable<Long> longs) {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        instanceInPLRepository.deleteById(aLong);
    }

    @Override
    public void delete(ALCityInstanceInPL entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityInstanceInPL> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityInstanceInPL> findByRow(Integer row) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByCol(Integer col) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByzOrder(Integer zOrder) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByAlCityObjectInPGAndPuzzleLevel(ALCityObjectInPG pgObject, PuzzleLevel pl) {
        return instanceInPLRepository.findByAlCityObjectInPGAndPuzzleLevel(pgObject,pl);
    }


}
