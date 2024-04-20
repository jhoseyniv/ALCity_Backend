package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.ALCityAttributeService;
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
    private PLGroundService puzzleLevelGroundService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleLevelData getPuzzleLevelForInterpreter(@PathVariable Long id) {
         PuzzleLevelData puzzleLevelData= new PuzzleLevelData();
         Optional<PLGround> puzzleLevelGroundOptional = puzzleLevelGroundService.findByPuzzleLevelId(id);
        PLGround puzzleLevelGround = new PLGround();
         if(puzzleLevelGroundOptional.isPresent()){
             puzzleLevelGround = puzzleLevelGroundOptional.get();

             puzzleLevelData.setBoardGraphicId(puzzleLevelGround.getBoardGraphic().getId());
             CameraSetup cameraSetup =  puzzleLevelGround.getCameraSetup();
             Position Position = new Position(cameraSetup.getxPosition(),cameraSetup.getyPosition(), cameraSetup.getzPosition());
             Position Rotation = new Position(cameraSetup.getxRotation(),cameraSetup.getyRotation(),cameraSetup.getzRotation());
             CameraSetupData cameraSetupInterpreter = new CameraSetupData(Position,Rotation);
             puzzleLevelData.setCameraSetup(cameraSetupInterpreter);

             PuzzleLevel pl = puzzleLevelGround.getPuzzleLevel();
             puzzleLevelData.setCode(pl.getCode());
             puzzleLevelData.setName(pl.getName());

             Collection<RecordrData>  variables = getVariablesForAPuzzleLevelById(pl.getId());

             Collection<PuzzleLevelObjectiveData> puzzleLevelObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
             puzzleLevelData.setObjectives(puzzleLevelObjectiveDataCollection);
             PuzzleGroup pg = pl.getPuzzleGroup();
             Collection<PuzzleGroupObjectData> objects = getObjectsForPuzzleGroup(pg);
             Collection<RuleData> rules = getRulesForPuzzleLevel(pl);

             puzzleLevelData.setCols(puzzleLevelGround.getNumColumns());
             puzzleLevelData.setRows(puzzleLevelGround.getNumRows());
             puzzleLevelData.setVariables(variables);
             puzzleLevelData.setObjects(objects);
             puzzleLevelData.setRules(rules);
         }

        return puzzleLevelData;
    }

    Collection<RecordrData> getParametreDataForRuleAction(Long OwnerId){
        Collection<RecordrData> parameters = new ArrayList<RecordrData>();
        Collection<ALAttribute>  alCityAttributes =alCityAttributeService.findByOwnerIdAndAttributeOwnerType(OwnerId, AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        Iterator<ALAttribute> iterator = alCityAttributes.iterator();
        while(iterator.hasNext()) {
            ALAttribute attribute = iterator.next();

            Collection<ALAttributeValue> attributeValues = attribute.getAttributeValueSet();
            Iterator<ALAttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext()) {
                ALAttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getValue(alCityAttributeValue);
                String type = attribute.getDataType().getValue();
                RecordrData property = new RecordrData(attribute.getName(),value,type);
                parameters.add(property);
            }
        }
        return parameters;
    }
    Collection<RuleActionData> getRuleActionData( PLRule plRule){
        Collection<RuleActionData> actions = new ArrayList<RuleActionData>();
        Collection<PLRulePostAction> plRulePostActions = plRule.getPlRulePostActions();
        Iterator<PLRulePostAction> iterator = plRulePostActions.iterator();
        while(iterator.hasNext()) {
            Collection<RecordrData> parameters = new ArrayList<RecordrData>();

            PLRulePostAction plRulePostAction =iterator.next();
            RuleActionData ruleActionData = new RuleActionData();
            ruleActionData.setOrdering(plRulePostAction.getOrdering());
            ruleActionData.setActionName(plRulePostAction.getActionName());
            ruleActionData.setObjectId(plRulePostAction.getObjectId());
            ruleActionData.setActionType(plRulePostAction.getPlRulePostActionType().toString());
            parameters = getParametreDataForRuleAction(plRulePostAction.getId());
            ruleActionData.setParameters(parameters);

            actions.add(ruleActionData);
        }


            return actions;
    }

    public Collection<RuleData> getRulesForPuzzleLevel(PuzzleLevel pl){
        Collection<RuleData> rules = new ArrayList<RuleData>();
        Collection<PLRule>  puzzleLevelRules = pl.getPuzzleLevelRuleCollection();
        Iterator<PLRule> iterator = puzzleLevelRules.iterator();
        while(iterator.hasNext()) {
            PLRule puzzleLevelRule = iterator.next();
            RuleData rule = new RuleData();
            rule.setTitle(puzzleLevelRule.getTitle());
            rule.setOrdering(puzzleLevelRule.getOrdering());
            rule.setConditions(puzzleLevelRule.getCondition());
            Collection<RuleActionData> actions = getRuleActionData(puzzleLevelRule);
            rule.setActions(actions);

            rules.add(rule);
        }

      return rules;
    }

    public Collection<ObjectInstanceData> getInstancesForAObjectInPuzzleLevel(PuzzleGroup_PuzzleObject pgpo) {
        Collection<ObjectInstanceData> objectInstanceDataCollection = new ArrayList<ObjectInstanceData>();
        Collection<PuzzleGroupObjectInstance> puzzleGroupObjectInstanceCollection = pgpo.getPuzzleGroupObjectInstanceCollection();
        Iterator<PuzzleGroupObjectInstance> iterator = puzzleGroupObjectInstanceCollection.iterator();

        while(iterator.hasNext()) {
            PuzzleGroupObjectInstance puzzleGroupObjectInstance = iterator.next();

            ObjectInstanceData objectInstanceData = new ObjectInstanceData();
            objectInstanceData.setId(puzzleGroupObjectInstance.getId());
            objectInstanceData.setName(puzzleGroupObjectInstance.getName());
            Position instancePostion = new Position(puzzleGroupObjectInstance.getRow() , puzzleGroupObjectInstance.getCol(),puzzleGroupObjectInstance.getzOrder());
            objectInstanceData.setPosition(instancePostion);

            Collection<RecordrData> properties = getPropertiesForAInstanceObjectById(puzzleGroupObjectInstance.getId());
            Collection<RecordrData>  variables = getVariablesForAInstanceObjectById(puzzleGroupObjectInstance.getId());
            objectInstanceData.setProperties(properties);
            objectInstanceData.setVariables(variables);

            objectInstanceDataCollection.add(objectInstanceData);
        }

        return objectInstanceDataCollection;
     }
    public Collection<RecordrData>  getVariablesForAPuzzleLevelById(Long plId){
        Collection<RecordrData> variables = new ArrayList<RecordrData>();
        Collection<ALAttribute>  alCityAttributes =alCityAttributeService.findByOwnerIdAndAttributeOwnerType(plId,AttributeOwnerType.Puzzle_Level_Variable);
        Iterator<ALAttribute> iterator = alCityAttributes.iterator();
        while(iterator.hasNext()) {
            ALAttribute attribute = iterator.next();
            Collection<ALAttributeValue> attributeValues = attribute.getAttributeValueSet();
            Iterator<ALAttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext()) {
                ALAttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getValue(alCityAttributeValue);
                String type = attribute.getDataType().getValue();
                RecordrData variable = new RecordrData(attribute.getName(),value,type);
                variables.add(variable);
            }

        }

        return variables;
    }
    public Collection<RecordrData>  getPropertiesForAInstanceObjectById(Long instanceId){
        Collection<RecordrData> properties = new ArrayList<RecordrData>();
        Collection<ALAttribute>  alCityAttributes =alCityAttributeService.findByOwnerIdAndAttributeOwnerType(instanceId, AttributeOwnerType.PuzzleGroup_ObjectInstance_Property);
        Iterator<ALAttribute> iterator = alCityAttributes.iterator();
        while(iterator.hasNext()) {
            ALAttribute attribute = iterator.next();

            Collection<ALAttributeValue> attributeValues = attribute.getAttributeValueSet();
            Iterator<ALAttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext()) {
                ALAttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getValue(alCityAttributeValue);
                String type = attribute.getDataType().getValue();
                RecordrData property = new RecordrData(attribute.getName(),value,type);
                properties.add(property);
            }
        }
        return properties;
    }

    public Collection<RecordrData>  getVariablesForAInstanceObjectById(Long instanceId){
        Collection<RecordrData> variables = new ArrayList<RecordrData>();
        Collection<ALAttribute>  alCityAttributes =alCityAttributeService.findByOwnerIdAndAttributeOwnerType(instanceId,AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable);
        Iterator<ALAttribute> iterator = alCityAttributes.iterator();
        while(iterator.hasNext()) {
            ALAttribute attribute = iterator.next();

            Collection<ALAttributeValue> attributeValues = attribute.getAttributeValueSet();
            Iterator<ALAttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext()) {
                ALAttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getValue(alCityAttributeValue);
                String type = attribute.getDataType().getValue();
                RecordrData variable = new RecordrData(attribute.getName(),value,type);
                variables.add(variable);
            }
        }

        return variables;
    }


        public Collection<PuzzleGroupObjectData> getObjectsForPuzzleGroup(PuzzleGroup pg) {
        Collection<PuzzleGroupObjectData> puzzleGroupObjectDataCollection = new ArrayList<PuzzleGroupObjectData>();

        Collection<PuzzleGroup_PuzzleObject> puzzleGroup_puzzleObjectCollection = new ArrayList<>();
        puzzleGroup_puzzleObjectCollection = pg.getPuzzleGroup_puzzleObjectCollection();
        Iterator<PuzzleGroup_PuzzleObject> iterator = puzzleGroup_puzzleObjectCollection.iterator();
        while(iterator.hasNext()) {
            PuzzleGroup_PuzzleObject puzzleGroup_puzzleObject = iterator.next();
            PuzzleGroupObjectData puzzleGroupObjectData = new PuzzleGroupObjectData();

            puzzleGroupObjectData.setId(puzzleGroup_puzzleObject.getPuzzleObject().getId());
            puzzleGroupObjectData.setTitle(puzzleGroup_puzzleObject.getTitle());
            puzzleGroupObjectData.setCode(puzzleGroup_puzzleObject.getCode());
            puzzleGroupObjectData.setVersion(puzzleGroup_puzzleObject.getVersion());

            BinaryContent picture = puzzleGroup_puzzleObject.getPuzzleObject().getPicture();
            puzzleGroupObjectData.setImageGraphicId(picture.getId());

            BinaryContent icon = puzzleGroup_puzzleObject.getPuzzleObject().getIcon();
            puzzleGroupObjectData.setIconGraphicId(icon.getId());

            Collection<ObjectActionData> actions = getActionsForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setActions(actions);

            Collection<RecordrData> variables = getVariableForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setVariables(variables);

            Collection<RecordrData> properties = getPropertiesForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setVariables(properties);

            Collection<ObjectInstanceData> instances = getInstancesForAObjectInPuzzleLevel(puzzleGroup_puzzleObject);
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
    public Collection<ObjectActionData> getActionsForAPuzzleObjectById(Long pgoId){
        Collection<ObjectActionData> objectActionDataCollection = new ArrayList<ObjectActionData>();
        Collection<PuzzleObject_ObjectAction> puzzleObject_objectActionCollection = new ArrayList<PuzzleObject_ObjectAction>();
        puzzleObject_objectActionCollection = puzzleObject_objectActionService.findByOwnerObjectid(pgoId);

        Iterator<PuzzleObject_ObjectAction> iterator = puzzleObject_objectActionCollection.iterator();
        while(iterator.hasNext()) {
            PuzzleObject_ObjectAction puzzleObject_objectAction = iterator.next();
            ObjectAction objectAction = puzzleObject_objectAction.getObjectAction();
            POActionOwnerType puzzleObjectActionOwnerType = puzzleObject_objectAction.getPoActionOwnerType();

            Collection<RecordrData> parameterData = getPropertiesForAOwnerObject(pgoId,puzzleObjectActionOwnerType);
            ObjectActionData objectActionData = new ObjectActionData();
            objectActionData.setActionName(objectAction.getValue());
            objectActionData.setId(objectAction.getId());
            objectActionData.setHandler(puzzleObject_objectAction.getActionRenderer().getHandler());
            objectActionData.setParameters(parameterData);

            objectActionDataCollection.add(objectActionData);
        }

        return objectActionDataCollection;
    }

    @Autowired
    ALCityAttributeService alCityAttributeService;

    public Collection<RecordrData> getPropertiesForAOwnerObject(Long ownerId, POActionOwnerType ownerType){

        Collection<RecordrData> objectActionParameterDataCollection = new ArrayList<RecordrData>();
        Collection<ALAttribute> alCityAttributeCollection = new ArrayList<ALAttribute>();
        alCityAttributeCollection = alCityAttributeService.findByOwnerId(ownerId);
        Iterator<ALAttribute> iterator = alCityAttributeCollection.iterator();
        while(iterator.hasNext()) {
            ALAttribute alCityAttribute = iterator.next();
            Collection<ALAttributeValue> alCityAttributeValueCollection = alCityAttribute.getAttributeValueSet();
            Iterator<ALAttributeValue> alCityAttributeValueIterator = alCityAttributeValueCollection.iterator();
            ALAttributeValue alCityAttributeValue = alCityAttributeValueIterator.next();

            RecordrData objectActionParameterData = new RecordrData();
            objectActionParameterData.setName(alCityAttribute.getName());
            objectActionParameterData.setType(alCityAttribute.getDataType().getValue());

            objectActionParameterData.setValue(getValue(alCityAttributeValue));
            objectActionParameterDataCollection.add(objectActionParameterData);
        }

        return objectActionParameterDataCollection;
    }
    public String getValue(ALAttributeValue alCityAttributeValue){
        if (alCityAttributeValue.getBooleanValue()!=null )
            return alCityAttributeValue.getBooleanValue().toString();

        if (alCityAttributeValue.getDoubleValue()!=null )
            return alCityAttributeValue.getDoubleValue().toString();

        if (alCityAttributeValue.getIntValue()!=null )
            return alCityAttributeValue.getIntValue().toString();

        if (alCityAttributeValue.getLongValue()!=null )
            return alCityAttributeValue.getLongValue().toString();

        if (alCityAttributeValue.getBinaryContent()!=null )
            return alCityAttributeValue.getBinaryContent().toString();

        if (alCityAttributeValue.getStringValue()!=null )
            return alCityAttributeValue.getStringValue();

        return "Unknown Value";
    }

}
