package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectAction;
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
             puzzleLevelData.setName(pl.getName());

             Collection<RecordrData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

             Collection<PLObjectiveData> plObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
             puzzleLevelData.setObjectives(plObjectiveDataCollection);
             PuzzleGroup pg = pl.getPuzzleGroup();
             Collection<PGData> objects = getObjectsForPuzzleGroup(pg,pl);
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

            Collection<RecordrData> properties = DTOUtil.getAttributeForOwnerById(attributeService,puzzleGroupObjectInstance.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property);
            Collection<RecordrData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,puzzleGroupObjectInstance.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable);

            objectInstanceData.setProperties(properties);
            objectInstanceData.setVariables(variables);

            objectInstanceDataCollection.add(objectInstanceData);
        }

        return objectInstanceDataCollection;
     }


        public Collection<PGData> getObjectsForPuzzleGroup(PuzzleGroup pg,PuzzleLevel pl) {
        Collection<PGData> puzzleGroupObjectDataCollection = new ArrayList<PGData>();

        Collection<ALCityObjectInPG> alCityObjectInPGCollection = new ArrayList<>();
            alCityObjectInPGCollection = pg.getPuzzleGroup_puzzleObjectCollection();
        Iterator<ALCityObjectInPG> iterator = alCityObjectInPGCollection.iterator();
        while(iterator.hasNext()) {
            ALCityObjectInPG alCityObjectInPG = iterator.next();
            PGData puzzleGroupObjectData = new PGData();

            puzzleGroupObjectData.setId(alCityObjectInPG.getAlCityObject().getId());
            puzzleGroupObjectData.setTitle(alCityObjectInPG.getTitle());
            puzzleGroupObjectData.setCode(alCityObjectInPG.getCode());
            puzzleGroupObjectData.setVersion(alCityObjectInPG.getVersion());

            BinaryContent picture = alCityObjectInPG.getAlCityObject().getPicture();
            puzzleGroupObjectData.setImageGraphicId(picture.getId());

            BinaryContent icon = alCityObjectInPG.getAlCityObject().getIcon();
            puzzleGroupObjectData.setIconGraphicId(icon.getId());

            Collection<ActionData> actions = getActionsForAPuzzleObjectById(alCityObjectInPG);
            puzzleGroupObjectData.setActions(actions);

            Collection<RecordrData> variables = DTOUtil.getAttributeForOwnerById(attributeService,alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);

            puzzleGroupObjectData.setVariables(variables);

            Collection<RecordrData> properties = DTOUtil.getAttributeForOwnerById(attributeService,alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
            puzzleGroupObjectData.setProperties(properties);

            Collection<InstanceData> instances = getInstancesForAObjectInPuzzleLevel(alCityObjectInPG,pl);
            puzzleGroupObjectData.setInstances(instances);
            puzzleGroupObjectDataCollection.add(puzzleGroupObjectData);

        }
        return puzzleGroupObjectDataCollection;
    }

    @Autowired
    PuzzleObjectActionService puzzleObjectActionService;
    public Collection<ActionData> getActionsForAPuzzleObjectById(ALCityObjectInPG alCityObjectInPG){
        Collection<ActionData> objectActionDataCollection = new ArrayList<ActionData>();
        Collection<PuzzleObjectAction> puzzleObjectActionCollection = new ArrayList<PuzzleObjectAction>();
        puzzleObjectActionCollection = puzzleObjectActionService.findActions(alCityObjectInPG);

        Iterator<PuzzleObjectAction> iterator = puzzleObjectActionCollection.iterator();
        while(iterator.hasNext()) {
            PuzzleObjectAction puzzleObject_objectAction = iterator.next();
            ObjectAction objectAction = puzzleObject_objectAction.getObjectAction();

            Collection<RecordrData> parametersData = DTOUtil.getAttributeForOwnerById(attributeService,puzzleObject_objectAction.getActionRenderer().getId(),AttributeOwnerType.Action_Renderer_Parameter);

            ActionData objectActionData = new ActionData();
            objectActionData.setActionName(objectAction);
            objectActionData.setId(objectAction.ordinal());
            objectActionData.setHandler(puzzleObject_objectAction.getActionRenderer().getHandler());
            objectActionData.setParameters(parametersData);

            objectActionDataCollection.add(objectActionData);
        }

        return objectActionDataCollection;
    }

}
