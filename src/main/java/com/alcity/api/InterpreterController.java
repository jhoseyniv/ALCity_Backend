package com.alcity.api;


import com.alcity.api.async.HeavyService;
import com.alcity.customexception.ResponseMessage;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    HeavyService heavyTaskService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Autowired
    InstanceService pgObjectInstanceService;

    @Operation( summary = "Create  json File ",  description = "Create Json file for a puzzle level structure and rules")
    @RequestMapping(value = "/create-json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage createJsonFile(@PathVariable Long id) throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
        Collection<PLBinaryContentDTO> plContents= new ArrayList<>();
        if(puzzleLevelOptional.isPresent()){
            try {
                plData = getJsonFile(id);
                //plData = getJsonFileAsync(id);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //    plContents = puzzleLevelService.getContents(id);
         //   PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
          //  byte[] plContentsBytes = ImageUtil.convertObjectToBytes(plContents);
          //  puzzleLevel.setPlconetents(plContentsBytes);
          //  puzzleLevelService.save(puzzleLevel);

        }
        else
            return new ResponseMessage(ErrorType.PUZZLE_LEVEL_NOT_CREATED, InterpreterController.class.getSimpleName() , Status.ok.name(), id, SystemMessage.PUZZLE_LEVEL_NOT_CREATED);
       // return plData;
        return new ResponseMessage(ErrorType.JSON_CREATED_SUCCESSFULLY, InterpreterController.class.getSimpleName() , Status.ok.name(), id, SystemMessage.JSON_CREATED_SUCCESSFULLY);
    }

    @Operation( summary = "Fetch a json ",  description = "Fetches all data that need to Interpret a puzzle level structure and rules")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLData getPuzzleLevelForInterpreter(@PathVariable Long id) throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
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
public PLData getJsonFile(Long plID) throws ExecutionException, InterruptedException, IOException {
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

        Collection<AttributeData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

        Collection<PLObjectiveData> objectives = DTOUtil.getPLObjectiveData(pl);
        plData.setObjectives(objectives);

        Collection<PGObjectData> objects = DTOUtil.getObjectsForPuzzleGroup(pl,attributeService,actionService,pgObjectInstanceService);

        Collection<PLCell> cells = plGround.getPlCells();

        Collection<PLCellData> cellDTOS = DTOUtil.getPLCellDTOS(cells,attributeService);


         Collection<RuleData> rules = DTOUtil.getPLRules(pl,attributeService,attributeValueService,plRulePostActionService);

        plData.setCols(plGround.getNumColumns());
        plData.setRows(plGround.getNumRows());
        plData.setVariables(variables);
        plData.setObjects(objects);
        plData.setCells(cellDTOS);
        plData.setRules(rules);

        byte[] jsonBytes = ImageUtil.convertObjectToBytes(plData);
        pl.setInterpreterFile(jsonBytes);
        long end_time = System.currentTimeMillis();
        String message_1 = "Time for running get Json Method  = " + (end_time - start_time);
        ToolBox.SendMessageToImportLogs(message_1, DateUtils.getNow());
        System.out.println(message_1);

        puzzleLevelService.save(pl);
    }
    return plData;

}

    public PLData getJsonFileAsync(Long plID) throws ExecutionException, InterruptedException, IOException {
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

            Collection<AttributeData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,pl.getId(),AttributeOwnerType.Puzzle_Level_Variable);

            Collection<PLObjectiveData> objectives = DTOUtil.getPLObjectiveData(pl);
            plData.setObjectives(objectives);

            //Collection<PGObjectData> objects = DTOUtil.getObjectsForPuzzleGroup(pl,attributeService,actionService,pgObjectInstanceService);
            CompletableFuture<Collection<PGObjectData>> futureObjects = heavyTaskService.getObjectsForPuzzleGroup(pl);

            Collection<PLCell> cells = plGround.getPlCells();

            Collection<PLCellData> cellDTOS = DTOUtil.getPLCellDTOS(cells,attributeService);

           // CompletableFuture<Collection<PLCellData>> futureCells = heavyTaskService.getCells(plGround.getPlCells());

            // Collection<RuleData> rules = DTOUtil.getPLRules(pl,attributeService,attributeValueService,plRulePostActionService);
            CompletableFuture<Collection<RuleData>> futureRules = heavyTaskService.getRules(pl);

            CompletableFuture.allOf(futureObjects, futureRules).join();

            Collection<PGObjectData> objects = futureObjects.join();
            //Collection<PLCellData> cellDTOS = futureCells.join();
            Collection<RuleData> rules = futureRules.join();

           // Collection<PLCellData> cellDTOS = futureCelss.get(); // اینجا نتیجه را دریافت می‌کنیم
           // Collection<PGObjectData> objects = futureObjects.get(); // اینجا نتیجه را دریافت می‌کنیم
           // Collection<RuleData> rules = futureRules.get(); // اینجا نتیجه را دریافت می‌کنیم

            plData.setCols(plGround.getNumColumns());
            plData.setRows(plGround.getNumRows());
            plData.setVariables(variables);
            plData.setObjects(objects);
            plData.setCells(cellDTOS);
            plData.setRules(rules);

            byte[] jsonBytes = ImageUtil.convertObjectToBytes(plData);
            pl.setInterpreterFile(jsonBytes);
            long end_time = System.currentTimeMillis();
            String message_1 = "Time for running get Json by Async Method  = " + (end_time - start_time);
            ToolBox.SendMessageToImportLogs(message_1, DateUtils.getNow());
            System.out.println(message_1);

            puzzleLevelService.save(pl);
        }
        return plData;

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



    @Autowired
    ActionService actionService;


}
