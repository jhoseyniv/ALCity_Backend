package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.PuzzleObjectActionService;
import com.alcity.service.puzzle.ALCityInstanceInPLService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Tag(name = "Interpreter APIs", description = "Get Puzzle Level in the Json Format for Other Systems")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/puzz")
public class InterpreterController {

    @Autowired
    private PLGroundService plGroundService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    ALCityInstanceInPLService pgObjectInstanceService;

    @Operation( summary = "Fetch a json ",  description = "fetches all data that need to Interpret a puzzle level structure and rules")
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
             puzzleLevelData.setName(pl.getTitle());

             Collection<RecordData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

             Collection<PLObjectiveData> plObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
             puzzleLevelData.setObjectives(plObjectiveDataCollection);
             PuzzleGroup pg = pl.getPuzzleGroup();
             Collection<POData> objects = getObjectsForPuzzleGroup(pg,pl);
             Collection<RuleData> rules = DTOUtil.getRulesForPuzzleLevel(pl,attributeService);

             puzzleLevelData.setCols(plGround.getNumColumns());
             puzzleLevelData.setRows(plGround.getNumRows());
             puzzleLevelData.setVariables(variables);
             puzzleLevelData.setObjects(objects);
             puzzleLevelData.setRules(rules);
         }
        return puzzleLevelData;
    }


    public Collection<InstanceData> getInstancesForAObjectInPuzzleLevel(ALCityObjectInPG pgpo, PuzzleLevel  pl) {
        Collection<InstanceData> objectInstanceDataCollection = new ArrayList<InstanceData>();
        Collection<ALCityInstanceInPL> InstanceCollection = pgObjectInstanceService.findByAlCityObjectInPGAndPuzzleLevel(pgpo,pl);
        //Collection<ALCityInstanceInPL> puzzleGroupObjectInstanceCollection = pgpo.getAlCityInstanceInPLCollection();
        Iterator<ALCityInstanceInPL> iterator = InstanceCollection.iterator();

        while(iterator.hasNext()) {
            ALCityInstanceInPL puzzleGroupObjectInstance = iterator.next();

            InstanceData objectInstanceData = new InstanceData();
            objectInstanceData.setId(puzzleGroupObjectInstance.getId());
            objectInstanceData.setName(puzzleGroupObjectInstance.getName());
            Position instancePostion = new Position(puzzleGroupObjectInstance.getRow() , puzzleGroupObjectInstance.getCol(),puzzleGroupObjectInstance.getzOrder());
            objectInstanceData.setPosition(instancePostion);

            Collection<RecordData> properties = DTOUtil.getAttributeForOwnerById(attributeService,puzzleGroupObjectInstance.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);
            Collection<RecordData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,puzzleGroupObjectInstance.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);

            objectInstanceData.setProperties(properties);
            objectInstanceData.setVariables(variables);

            objectInstanceDataCollection.add(objectInstanceData);
        }

        return objectInstanceDataCollection;
     }

   public  POData getObjectData(ALCityObjectInPG alCityObjectInPG, PuzzleLevel pl){
       POData poData = new POData();

       poData.setId(alCityObjectInPG.getId());
       poData.setTitle(alCityObjectInPG.getTitle());
       poData.setCode(alCityObjectInPG.getCode());

       BinaryContent picture = alCityObjectInPG.getAlCityObject().getPic();
       poData.setImageGraphicId(picture.getId());

       BinaryContent icon = alCityObjectInPG.getAlCityObject().getIcon();
       poData.setIconGraphicId(icon.getId());

       Collection<ActionData> actions = getActionsDTOForALCityObjectInPG(alCityObjectInPG);
       poData.setActions(actions);

       Collection<RecordData> variables = DTOUtil.getAttributeForOwnerById(attributeService,alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);

       poData.setVariables(variables);

       Collection<RecordData> properties = DTOUtil.getAttributeForOwnerById(attributeService,alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
       poData.setProperties(properties);

       Collection<InstanceData> instances = getInstancesForAObjectInPuzzleLevel(alCityObjectInPG,pl);
       poData.setInstances(instances);

       poData.setVersion(alCityObjectInPG.getVersion());

       return  poData;
   }

    public Collection<POData> getObjectsForPuzzleGroup(PuzzleGroup pg, PuzzleLevel pl) {
        Collection<POData> pObjectsData = new ArrayList<POData>();

        Collection<ALCityObjectInPG> objects = new ArrayList<>();
        objects = pg.getAlCityObjectInPGS();
        Iterator<ALCityObjectInPG> iterator = objects.iterator();
        while(iterator.hasNext()) {
            ALCityObjectInPG object = iterator.next();
            POData poData =getObjectData(object,pl);
            pObjectsData.add(poData);
        }
        return pObjectsData;
    }

    @Autowired
    PuzzleObjectActionService puzzleObjectActionService;
    public Collection<ActionData> getActionsDTOForALCityObjectInPG(ALCityObjectInPG alCityObjectInPG){
        Collection<ActionData> actionDataCollection = new ArrayList<ActionData>();
        Collection<ObjectAction> actionsCollection = new ArrayList<ObjectAction>();
        actionsCollection = puzzleObjectActionService.findActionsForALCityObjectInPG(alCityObjectInPG);

        Iterator<ObjectAction> iterator = actionsCollection.iterator();
        while(iterator.hasNext()) {
            ObjectAction puzzleObjectAction = iterator.next();
            ObjectActionType objectAction = puzzleObjectAction.getObjectAction();

            Collection<RecordData> parametersData = DTOUtil.getAttributeForOwnerById(attributeService,puzzleObjectAction.getId(),AttributeOwnerType.ALCity_Object_In_Puzzle_Group);

            ActionData objectActionData = new ActionData();
            objectActionData.setActionName(objectAction);
            objectActionData.setId(puzzleObjectAction.getId());
            objectActionData.setHandler(puzzleObjectAction.getActionRenderer().getHandler());
            objectActionData.setParameters(parametersData);

            actionDataCollection.add(objectActionData);
        }

        return actionDataCollection;
    }

}
