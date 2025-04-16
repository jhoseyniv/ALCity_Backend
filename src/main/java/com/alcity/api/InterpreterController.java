package com.alcity.api;


import com.alcity.dto.Interpreter.CameraSetupDataImport;
import com.alcity.dto.Interpreter.PLDataImport;
import com.alcity.dto.Interpreter.PLObjectiveDataImport;
import com.alcity.dto.Interpreter.PODataImport;
import com.alcity.dto.Interpreter.object.ActionDataImport;
import com.alcity.dto.Interpreter.object.InstanceDataImport;
import com.alcity.dto.Interpreter.object.PositionImport;
import com.alcity.dto.Interpreter.object.RecordDataImport;
import com.alcity.dto.Interpreter.object.RuleDataImport;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.puzzle.InstanceInPLService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import com.alcity.utility.PLDTOUtil;
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
    InstanceInPLService pgObjectInstanceService;

    @Operation( summary = "Create  json File ",  description = "Create Json file for a puzzle level structure and rules")
    @RequestMapping(value = "/create-json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ALCityResponseObject createJsonFile(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLDataImport plData = new PLDataImport();
        if(puzzleLevelOptional.isPresent()){
            plData = getJsonFile(id);
        }
        return new ALCityResponseObject(HttpStatus.OK.value(), "ok",id, "Json Created /Updated Successfully!");
    }

    @Operation( summary = "Fetch a json ",  description = "Fetches all data that need to Interpret a puzzle level structure and rules")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLDataImport getPuzzleLevelForInterpreter(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLDataImport plData = new PLDataImport();
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            if(puzzleLevel.getInterpreterFile()!=null)
                plData = PLDTOUtil.getInterpreterJSON(puzzleLevel);
            else {
                plData = getJsonFile(id);
            }
        }
        return plData;
    }
    @Operation( summary = "Save a Json as Puzzle Level Structure in Database ",  description = "Save a puzzle level  entity and their data to data base from a json")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleLevel(@RequestBody PLDataImport json) {
        ALCityResponseObject responseObject = new ALCityResponseObject();
        PLDataImport plData = json;
        puzzleLevelService.saveJsonInDatabase(plData);
        return responseObject;
    }


    public PLDataImport getJsonFile(Long puzzleLevelId) {
        PLDataImport puzzleLevelData= new PLDataImport();

        Optional<PLGround> puzzleLevelGroundOptional = plGroundService.findByPuzzleLevelId(puzzleLevelId);
        PLGround plGround = new PLGround();
        if(puzzleLevelGroundOptional.isPresent()){
            plGround = puzzleLevelGroundOptional.get();

            PositionImport Position = new PositionImport(plGround.getxPosition(), plGround.getyPosition(), plGround.getzPosition());
            PositionImport Rotation = new PositionImport(plGround.getxRotation(),plGround.getyRotation(),plGround.getzRotation());
            CameraSetupDataImport cameraSetupInterpreter = new CameraSetupDataImport(Position,Rotation);
            puzzleLevelData.setCameraSetup(cameraSetupInterpreter);

            PuzzleLevel pl = plGround.getPuzzleLevel();
            puzzleLevelData.setCode(pl.getCode());
            puzzleLevelData.setName(pl.getTitle());

            Collection<RecordDataImport>  puzzleLevelVariables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

            Collection<PLObjectiveDataImport> plObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
            puzzleLevelData.setObjectives(plObjectiveDataCollection);
            PuzzleGroup pg = pl.getPuzzleGroup();
            Collection<PODataImport> objects = getObjectsForPuzzleGroup(pg,pl);
            Collection<RuleDataImport> rules = DTOUtil.getRulesForPuzzleLevel(pl,attributeService);

            puzzleLevelData.setCols(plGround.getNumColumns());
            puzzleLevelData.setRows(plGround.getNumRows());
            puzzleLevelData.setVariables(puzzleLevelVariables);
            puzzleLevelData.setObjects(objects);
            puzzleLevelData.setRules(rules);
            byte[] bytes = ImageUtil.convertObjectToBytes(puzzleLevelData);
            pl.setInterpreterFile(bytes);
            puzzleLevelService.save(pl);
        }
        return puzzleLevelData;

    }

    public Collection<InstanceDataImport> getInstancesDTOForAnObject(ALCityObjectInPG pgo, PuzzleLevel  pl) {
        Collection<InstanceDataImport> instanceDTOS = new ArrayList<InstanceDataImport>();
        Collection<ALCityInstanceInPL> instances = pgObjectInstanceService.findByAlCityObjectInPGAndPuzzleLevel(pgo,pl);
        Iterator<ALCityInstanceInPL> iterator = instances.iterator();
        Integer zorder =1;
        while(iterator.hasNext()) {
            ALCityInstanceInPL alCityInstanceInPL = iterator.next();

            if( alCityInstanceInPL.getzOrder()!=0 &&  alCityInstanceInPL.getzOrder()!=null)
                zorder = alCityInstanceInPL.getzOrder();

            InstanceDataImport instanceDTO = new InstanceDataImport();
            instanceDTO.setId(alCityInstanceInPL.getId());
            instanceDTO.setName(alCityInstanceInPL.getName());
            PositionImport instancePostion = new PositionImport(alCityInstanceInPL.getRow() , alCityInstanceInPL.getCol(),zorder);
            instanceDTO.setPosition(instancePostion);

            Collection<Attribute> variables = attributeService.findInstanceVariables(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
            Collection<RecordDataImport> variableDTOS = DTOUtil.getVariablesDTOForPGObject(variables);
            instanceDTO.setVariables(variableDTOS);

            Collection<Attribute> properties = attributeService.findInstanceProperties(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
            Collection<RecordDataImport> propertyDTOS = DTOUtil.getPropertiesDTOForPGObject(properties);
            instanceDTO.setProperties(propertyDTOS);

            instanceDTOS.add(instanceDTO);
        }

        return instanceDTOS;
     }

   public PODataImport getPGObjectData(ALCityObjectInPG alCityObjectInPG, PuzzleLevel pl){
       PODataImport poData = new PODataImport();

       poData.setId(alCityObjectInPG.getId());
       poData.setTitle(alCityObjectInPG.getTitle());
       poData.setCode(alCityObjectInPG.getCode());

       BinaryContent picture = alCityObjectInPG.getAlCityObject().getPic();
       poData.setImageGraphicId(picture.getId());

       BinaryContent icon = alCityObjectInPG.getAlCityObject().getIcon();
       poData.setIconGraphicId(icon.getId());

       Collection<ActionDataImport> actions = getActionsDTOForALCityObjectInPG(alCityObjectInPG);
       poData.setActions(actions);

       Collection<Attribute> variables = attributeService.findVariablesForPuzzleGroupObject(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
       Collection<RecordDataImport> variablesDTO = DTOUtil.getVariablesDTOForPGObject(variables);

       poData.setVariables(variablesDTO);

       Collection<Attribute> properties = attributeService.findPropertiesForPuzzleGroupObject(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
       Collection<RecordDataImport> propertiesDTO = DTOUtil.getPropertiesDTOForPGObject(properties);
       poData.setProperties(propertiesDTO);

       Collection<InstanceDataImport> instances = getInstancesDTOForAnObject(alCityObjectInPG,pl);
       poData.setInstances(instances);

       poData.setVersion(alCityObjectInPG.getVersion());

       return  poData;
   }

    public Collection<PODataImport> getObjectsForPuzzleGroup(PuzzleGroup pg, PuzzleLevel pl) {
        Collection<PODataImport> pObjectsData = new ArrayList<PODataImport>();

        Collection<ALCityObjectInPG> objects = new ArrayList<>();
        objects = pg.getAlCityObjectInPGS();
        Iterator<ALCityObjectInPG> iterator = objects.iterator();
        while(iterator.hasNext()) {
            ALCityObjectInPG object = iterator.next();
            PODataImport poData =getPGObjectData(object,pl);
            pObjectsData.add(poData);
        }
        return pObjectsData;
    }

    @Autowired
    ActionService puzzleObjectActionService;
    public Collection<ActionDataImport> getActionsDTOForALCityObjectInPG(ALCityObjectInPG alCityObjectInPG){
        Collection<ActionDataImport> actionsData = new ArrayList<ActionDataImport>();
        Collection<ObjectAction> actions = new ArrayList<ObjectAction>();
        actions = puzzleObjectActionService.findActionsForALCityObjectInPG(alCityObjectInPG);

        Iterator<ObjectAction> iterator = actions.iterator();
        while(iterator.hasNext()) {
            ObjectAction puzzleObjectAction = iterator.next();
            ObjectActionType objectAction = puzzleObjectAction.getObjectAction();
            Collection<Attribute> actionParameters = attributeService.findAttributesForPuzzleGroupObjectActionHandler(puzzleObjectAction.getId());

            Collection<RecordDataImport> parametersData = DTOUtil.getActionParametersDTOS(actionParameters);

            ActionDataImport objectActionData = new ActionDataImport();
            objectActionData.setActionName(objectAction);
            objectActionData.setId(puzzleObjectAction.getId());
            objectActionData.setHandler(puzzleObjectAction.getActionRenderer().getHandler());
            objectActionData.setParameters(parametersData);

            actionsData.add(objectActionData);
        }

        return actionsData;
    }


}
