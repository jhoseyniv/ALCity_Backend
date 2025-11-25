package com.alcity.api.async;


import com.alcity.dto.plimpexport.*;
import com.alcity.dto.plimpexport.ruleexport.RuleData;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.puzzle.InstanceService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AsyncService {
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

    @Autowired
    ActionService actionService;
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

    public PLData getJsonFile(Long plID) throws IOException, InterruptedException {
        PLData plData= new PLData();

        Optional<PLGround> plGroundOptional = plGroundService.findByPuzzleLevelId(plID);
        PLGround plGround = new PLGround();
        if(plGroundOptional.isPresent()){
            plGround = plGroundOptional.get();

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
            System.out.println("in task 1"+variables.size());
            Collection<PLObjectiveData> objectives = DTOUtil.getPLObjectiveData(pl);
            plData.setObjectives(objectives);
            Collection<PGObjectData> objects = getObjectsForPuzzleGroup(pl);
            plData.setObjects(objects);
            Collection<PLCell> cells = finalPlGround.getPlCells();

            Collection<PLCellData> cellDTOS = DTOUtil.getPLCellDTOS(cells,attributeService);
            plData.setCells(cellDTOS);
            Collection<RuleData> rules = DTOUtil.getPLRulesParalles(pl,attributeService,attributeValueService,plRulePostActionService);
            plData.setRules(rules);

            plData.setCols(plGround.getNumColumns());
            plData.setRows(plGround.getNumRows());

            byte[] jsonBytes = ImageUtil.convertObjectToBytes(plData);
            pl.setInterpreterFile(jsonBytes);

            puzzleLevelService.save(pl);
        }
        return plData;

    }

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor; // Inject the executor

    public CompletableFuture<Collection<Attribute>> findEntitiesAsync() {
       // CompletableFuture<Collection<PuzzleLevel>> puzzles = CompletableFuture.supplyAsync(() ->puzzleLevelService.findAll(), threadPoolTaskExecutor);
        CompletableFuture<Collection<Attribute>> attributes = CompletableFuture.supplyAsync(() ->attributeService.findAll(), threadPoolTaskExecutor);
        //CompletableFuture<Void> allFutures = CompletableFuture.allOf(puzzles, attributes);

        return attributes;
    }


}
