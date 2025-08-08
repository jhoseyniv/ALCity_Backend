package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.puzzle.InstanceService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    PLRulePostActionService plRulePostActionService;

    @Autowired
    InstanceService pgObjectInstanceService;

    @Operation( summary = "Create  json File ",  description = "Create Json file for a puzzle level structure and rules")
    @RequestMapping(value = "/create-json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ALCityResponseObject createJsonFile(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
        if(puzzleLevelOptional.isPresent()){
            plData = getJsonFile(id);
        }
        return new ALCityResponseObject(HttpStatus.OK.value(), "ok",id, "Json Created /Updated Successfully!");
    }

    @Operation( summary = "Fetch a json ",  description = "Fetches all data that need to Interpret a puzzle level structure and rules")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLData getPuzzleLevelForInterpreter(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
           // if(puzzleLevel.getInterpreterFile()!=null)
           //     plData = PLDTOUtil.getInterpreterJSON(puzzleLevel);
          //  else {
                plData = getJsonFile(id);
         //   }
        }
        return plData;
    }

    public PLData getJsonFile(Long puzzleLevelId) {
        PLData plData= new PLData();

        Optional<PLGround> plGroundOptional = plGroundService.findByPuzzleLevelId(puzzleLevelId);
        PLGround plGround = new PLGround();
        if(plGroundOptional.isPresent()){
            plGround = plGroundOptional.get();

            PLGroundPostion Position = new PLGroundPostion(plGround.getxPosition(), plGround.getyPosition(), plGround.getzPosition());
            PLGroundPostion Rotation = new PLGroundPostion(plGround.getxRotation(),plGround.getyRotation(),plGround.getzRotation());
            Features features = new Features(plGround.getZoom(),plGround.getPan(),plGround.getRotation());
            CameraSetupData cameraSetupData = new CameraSetupData(Position,Rotation,features);
            cameraSetupData.setFeatures(features);
            plData.setCameraSetup(cameraSetupData);

            PuzzleLevel pl = plGround.getPuzzleLevel();
            plData.setCode(pl.getCode());
            plData.setName(pl.getTitle());

            Collection<RecordData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

            Collection<PLObjectiveData> objectives = DTOUtil.getPuzzleLevelObjectiveData(pl);
            plData.setObjectives(objectives);

            Collection<POData> objects = getObjectsForPuzzleGroup(pl);
            Collection<PLCell> cells = plGround.getPlCells();

            Collection<PLCellData> cellDTOS = DTOUtil.getPLCellDTOS(cells,attributeService);

            Collection<RuleData> rules = DTOUtil.getRulesForPuzzleLevel(pl,attributeService,plRulePostActionService);

            plData.setCols(plGround.getNumColumns());
            plData.setRows(plGround.getNumRows());
            plData.setVariables(variables);
            plData.setObjects(objects);
            plData.setCells(cellDTOS);
            plData.setRules(rules);

            byte[] jsonBytes = ImageUtil.convertObjectToBytes(plData);
            pl.setInterpreterFile(jsonBytes);

            puzzleLevelService.save(pl);
        }
        return plData;

    }

    public Collection<InstanceData> getInstanceData(PGObject pgo, PuzzleLevel  pl) {
        Collection<InstanceData> instanceDTOS = new ArrayList<InstanceData>();
        Collection<Instance> instances = pgObjectInstanceService.findByAlCityObjectInPGAndPuzzleLevel(pgo,pl);
        Iterator<Instance> iterator = instances.iterator();
        Integer zorder =1;
        while(iterator.hasNext()) {
            Instance alCityInstanceInPL = iterator.next();

            if( alCityInstanceInPL.getzOrder()!=0 &&  alCityInstanceInPL.getzOrder()!=null)
                zorder = alCityInstanceInPL.getzOrder();

            InstanceData instanceDTO = new InstanceData();
            instanceDTO.setId(alCityInstanceInPL.getId());
            instanceDTO.setName(alCityInstanceInPL.getName());
            instanceDTO.setPgoId(pgo.getId());
            Position instancePostion = new Position(alCityInstanceInPL.getRow() , alCityInstanceInPL.getCol(),zorder);
            instanceDTO.setPosition(instancePostion);

            Collection<Attribute> variables = attributeService.findInstanceVariables(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
            Collection<RecordData> variableDTOS = DTOUtil.getVariablesDTOForPGObject(variables);
            instanceDTO.setVariables(variableDTOS);

            Collection<Attribute> properties = attributeService.findInstanceProperties(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
            Collection<RecordData> propertyDTOS = DTOUtil.getPropertiesDTOForPGObject(properties);
            instanceDTO.setProperties(propertyDTOS);

            instanceDTOS.add(instanceDTO);
        }
        return instanceDTOS;
     }

   public POData getPGObjectData(PGObject alCityObjectInPG, PuzzleLevel pl){
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

       Collection<Attribute> variables = attributeService.findVariablesForPuzzleGroupObject(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
       Collection<RecordData> variablesDTO = DTOUtil.getVariablesDTOForPGObject(variables);

       poData.setVariables(variablesDTO);

       Collection<Attribute> properties = attributeService.findPropertiesForPuzzleGroupObject(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
       Collection<RecordData> propertiesDTO = DTOUtil.getPropertiesDTOForPGObject(properties);
       poData.setProperties(propertiesDTO);
        //get instances  for an object that define in PG and used in a puzzle level
       Collection<InstanceData> instances = getInstanceData(alCityObjectInPG,pl);
       poData.setInstances(instances);

       poData.setVersion(alCityObjectInPG.getVersion());

       return  poData;
   }

    public Collection<POData> getObjectsForPuzzleGroup(PuzzleLevel pl) {
        PuzzleGroup pg = pl.getPuzzleGroup();
        Collection<POData> objectData = new ArrayList<POData>();
        Collection<PGObject> objects = new ArrayList<>();
        objects = pg.getAlCityObjectInPGS();
        Iterator<PGObject> iterator = objects.iterator();
        while(iterator.hasNext()) {
            PGObject object = iterator.next();
            //fetch puzzle object that used in a pl including instances properties , vriables , ...
            POData poData =getPGObjectData(object,pl);
            objectData.add(poData);
        }
        return objectData;
    }

    @Autowired
    ActionService actionService;
    public Collection<ActionData> getActionsDTOForALCityObjectInPG(PGObject alCityObjectInPG){
        Collection<ActionData> actionsData = new ArrayList<ActionData>();
        Collection<ObjectAction> actions = new ArrayList<ObjectAction>();
        actions = actionService.findActionsForALCityObjectInPG(alCityObjectInPG);

        Iterator<ObjectAction> iterator = actions.iterator();
        while(iterator.hasNext()) {
            ObjectAction puzzleObjectAction = iterator.next();
            ObjectActionType objectAction = puzzleObjectAction.getObjectAction();
            Collection<Attribute> actionParameters = attributeService.findAttributesForPuzzleGroupObjectActionHandler(puzzleObjectAction.getId());

            Collection<RecordData> parametersData = DTOUtil.getActionParametersDTOS(actionParameters);

            ActionData objectActionData = new ActionData();
            objectActionData.setActionName(objectAction);
            objectActionData.setId(puzzleObjectAction.getId());
            objectActionData.setHandler(puzzleObjectAction.getActionRenderer().getHandler());
            objectActionData.setParameters(parametersData);

            actionsData.add(objectActionData);
        }

        return actionsData;
    }


}
