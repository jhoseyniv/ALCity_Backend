package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.PuzzleObject_ObjectActionService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/puzz")
public class IntrpreterController {

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @Autowired
    private PLGroundService plGroundService;


    @Autowired
    AttributeService attributeService;


    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLData getPuzzleLevelForInterpreter(@PathVariable Long id) {
         PLData puzzleLevelData= new PLData();
         Optional<PLGround> puzzleLevelGroundOptional = plGroundService.findByPuzzleLevelId(id);
        PLGround plGround = new PLGround();
         if(puzzleLevelGroundOptional.isPresent()){
             plGround = puzzleLevelGroundOptional.get();

             puzzleLevelData.setBoardGraphicId(plGround.getBoardGraphic().getId());
             CameraSetup cameraSetup =  plGround.getCameraSetup();
             Position Position = new Position(cameraSetup.getxPosition(),cameraSetup.getyPosition(), cameraSetup.getzPosition());
             Position Rotation = new Position(cameraSetup.getxRotation(),cameraSetup.getyRotation(),cameraSetup.getzRotation());
             CameraSetupData cameraSetupInterpreter = new CameraSetupData(Position,Rotation);
             puzzleLevelData.setCameraSetup(cameraSetupInterpreter);

             PuzzleLevel pl = plGround.getPuzzleLevel();
             puzzleLevelData.setCode(pl.getCode());
             puzzleLevelData.setName(pl.getName());

             Collection<RecordrData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

             Collection<PLObjectiveData> puzzleLevelObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
             puzzleLevelData.setObjectives(puzzleLevelObjectiveDataCollection);
             PuzzleGroup pg = pl.getPuzzleGroup();
             Collection<PGData> objects = getObjectsForPuzzleGroup(pg);
             Collection<RuleData> rules = DTOUtil.getRulesForPuzzleLevel(pl,attributeService);

             puzzleLevelData.setCols(plGround.getNumColumns());
             puzzleLevelData.setRows(plGround.getNumRows());
             puzzleLevelData.setVariables(variables);
             puzzleLevelData.setObjects(objects);
             puzzleLevelData.setRules(rules);
         }

        return puzzleLevelData;
    }





    public Collection<InstanceData> getInstancesForAObjectInPuzzleLevel(PuzzleGroup_PuzzleObject pgpo) {
        Collection<InstanceData> objectInstanceDataCollection = new ArrayList<InstanceData>();
        Collection<PGObjectInstance> puzzleGroupObjectInstanceCollection = pgpo.getPuzzleGroupObjectInstanceCollection();
        Iterator<PGObjectInstance> iterator = puzzleGroupObjectInstanceCollection.iterator();

        while(iterator.hasNext()) {
            PGObjectInstance puzzleGroupObjectInstance = iterator.next();

            InstanceData objectInstanceData = new InstanceData();
            objectInstanceData.setId(puzzleGroupObjectInstance.getId());
            objectInstanceData.setName(puzzleGroupObjectInstance.getName());
            Position instancePostion = new Position(puzzleGroupObjectInstance.getRow() , puzzleGroupObjectInstance.getCol(),puzzleGroupObjectInstance.getzOrder());
            objectInstanceData.setPosition(instancePostion);

            Collection<RecordrData> properties = DTOUtil.getAttributeForOwnerById(attributeService,puzzleGroupObjectInstance.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property);
            Collection<RecordrData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,puzzleGroupObjectInstance.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable);

            objectInstanceData.setProperties(properties);
            objectInstanceData.setVariables(variables);

            objectInstanceDataCollection.add(objectInstanceData);
        }

