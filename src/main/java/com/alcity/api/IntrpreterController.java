package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.ObjectActionData;
import com.alcity.dto.Interpreter.object.ObjectInstanceData;
import com.alcity.dto.Interpreter.object.ParameterData;
import com.alcity.dto.Interpreter.object.Position;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.ALCityAttributeService;
import com.alcity.service.alobject.PuzzleObject_ObjectActionService;
import com.alcity.service.puzzle.PuzzleLevelGroundService;
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
    private PuzzleLevelGroundService puzzleLevelGroundService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PuzzleLevelData getPuzzleLevelForInterpreter(@PathVariable Long id) {
         PuzzleLevelData puzzleLevelInterpreterDTO= new PuzzleLevelData();
         Optional<PuzzleLevelGround> puzzleLevelGroundOptional = puzzleLevelGroundService.findByPuzzleLevelId(id);
        PuzzleLevelGround puzzleLevelGround = new PuzzleLevelGround();
         if(puzzleLevelGroundOptional.isPresent()){
             puzzleLevelGround = puzzleLevelGroundOptional.get();
             CameraSetup cameraSetup =  puzzleLevelGround.getCameraSetup();
             Position Position = new Position(cameraSetup.getxPosition(),cameraSetup.getyPosition(), cameraSetup.getzPosition());
             Position Rotation = new Position(cameraSetup.getxRotation(),cameraSetup.getyRotation(),cameraSetup.getzRotation());

             CameraSetupData cameraSetupInterpreter = new CameraSetupData(Position,Rotation);

             puzzleLevelInterpreterDTO.setCameraSetup(cameraSetupInterpreter);

             PuzzleLevel pl = puzzleLevelGround.getPuzzleLevel();
             puzzleLevelInterpreterDTO.setCode(pl.getCode());
             puzzleLevelInterpreterDTO.setName(pl.getName());

             Collection<PuzzleLevelObjectiveData> puzzleLevelObjectiveDataCollection = DTOUtil.getPuzzleLevelObjectiveData(pl);
             puzzleLevelInterpreterDTO.setObjectives(puzzleLevelObjectiveDataCollection);
             PuzzleGroup pg = pl.getPuzzleGroup();
             Collection<PuzzleGroupObjectData> objects = getObjectsForPuzzleGroup(pg);

             puzzleLevelInterpreterDTO.setCols(puzzleLevelGround.getNumColumns());
             puzzleLevelInterpreterDTO.setRows(puzzleLevelGround.getNumRows());
             puzzleLevelInterpreterDTO.setObjects(objects);

         }

        return puzzleLevelInterpreterDTO;
    }

    public Collection<ObjectInstanceData> getInstancesForAObjectInPuzzleLevel(PuzzleGroup_PuzzleObject pgpo) {
        Collection<ObjectInstanceData> objectInstanceDataCollection = new ArrayList<ObjectInstanceData>();
        Collection<PuzzleGroupObjectInstance> puzzleGroupObjectInstanceCollection = pgpo.getPuzzleGroupObjectInstanceCollection();
        Iterator<PuzzleGroupObjectInstance> iterator = puzzleGroupObjectInstanceCollection.iterator();

        while(iterator.hasNext()) {
            PuzzleGroupObjectInstance puzzleGroupObjectInstance = iterator.next();
            System.out.println("----------------"+puzzleGroupObjectInstance.getId());

            ObjectInstanceData objectInstanceData = new ObjectInstanceData();
            objectInstanceData.setId(puzzleGroupObjectInstance.getId());
            objectInstanceData.setName(puzzleGroupObjectInstance.getName());
            Position instancePostion = new Position(puzzleGroupObjectInstance.getRow() , puzzleGroupObjectInstance.getCol(),puzzleGroupObjectInstance.getzOrder());
            objectInstanceData.setPosition(instancePostion);

            Collection<ParameterData> parameterData = getPatemetersForAobjectActionById(puzzleGroupObjectInstance.getId());
            objectInstanceData.setProperties(parameterData);

            objectInstanceDataCollection.add(objectInstanceData);
        }

        return objectInstanceDataCollection;
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

            Collection<ObjectActionData> objectActionDataCollection = getActionsForAPuzzleObjectById(puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setActions(objectActionDataCollection);

            Collection<ObjectInstanceData> objectInstanceDataCollection = getInstancesForAObjectInPuzzleLevel(puzzleGroup_puzzleObject);
            puzzleGroupObjectData.setInstances(objectInstanceDataCollection);


            puzzleGroupObjectDataCollection.add(puzzleGroupObjectData);

        }
        return puzzleGroupObjectDataCollection;
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
            PuzzleObjectActionOwnerType puzzleObjectActionOwnerType = puzzleObject_objectAction.getPuzzleObjectActionOwnerType();

            Collection<ParameterData> parameterData = getPatemetersForAobjectActionById(pgoId);
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

    public Collection<ParameterData> getPatemetersForAobjectActionById(Long pgoId){

        Collection<ParameterData> objectActionParameterDataCollection = new ArrayList<ParameterData>();
        Collection<ALCityAttribute> alCityAttributeCollection = new ArrayList<ALCityAttribute>();
        alCityAttributeCollection = alCityAttributeService.findByOwnerId(pgoId);
        Iterator<ALCityAttribute> iterator = alCityAttributeCollection.iterator();
        while(iterator.hasNext()) {
            ALCityAttribute alCityAttribute = iterator.next();
            Collection<ALCityAttributeValue> alCityAttributeValueCollection = alCityAttribute.getAttributeValueSet();
            Iterator<ALCityAttributeValue> alCityAttributeValueIterator = alCityAttributeValueCollection.iterator();
            ALCityAttributeValue alCityAttributeValue = alCityAttributeValueIterator.next();

            ParameterData objectActionParameterData = new ParameterData();
            objectActionParameterData.setName(alCityAttribute.getName());
            objectActionParameterData.setType(alCityAttribute.getDataType().getValue());

            objectActionParameterData.setValue(getValue(alCityAttributeValue));
            objectActionParameterDataCollection.add(objectActionParameterData);
        }

        return objectActionParameterDataCollection;
    }
    public String getValue(ALCityAttributeValue alCityAttributeValue){
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
