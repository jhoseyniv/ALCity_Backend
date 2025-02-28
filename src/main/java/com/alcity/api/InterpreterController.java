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
import com.alcity.service.alobject.ActionService;
import com.alcity.service.puzzle.ALCityInstanceInPLService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    PuzzleLevelService puzzleLevelService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    ALCityInstanceInPLService pgObjectInstanceService;

    @Operation( summary = "Fetch a json ",  description = "fetches all data that need to Interpret a puzzle level structure and rules")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLData getPuzzleLevelForInterpreter(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            if(puzzleLevel.getInterpreterFile()!=null)
                plData = PLDTOUtil.getInterpreterJSON(puzzleLevel.getInterpreterFile());
            else {
                plData = getJsonFile(id);
            }
        }
        return plData;
    }

    public  PLData getJsonFile(Long puzzleLevelId) {
        PLData puzzleLevelData= new PLData();

        Optional<PLGround> puzzleLevelGroundOptional = plGroundService.findByPuzzleLevelId(puzzleLevelId);
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
            byte[] bytes = convertObjectToBytes(puzzleLevelData);
            pl.setInterpreterFile(bytes);
            puzzleLevelService.save(pl);
        }
        return puzzleLevelData;

    }

    public static byte[] convertObjectToBytes(PLData obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            //Byte[] byteObject = ArrayUtils.toObject();

            return boas.toByteArray();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

    public Collection<InstanceData> getInstancesForAObjectInPuzzleLevel(ALCityObjectInPG pgpo, PuzzleLevel  pl) {
        Collection<InstanceData> objectInstanceDataCollection = new ArrayList<InstanceData>();
        Collection<ALCityInstanceInPL> InstanceCollection = pgObjectInstanceService.findByAlCityObjectInPGAndPuzzleLevel(pgpo,pl);
        //Collection<ALCityInstanceInPL> puzzleGroupObjectInstanceCollection = pgpo.getAlCityInstanceInPLCollection();
        Iterator<ALCityInstanceInPL> iterator = InstanceCollection.iterator();
        Integer zorder =1;
        while(iterator.hasNext()) {
            ALCityInstanceInPL alCityInstanceInPL = iterator.next();
            if( alCityInstanceInPL.getzOrder()!=0 &&  alCityInstanceInPL.getzOrder()!=null)
                zorder = alCityInstanceInPL.getzOrder();
            InstanceData objectInstanceData = new InstanceData();
            objectInstanceData.setId(alCityInstanceInPL.getId());
            objectInstanceData.setName(alCityInstanceInPL.getName());
            Position instancePostion = new Position(alCityInstanceInPL.getRow() , alCityInstanceInPL.getCol(),zorder);
            objectInstanceData.setPosition(instancePostion);

            Collection<RecordData> properties = DTOUtil.getAttributeForOwnerById(attributeService,alCityInstanceInPL.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);
            Collection<RecordData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,alCityInstanceInPL.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);

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
    ActionService puzzleObjectActionService;
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
