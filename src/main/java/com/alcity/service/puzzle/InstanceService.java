package com.alcity.service.puzzle;

import com.alcity.dto.plimport.PLCellImport;
import com.alcity.dto.plimport.PLObjectImport;
import com.alcity.dto.plimport.object.InstanceDataImport;
import com.alcity.dto.plimport.object.PositionImport;
import com.alcity.dto.puzzle.CityObjectInPLDTO;
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
import com.alcity.service.customexception.ALCityResponseObject;
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
    InstanceRepository instanceInPLRepository;
    @Autowired
    private AttributeService attributeService;


    @Override
    public <S extends Instance> S save(S entity) {
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

    public Instance save(CityObjectInPLDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelService.findById(dto.getPuzzleLevelId());
        Optional<ALCityObjectInPG> alCityObjectInPGOptional =  objectInPGService.findById(dto.getAlCityObjectInPGId());
        PLCell cell = getCell(puzzleLevelOptional,dto.getRow(),dto.getCol(),dto.getZorder());
        if(puzzleLevelOptional.isEmpty() || alCityObjectInPGOptional.isEmpty()) return null;


        Instance instance=null;
            if (code.equalsIgnoreCase("Save")) { //Save
                instance = new Instance(dto.getName(), dto.getRow(),dto.getCol(),dto.getZorder(),cell,alCityObjectInPGOptional.get(),
                        puzzleLevelOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
                instanceInPLRepository.save(instance);
            }else{//edit
                Optional<Instance> alCityInstanceInPLOptional= instanceInPLRepository.findById(dto.getId());
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
    public Instance copyInstanceByPosition(Instance source , PuzzleLevel target , PLCell cell, Integer x, Integer y, Integer z) {
        //create a copy from instance only
        Instance instanceCopy = new Instance("instance_img_" + x + "_" + y + "_"+ z , x ,y,z,cell,source.getAlCityObjectInPG(),target,
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

    public Collection<PLCell> importCells(Collection<PLCellImport> cellImports , PuzzleLevel importedPL) {
        Collection<PLCell> importedCells = new ArrayList<>();
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");

        Iterator<PLCellImport> iterator = cellImports.iterator();
        while(iterator.hasNext()) {
            PLCellImport cellImport = iterator.next();

            PLCell importedPLCell = new PLCell(1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get(),cellImport.getPosition().getX(),
                    cellImport.getPosition().getY(),cellImport.getPosition().getZ(),importedPL.getPlGrounds().iterator().next());
            plCellService.save(importedPLCell);
            attributeService.importPLCellVariables(cellImport.getVariables(),importedPLCell,AttributeOwnerType.Puzzle_Level_Cell_Variable);
            attributeService.importPLCellVariables(cellImport.getProperties(),importedPLCell,AttributeOwnerType.Puzzle_Level_Cell_Property);


            importedCells.add(importedPLCell);

        }
        return importedCells;
    }

    public Collection<Instance> importObjects(Collection<PLObjectImport> objectImports , PuzzleLevel importedPL) {
        Collection<Instance> importedInstances = new ArrayList<>();
        Iterator<PLObjectImport> iterator = objectImports.iterator();
        while(iterator.hasNext()) {
            PLObjectImport objectImport = iterator.next();
            Optional<ALCityObject> cityObjectOptional = objectService.findById(objectImport.getId());
            Optional<ALCityObjectInPG> alCityObjectInPGOptional = objectInPGService.findByPuzzleGroupAndAlCityObject(importedPL.getPuzzleGroup(),cityObjectOptional.get().getId());
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

        instanceInPLRepository.delete(instance);

    }

    public void deleteInstances(Collection<Instance> instances) {
        Iterator<Instance> iterator = instances.iterator();
        while(iterator.hasNext()) {
            Instance instance = iterator.next();
            deleteAnInstance(instance);
        }
   }
    public Collection<Instance> importInstances(ALCityObjectInPG alCityObjectInPG, Collection<InstanceDataImport> instanceDataImports, Collection<PLCell> cells , PuzzleLevel importedPL) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Collection<Instance> importedInstances = new ArrayList<>();
        Iterator<InstanceDataImport> iterator = instanceDataImports.iterator();
        Instance importedInstance = null;
        while(iterator.hasNext()) {
            InstanceDataImport instanceDataImport = iterator.next();
            PositionImport position = instanceDataImport.getPosition();
//            Collection<PLCell> matchValueOptional_row = cells.stream().filter(cell -> cell.getRow().equals(position.getX())).collect(Collectors.toList());
//            Collection<PLCell> matchValueOptional_col = matchValueOptional_row.stream().filter(cell -> cell.getCol().equals(position.getY())).collect(Collectors.toList());
//            Collection<PLCell> matchValueOptional_zorder = matchValueOptional_col.stream().filter(cell -> cell.getzOrder().equals(position.getZ())).collect(Collectors.toList());
//            Optional<PLCell> cell = matchValueOptional_zorder.stream().findFirst();
             PLCell cell = getPLCellFromGroundByPosition(cells, position.getX(), position.getY(), position.getZ());
            importedInstance = new Instance(instanceDataImport.getName(),position.getX(),position.getY(),position.getZ(),cell,
                    alCityObjectInPG,importedPL,1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
            instanceInPLRepository.save(importedInstance);
            attributeService.importPLInstanceVariables(instanceDataImport.getVariables(),importedInstance,AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
            attributeService.importPLInstanceVariables(instanceDataImport.getProperties(),importedInstance,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

            importedInstances.add(importedInstance);
        }
        return importedInstances;
    }

    public PLCell getPLCellFromGroundByPosition( Collection<PLCell> cells , Integer x,Integer y, Integer z){
        Collection<PLCell> matchValueOptional_row = cells.stream().filter(cell -> cell.getRow().equals(x)).collect(Collectors.toList());
        Collection<PLCell> matchValueOptional_col = matchValueOptional_row.stream().filter(cell -> cell.getCol().equals(y)).collect(Collectors.toList());
        Collection<PLCell> matchValueOptional_zorder = matchValueOptional_col.stream().filter(cell -> cell.getzOrder().equals(z)).collect(Collectors.toList());
        Optional<PLCell> cell = matchValueOptional_zorder.stream().findFirst();
        return cell.get();
    }
    public Collection<Instance> copyInstancesFromSourcePLToTargetPL(PuzzleLevel source , PuzzleLevel target) {
        Collection<Instance> copiedInstances = new ArrayList<>();
        Collection<Instance> sourceInstances = source.getPuzzleGroupObjectInstanceCollection();
        Iterator<Instance> iterator = sourceInstances.iterator();
        Collection<PLGround> plGrounds = source.getPlGrounds();
        PLGround plGround = plGrounds.iterator().next();

        while(iterator.hasNext()) {
            Instance instance = iterator.next();
            PLCell  plCell = getPLCellFromGroundByPosition(plGround.getPlCells(), instance.getRow(), instance.getCol(),instance.getzOrder());
            copyInstanceByPosition(instance,target,plCell,instance.getRow(),instance.getCol(),instance.getzOrder());
        }
        return copiedInstances;
    }

    @Transactional
    public Collection<Instance> copyOneInstanceToOthers(Instance instance, PLGround plGround) {
        ALCityResponseObject responseObject = new ALCityResponseObject();
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Collection<Instance> copiedInstances = new ArrayList<>();
        Integer instanceXPos = instance.getRow();
        Integer instanceYPos = instance.getCol();
        Integer instanceZPos = instance.getzOrder();
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
        return instanceInPLRepository.findById(id);
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
        instanceInPLRepository.deleteById(aLong);
    }

    @Override
    public void delete(Instance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Instance> entities) {
        instanceInPLRepository.deleteAll(entities);
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
    public Collection<Instance> findByzOrder(Integer zOrder) {
        return null;
    }

    @Override
    public Collection<Instance> findByAlCityObjectInPGAndPuzzleLevel(ALCityObjectInPG pgObject, PuzzleLevel pl) {
        return instanceInPLRepository.findByAlCityObjectInPGAndPuzzleLevel(pgObject,pl);
    }


}
