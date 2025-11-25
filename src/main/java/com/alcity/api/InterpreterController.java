package com.alcity.api;


import com.alcity.dto.base.PLBinaryContentDTO;
import com.alcity.dto.plimpexport.*;
import com.alcity.dto.plimpexport.InstanceData;
import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.dto.plimpexport.PGObjectData;
import com.alcity.dto.plimpexport.ruleexport.RuleData;
import com.alcity.dto.plimpexport.ActionData;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.customexception.ResponseObject;
import com.alcity.service.puzzle.InstanceService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Tag(name = "Interpreter APIs", description = "Get Puzzle Level in the Json Format for Other Systems")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/puzz")
public class InterpreterController  {

    @Autowired
    private PLGroundService plGroundService;

    @Autowired
    PuzzleLevelService puzzleLevelService;

    @Autowired
    AttributeService attributeService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Autowired
    InstanceService pgObjectInstanceService;

    @Operation( summary = "Create  json File ",  description = "Create Json file for a puzzle level structure and rules")
    @RequestMapping(value = "/create-json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseObject createJsonFile(@PathVariable Long id) throws IOException, ClassNotFoundException, InterruptedException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
        Collection<PLBinaryContentDTO> plContents= new ArrayList<>();
        if(puzzleLevelOptional.isPresent()){
            plData = getJsonFile(id);
            plContents = puzzleLevelService.getContents(id);
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            byte[] plContentsBytes = ImageUtil.convertObjectToBytes(plContents);
            puzzleLevel.setPlconetents(plContentsBytes);
            puzzleLevelService.save(puzzleLevel);

        }
        return new ResponseObject(ErrorType.SaveSuccess, ObjectAction.class.getSimpleName() , Status.ok.name(), id, SystemMessage.SaveOrEditMessage_Success);
    }

    @Operation( summary = "Fetch a json ",  description = "Fetches all data that need to Interpret a puzzle level structure and rules")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLData getPuzzleLevelForInterpreter(@PathVariable Long id) throws IOException, ClassNotFoundException, InterruptedException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
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
/*
    @Operation( summary = "Fetch PL Contents from DB  by puzzle level Id",  description = "Fetches PL Contents for a puzzle level from DB")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLContents getPuzzleLevelContents(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLContents plContents = new PLContents();
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            if(puzzleLevel.getPlconetents()!=null)
                plContents = PLDTOUtil.getPLContentsJSON(puzzleLevel);
            else {
                plContents = puzzleLevelService.getContents(id);
            }
        }
        return plContents;
    }
*/