        return objectInstanceDataCollection;
     }


        public Collection<PGData> getObjectsForPuzzleGroup(PuzzleGroup pg) {
        Collection<PGData> puzzleGroupObjectDataCollection = new ArrayList<PGData>();

        Collection<PuzzleGroup_PuzzleObject> puzzleGroup_puzzleObjectCollection = new ArrayList<>();
        puzzleGroup_puzzleObjectCollection = pg.getPuzzleGroup_puzzleObjectCollection();
        Iterator<PuzzleGroup_PuzzleObject> iterator = puzzleGroup_puzzleObjectCollection.iterator();
        while(iterator.hasNext()) {
            PuzzleGroup_PuzzleObject puzzleGroup_puzzleObject = iterator.next();
            PGData puzzleGroupObjectData = new PGData();

            puzzleGroupObjectData.setId(puzzleGroup_puzzleObject.getPuzzleObject().getId());
            puzzleGroupObjectData.setTitle(puzzleGroup_puzzleObject.getTitle());
            puzzleGroupObjectData.setCode(puzzleGroup_puzzleObject.getCode());
            puzzleGroupObjectData.setVersion(puzzleGroup_puzzleObject.getVersion());

            BinaryContent picture = puzzleGroup_puzzleObject.getPuzzleObject().getPicture();
            puzzleGroupObjectData.setImageGraphicId(picture.getId());

            BinaryContent icon = puzzleGroup_puzzleObject.getPuzzleObject().getIcon();
            puzzleGroupObjectData.setIconGraphicId(icon.getId());

            Collection<ActionData> actions = getActionsForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setActions(actions);

            Collection<RecordrData> variables = getVariableForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setVariables(variables);

            Collection<RecordrData> properties = getPropertiesForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setVariables(properties);

            Collection<InstanceData> instances = getInstancesForAObjectInPuzzleLevel(puzzleGroup_puzzleObject);
            puzzleGroupObjectData.setInstances(instances);


            puzzleGroupObjectDataCollection.add(puzzleGroupObjectData);

        }
        return puzzleGroupObjectDataCollection;
    }

    public Collection<RecordrData>  getPropertiesForAPuzzleObjectById(Long puzzleGroup_puzzleObject_id){
        Collection<RecordrData> properties = new ArrayList<RecordrData>();

        return properties;
    }
    public Collection<RecordrData>  getVariableForAPuzzleObjectById(Long puzzleGroup_puzzleObject_id){
        Collection<RecordrData> variables = new ArrayList<RecordrData>();

        return variables;
    }

    @Autowired
    PuzzleObject_ObjectActionService puzzleObject_objectActionService;
    public Collection<ActionData> getActionsForAPuzzleObjectById(Long pgoId){
        Collection<ActionData> objectActionDataCollection = new ArrayList<ActionData>();
        Collection<PuzzleObject_ObjectAction> puzzleObject_objectActionCollection = new ArrayList<PuzzleObject_ObjectAction>();
        puzzleObject_objectActionCollection = puzzleObject_objectActionService.findByOwnerObjectid(pgoId);

        Iterator<PuzzleObject_ObjectAction> iterator = puzzleObject_objectActionCollection.iterator();
        while(iterator.hasNext()) {
            PuzzleObject_ObjectAction puzzleObject_objectAction = iterator.next();
            ObjectAction objectAction = puzzleObject_objectAction.getObjectAction();
           // POActionOwnerType puzzleObjectActionOwnerType = puzzleObject_objectAction.getPoActionOwnerType();

            Collection<RecordrData> parametersData = DTOUtil.getAttributeForOwnerById(attributeService,puzzleObject_objectAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter);
            System.out.println("------------------" + puzzleObject_objectAction.getId() +" ----------" +parametersData.size());

            ActionData objectActionData = new ActionData();
            objectActionData.setActionName(objectAction);
            objectActionData.setId(objectAction.ordinal());
            objectActionData.setHandler(puzzleObject_objectAction.getActionRenderer().getHandler());
            objectActionData.setParameters(parametersData);

            objectActionDataCollection.add(objectActionData);
        }

        return objectActionDataCollection;
    }


    public Collection<RecordrData> getPropertiesForAOwnerObject(Long ownerId, POActionOwnerType ownerType){

        Collection<RecordrData> objectActionParameterDataCollection = new ArrayList<RecordrData>();
        Collection<Attribute> attributeCollection = new ArrayList<Attribute>();
        attributeCollection = attributeService.findByOwnerId(ownerId);
        Iterator<Attribute> iterator = attributeCollection.iterator();
        while(iterator.hasNext()) {
            Attribute alCityAttribute = iterator.next();
            Collection<AttributeValue> attributeValueCollection = alCityAttribute.getAttributeValueSet();
            Iterator<AttributeValue> attributeValueIterator = attributeValueCollection.iterator();
            AttributeValue alCityAttributeValue = attributeValueIterator.next();

            RecordrData objectActionParameterData = new RecordrData();
            objectActionParameterData.setName(alCityAttribute.getName());
            objectActionParameterData.setType(alCityAttribute.getDataType().getValue());

            objectActionParameterData.setValue(DTOUtil.getDataValue(alCityAttributeValue));
            objectActionParameterDataCollection.add(objectActionParameterData);
        }

        return objectActionParameterDataCollection;
    }


}
