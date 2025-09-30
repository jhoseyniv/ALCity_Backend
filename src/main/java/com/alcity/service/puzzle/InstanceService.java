package com.alcity.service.puzzle;

import com.alcity.dto.plimpexport.*;
import com.alcity.dto.puzzle.InstanceDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.InstanceRepository;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.customexception.ResponseObject;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstanceService implements InstanceRepository {

    @Autowired
    InstanceRepository instanceRepository;
    @Autowired
    private AttributeService attributeService;


    @Override
    public <S extends Instance> S save(S entity) {
        return instanceRepository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private PGObjectService objectInPGService;
    @Autowired
    private BaseObjectService objectService;

    @Autowired
    ActionService actionService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private PLCellService plCellService;


    public PLCell getCell(Optional<PuzzleLevel> puzzleLevelOptional,Integer x,Integer y ,Integer z) {
        PLGround plGround = puzzleLevelOptional.get().getPlGrounds().iterator().next();
        Collection<PLCell> plCells=plGround.getPlCells();
        PLCell cell =  getPLCellFromGroundByPosition(plCells,x,y,z);
         return cell;
    }

    public Instance save(InstanceDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelService.findById(dto.getPuzzleLevelId());
        Optional<PGObject> pgObjectOptional =  objectInPGService.findById(dto.getPGObjectId());
        PLCell cell = getCell(puzzleLevelOptional,dto.getRow(),dto.getCol(),dto.getzOrder());
        if(puzzleLevelOptional.isEmpty() || pgObjectOptional.isEmpty()) return null;


        Instance instance=null;
            if (code.equalsIgnoreCase("Save")) { //Save
                instance = new Instance(dto.getName(), dto.getRow(),dto.getCol(),dto.getzOrder(),cell,pgObjectOptional.get(),
                        puzzleLevelOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
                instanceRepository.save(instance);
            }else{//edit
                Optional<Instance> alCityInstanceInPLOptional= instanceRepository.findById(dto.getId());
                if(alCityInstanceInPLOptional.isPresent()) {
                    instance = alCityInstanceInPLOptional.get();
                    instance.setName(dto.getName());
                    instance.setRow(dto.getRow());
                    instance.setCol(dto.getCol());
                    instance.setZorder(dto.getzOrder());
                    instance.setAlCityObjectInPG(pgObjectOptional.get());
                    instance.setPuzzleLevel(puzzleLevelOptional.get());
                    instanceRepository.save(instance);
                }
            }
        return instance;
    }


    @Transactional
    public Instance copyInstanceByPosition(Instance source , PuzzleLevel target , PLCell cell, Integer x, Integer y, Integer z) {
        //create a copy from instance only
        Instance instanceCopy = new Instance("instance_img_" + x + "_" + y + "_"+ z , x ,y,z,cell,source.getAlCityObjectInPG(),target,
                1L,DateUtils.getNow(),DateUtils.getNow(),source.getCreatedBy(),source.getUpdatedBy());
        instanceRepository.save(instanceCopy);

        //create a copy from instance variables only
        Collection<Attribute> variables = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(source.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        Collection<Attribute> copiedVariables = attributeService.copyALLAttributesFromInstanceToInstance(variables,source ,instanceCopy,AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);

        //create a copy from instance properties only
        Collection<Attribute> properties = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(source.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        Collection<Attribute> copiedProperties = attributeService.copyALLAttributesFromInstanceToInstance(properties ,source,instanceCopy,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

        return instanceCopy;
    }

    public Collection<PLCell> importCells(Collection<PLCellImport> cellImports , PuzzleLevel importedPL) {
        Collection<PLCell> importedCells = new ArrayList<>();
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");

        Iterator<PLCellImport> iterator = cellImports.iterator();
        while(iterator.hasNext()) {
            PLCellImport cellImport = iterator.next();

            PLCell importedPLCell = new PLCell(1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get(),cellImport.getPosition().getX().intValue(),
                    cellImport.getPosition().getY().intValue(),cellImport.getPosition().getZ().intValue(),importedPL.getPlGrounds().iterator().next());
            plCellService.save(importedPLCell);
            attributeService.importPLCellVariables(cellImport.getVariables(),importedPLCell,AttributeOwnerType.Puzzle_Level_Cell_Variable);
            attributeService.importPLCellVariables(cellImport.getProperties(),importedPLCell,AttributeOwnerType.Puzzle_Level_Cell_Property);


            importedCells.add(importedPLCell);

        }
        return importedCells;
    }
    public Collection<Attribute> importObjectAttributes(Collection<AttributeData> attributes,Long ownerId, AttributeOwnerType ownerType) {
        Collection<Attribute> importedProperties = new ArrayList<>();
        Iterator<AttributeData> iterator = attributes.iterator();
        while(iterator.hasNext()) {
            AttributeData attributeData = iterator.next();
           Attribute attribute = attributeService.importVariables_New(attributeData,ownerId,ownerType);
            importedProperties.add(attribute);
        }
        return  importedProperties;
    }

    public static Collection<AttributeData> getMergedProperties(Collection<AttributeData> objectProperties, Collection<AttributeData> instanceProperties) {
        Iterator<AttributeData> iteratorOne = objectProperties.iterator();
        Iterator<AttributeData> iteratorTwo = instanceProperties.iterator();

        while(iteratorOne.hasNext()) {
            AttributeData one = iteratorOne.next();
            Collection<AttributeData> find =   instanceProperties.stream().filter(attributeData -> attributeData.getName().equals(one.getName())).collect(Collectors.toList());
            if(find.isEmpty()) {

                instanceProperties.add(one);
            }
        }
        return  instanceProperties;
    }

    public Collection<InstanceData> processInstanceData(PGObjectData objectData ) {
        Collection<InstanceData> instances =objectData.getInstances();
        Collection<AttributeData> objectProperties = objectData.getProperties();
        Iterator<InstanceData> iterator = instances.iterator();
        while(iterator.hasNext()) {
            InstanceData instance = iterator.next();
            Collection<AttributeData> instanceProperties = instance.getProperties();
            Collection<AttributeData> mergedProperties = new ArrayList<>();
            mergedProperties =  getMergedProperties(objectProperties,instanceProperties);
            instance.setProperties(mergedProperties);
        }
        return instances;
    }

    public Collection<Instance> importObjects(Collection<PGObjectData> objectImports , PuzzleLevel importedPL) {
        Collection<Instance> importedInstances = new ArrayList<>();
        Iterator<PGObjectData> iterator = objectImports.iterator();
        while(iterator.hasNext()) {
            PGObjectData objectImport = iterator.next();
            Optional<BaseObject> cityObjectOptional = objectService.findById(objectImport.getId());
            Optional<PGObject> alCityObjectInPGOptional = objectInPGService.findByPuzzleGroupAndAlCityObject(importedPL.getPuzzleGroup(),cityObjectOptional.get().getId());
            PLGround  plGround = importedPL.getPlGrounds().iterator().next();
            Collection<PLCell> cells = plGround.getPlCells();

            //Collection<InstanceData> instanceData = processInstanceData(objectImport);
            //Collection<Attribute> properties = importObjectAttributes(objectImport.getProperties(),alCityObjectInPGOptional.get().getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
            //Collection<Attribute> variables = importObjectAttributes(objectImport.getVariables(),alCityObjectInPGOptional.get().getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
            Collection<Instance> instances = importInstances(alCityObjectInPGOptional.get(),objectImport.getInstances(),cells,importedPL);
            importedInstances.addAll(instances);
        }
        return importedInstances;
    }
    public Collection<Instance> importObjects_New(Collection<PGObjectData> objectImports , PuzzleLevel importedPL) {
        Collection<Instance> importedInstances = new ArrayList<>();
        Iterator<PGObjectData> iterator = objectImports.iterator();
        while(iterator.hasNext()) {
            PGObjectData objectImport = iterator.next();
            Optional<BaseObject> cityObjectOptional = objectService.findById(objectImport.getId());
            Optional<PGObject> alCityObjectInPGOptional = objectInPGService.findByPuzzleGroupAndAlCityObject(importedPL.getPuzzleGroup(),cityObjectOptional.get().getId());
            PLGround  plGround = importedPL.getPlGrounds().iterator().next();
            Collection<PLCell> cells = plGround.getPlCells();
            Collection<Instance> instances = importInstances(alCityObjectInPGOptional.get(),objectImport.getInstances(),cells,importedPL);
            importedInstances.addAll(instances);
        }
        return importedInstances;
    }
    public void deleteAnInstance(Instance instance) {

        Collection<ObjectAction> actions = actionService.findByOwnerObjectidAndPoActionOwnerType(instance.getId(), POActionOwnerType.Puzzle_Level_Instance);
        actionService.deleteAll(actions);

        //delete variables and properties for an instance
        Collection<AttributeValue>  attributeValues= attributeValueService.findByOwnerId(instance.getId());
        attributeValueService.deleteAll(attributeValues);

        Collection<Attribute> attributes = attributeService.findByOwnerId(instance.getId());
        attributeService.deleteAll(attributes);

        instanceRepository.delete(instance);

    }

    public void deleteInstances(Collection<Instance> instances) {
        Iterator<Instance> iterator = instances.iterator();
        while(iterator.hasNext()) {
            Instance instance = iterator.next();
            deleteAnInstance(instance);
        }
   }
    public Collection<Instance> importInstances(PGObject alCityObjectInPG, Collection<InstanceData> instanceDataImports, Collection<PLCell> cells , PuzzleLevel importedPL) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Collection<Instance> importedInstances = new ArrayList<>();
        Iterator<InstanceData> iterator = instanceDataImports.iterator();
        Instance importedInstance = null;
        while(iterator.hasNext()) {
            InstanceData instanceDataImport = iterator.next();
            PostionIntDTO position = instanceDataImport.getPosition();

            PLCell cell = getPLCellFromGroundByPosition(cells, position.getX(), position.getY(), position.getZ());

            importedInstance = new Instance(instanceDataImport.getName(),position.getX(),position.getY(),position.getZ(),cell,
                    alCityObjectInPG,importedPL,1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
            instanceRepository.save(importedInstance);

            //load properties for pg object
            Collection<Attribute> pgObjectProperties = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
            Collection<AttributeData> instanceImportProperties = instanceDataImport.getProperties();

            //compare and import properties for base object and instance object that defined in import file
            attributeService.importPGObjectProperties_New(pgObjectProperties ,instanceImportProperties,importedInstance.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

            //load variables for pg object
            Collection<Attribute> pgObjectVariables = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
            Collection<AttributeData> instanceImportVariables = instanceDataImport.getVariables();

            //compare and import variables for base object and instance object that defined in import file
            attributeService.importPGObjectProperties_New(pgObjectVariables ,instanceImportVariables,importedInstance.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);

            importedInstances.add(importedInstance);
        }
        return importedInstances;
    }

    public PLCell getPLCellFromGroundByPosition( Collection<PLCell> cells , Integer x,Integer y, Integer z){
        Collection<PLCell> matchValueOptional_row = cells.stream().filter(cell -> cell.getRow().equals(x)).collect(Collectors.toList());
        Collection<PLCell> matchValueOptional_col = matchValueOptional_row.stream().filter(cell -> cell.getCol().equals(y)).collect(Collectors.toList());
       // Collection<PLCell> matchValueOptional_zorder = matchValueOptional_col.stream().filter(cell -> cell.getzOrder().equals(z)).collect(Collectors.toList());
        Optional<PLCell> cell = matchValueOptional_col.stream().findFirst();
        return cell.get();
    }
    public Collection<Instance> copyInstancesFromSourcePLToTargetPL(PuzzleLevel source , PuzzleLevel target) {
        Collection<Instance> copiedInstances = new ArrayList<>();
        Collection<Instance> sourceInstances = source.getInstances();
        Iterator<Instance> iterator = sourceInstances.iterator();
        Collection<PLGround> plGrounds = source.getPlGrounds();
        PLGround plGround = plGrounds.iterator().next();

        while(iterator.hasNext()) {
            Instance instance = iterator.next();
            PLCell  plCell = getPLCellFromGroundByPosition(plGround.getPlCells(), instance.getRow(), instance.getCol(),instance.getZorder());
            copyInstanceByPosition(instance,target,plCell,instance.getRow(),instance.getCol(),instance.getZorder());
        }
        return copiedInstances;
    }

    @Transactional
    public Collection<Instance> copyOneInstanceToOthers(Instance instance, PLGround plGround) {
        ResponseObject responseObject = new ResponseObject();
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Collection<Instance> copiedInstances = new ArrayList<>();
        Integer instanceXPos = instance.getRow();
        Integer instanceYPos = instance.getCol();
        Integer instanceZPos = instance.getZorder();
        Integer numOfRows = plGround.getNumRows();
        Integer numOfCols = plGround.getNumColumns();
        Collection<PLCell> cells = plGround.getPlCells();
        for(int height=1;height<=1;height++)
        for(int row=1; row<=numOfRows;row++)
            for(int col=1; col<=numOfCols;col++){
                System.out.println("row=" + row + " col=" + col + "height=" + height);

                if(instanceYPos == col && instanceXPos == row && instanceZPos == height) {
                    //do nothing
                }else {
                    //copy instance to this location
                    PLCell plCell = getPLCellFromGroundByPosition(cells,row,col,height);
                    Instance instanceCopy = copyInstanceByPosition(instance,plGround.getPuzzleLevel(),plCell,row,col,height);
                    copiedInstances.add(instanceCopy);
                }
            }
        return copiedInstances;
    }


    @Override
    public Optional<Instance> findById(Long id) {
        if(id==null || id<=0L) return Optional.empty();
        return instanceRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Instance> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Instance> findAll() {
        return null;
    }

    @Override
    public List<Instance> findAllById(Iterable<Long> longs) {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        instanceRepository.deleteById(aLong);
    }

    @Override
    public void delete(Instance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Instance> entities) {
        instanceRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<Instance> findByRow(Integer row) {
        return null;
    }

    @Override
    public Collection<Instance> findByCol(Integer col) {
        return null;
    }

    @Override
    public Collection<Instance> findByZorder(Integer zorder) {
        return instanceRepository.findByZorder(zorder);
    }

    @Override
    public Collection<Instance> findByAlCityObjectInPGAndPuzzleLevel(PGObject pgObject, PuzzleLevel pl) {
        return instanceRepository.findByAlCityObjectInPGAndPuzzleLevel(pgObject,pl);
    }


}