    public Collection<AttributeData>  getAttributes(PuzzleLevel pl){
        return DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);
    }


 public PLData getJsonFile(Long plID) throws IOException, InterruptedException {
    PLData plData= new PLData();


    Optional<PLGround> plGroundOptional = plGroundService.findByPuzzleLevelId(plID);
    PLGround plGround = new PLGround();
    if(plGroundOptional.isPresent()){
        plGround = plGroundOptional.get();

        long start_time = System.currentTimeMillis();

        PositionDTO Position = new PositionDTO(plGround.getxPosition(), plGround.getyPosition(), plGround.getzPosition());
        PositionDTO Rotation = new PositionDTO(plGround.getxRotation(),plGround.getyRotation(),plGround.getzRotation());
        FeaturesData features = new FeaturesData(plGround.getZoom(),plGround.getPan(),plGround.getRotation());
        BoardCenterDTO boardCenterDTO = new BoardCenterDTO(plGround.getBoardCenterX(), plGround.getBoardCenterY(),plGround.getBoardCenterZ());
        BoardCenterDTO initialOffsetDTO = new BoardCenterDTO(plGround.getInitPanOffsetX(), plGround.getInitPanOffsetY(),plGround.getInitPanOffsetZ());
        InitialValuesDTO initialValuesDTO = new InitialValuesDTO(plGround.getInitValueZoom(), plGround.getInitValueZoomLimit(), plGround.getPanLimit(),boardCenterDTO,initialOffsetDTO
                ,plGround.getBackground().getId(),plGround.getSkybox().getId(),plGround.getBackgroundScale() );
        CameraSetupData cameraSetupData = new CameraSetupData(Position,Rotation,features,initialValuesDTO);
        cameraSetupData.setFeatures(features);
        plData.setCameraSetup(cameraSetupData);

        PuzzleLevel pl = plGround.getPuzzleLevel();
        plData.setCode(pl.getCode());
        plData.setName(pl.getTitle());

              //  Collection<AttributeData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);
            PLGround finalPlGround = plGround;
                Collection<AttributeData> variables = DTOUtil.getAttributeForOwnerById(attributeService,plID, AttributeOwnerType.Puzzle_Level_Variable);
                plData.setVariables(variables);
                Collection<PLObjectiveData> objectives = DTOUtil.getPLObjectiveData(pl);
                plData.setObjectives(objectives);
                Collection<PGObjectData> objects = getObjectsForPuzzleGroup(pl);
                plData.setObjects(objects);
                Collection<PLCell> cells = finalPlGround.getPlCells();

                Collection<PLCellData> cellDTOS = DTOUtil.getPLCellDTOS(cells,attributeService);
                plData.setCells(cellDTOS);

        long start_time2 = System.currentTimeMillis();

                Collection<RuleData> rules = DTOUtil.getPLRules(pl,attributeService,attributeValueService,plRulePostActionService);
                plData.setRules(rules);
        long end_time2 = System.currentTimeMillis();
        String message_6 = "Time for running get json puzzle Rules  is ------- = " + (end_time2 - start_time2)/1000 + " Seconds";
        ToolBox.SendMessageToImportLogs(message_6, DateUtils.getNow());

        plData.setCols(plGround.getNumColumns());
        plData.setRows(plGround.getNumRows());

        long end_time = System.currentTimeMillis();
        String message_7 = "Time for running get json puzzle  is ------- = " + (end_time - start_time)/1000 + " Seconds";
        ToolBox.SendMessageToImportLogs(message_7, DateUtils.getNow());

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

            if( alCityInstanceInPL.getZorder()!=0 &&  alCityInstanceInPL.getZorder()!=null)
                zorder = alCityInstanceInPL.getZorder();

            InstanceData instanceDTO = new InstanceData();
            instanceDTO.setId(alCityInstanceInPL.getId());
            instanceDTO.setName(alCityInstanceInPL.getName());
            instanceDTO.setPgoId(pgo.getId());
            PostionIntDTO instancePosition = new PostionIntDTO(alCityInstanceInPL.getRow() , alCityInstanceInPL.getCol(),zorder);
            instanceDTO.setPosition(instancePosition);

            Collection<Attribute> variables = attributeService.findInstanceVariables(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
            Collection<AttributeData> variableDTOS = DTOUtil.getVariablesDTOForPGObject(variables);
            instanceDTO.setVariables(variableDTOS);

            Collection<Attribute> properties = attributeService.findInstanceProperties(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
            Collection<AttributeData> propertyDTOS = DTOUtil.getPropertiesDTOForPGObject(properties);
            instanceDTO.setProperties(propertyDTOS);
            instanceDTOS.add(instanceDTO);
        }

        return instanceDTOS;
    }
    public Collection<InstanceData> getInstanceDataWithVariablesOnly(PGObject pgo, PuzzleLevel  pl) {
        Collection<InstanceData> instanceDTOS = new ArrayList<InstanceData>();
        Collection<Instance> instances = pgObjectInstanceService.findByAlCityObjectInPGAndPuzzleLevel(pgo,pl);
        Iterator<Instance> iterator = instances.iterator();
        Integer zorder =1;
        while(iterator.hasNext()) {
            Instance alCityInstanceInPL = iterator.next();

            if( alCityInstanceInPL.getZorder()!=0 &&  alCityInstanceInPL.getZorder()!=null)
                zorder = alCityInstanceInPL.getZorder();

            InstanceData instanceDTO = new InstanceData();
            instanceDTO.setId(alCityInstanceInPL.getId());
            instanceDTO.setName(alCityInstanceInPL.getName());
            instanceDTO.setPgoId(pgo.getId());
            PostionIntDTO instancePosition = new PostionIntDTO(alCityInstanceInPL.getRow() , alCityInstanceInPL.getCol(),zorder);
            instanceDTO.setPosition(instancePosition);

            Collection<Attribute> variables = attributeService.findInstanceVariablesOnly(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
            Collection<AttributeData> variableDTOS = DTOUtil.getVariablesDTOForPGObject(variables);
            instanceDTO.setVariables(variableDTOS);

            Collection<Attribute> properties = attributeService.findInstanceProperties(alCityInstanceInPL.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
            Collection<AttributeData> propertyDTOS = DTOUtil.getPropertiesDTOForPGObject(properties);
            instanceDTO.setProperties(propertyDTOS);
            instanceDTOS.add(instanceDTO);
        }
        return instanceDTOS;
    }

   public PGObjectData getPGObjectData(PGObject alCityObjectInPG, PuzzleLevel pl){
       PGObjectData poData = new PGObjectData();

       poData.setId(alCityObjectInPG.getId());
       poData.setTitle(alCityObjectInPG.getTitle());
       poData.setCode(alCityObjectInPG.getTitle());

       BinaryContent picture = alCityObjectInPG.getAlCityObject().getPic();
       poData.setImageGraphicId(picture.getId());

       BinaryContent icon = alCityObjectInPG.getAlCityObject().getIcon();
       poData.setIconGraphicId(icon.getId());

       Collection<ActionData> actions = getActionsDTOForALCityObjectInPG(alCityObjectInPG);
       poData.setActions(actions);

       Collection<Attribute> variables = attributeService.findVariablesForPuzzleGroupObject(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
       Collection<AttributeData> variablesDTO = DTOUtil.getVariablesDTOForPGObject(variables);

       poData.setVariables(variablesDTO);

       Collection<Attribute> properties = attributeService.findPropertiesForPuzzleGroupObject(alCityObjectInPG.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
       Collection<AttributeData> propertiesDTO = DTOUtil.getPropertiesDTOForPGObject(properties);
       poData.setProperties(propertiesDTO);
       //get instances  for an object that define in PG and used in a puzzle level
       Collection<InstanceData> instances = getInstanceData(alCityObjectInPG,pl);
       //Collection<InstanceData> instances = getInstanceDataWithVariablesOnly(alCityObjectInPG,pl);
       poData.setInstances(instances);

       poData.setVersion(alCityObjectInPG.getVersion());

       return  poData;
   }

    public Collection<PGObjectData> getObjectsForPuzzleGroup(PuzzleLevel pl) {
        PuzzleGroup pg = pl.getPuzzleGroup();
        Collection<PGObjectData> objectData = new ArrayList<PGObjectData>();
        Collection<PGObject> objects = new ArrayList<>();
        objects = pg.getAlCityObjectInPGS();
        for (PGObject object : objects) {
            //fetch puzzle object that used in a pl including instances properties , variables , ...
            PGObjectData poData = getPGObjectData(object, pl);
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

            Collection<AttributeData> parametersData = DTOUtil.getActionParametersDTOS(actionParameters);

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
