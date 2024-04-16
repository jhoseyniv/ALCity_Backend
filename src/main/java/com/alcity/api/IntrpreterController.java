package com.alcity.api;


import com.alcity.dto.Interpreter.*;
import com.alcity.dto.Interpreter.object.ObjectActionData;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.PuzzleObjectActionOwnerType;
import com.alcity.entity.alobject.PuzzleObject_ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleGroup_PuzzleObject;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleLevelGround;
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
             CameraSetupData cameraSetupInterpreter = new CameraSetupData(cameraSetup.getxPosition(),cameraSetup.getyPosition(), cameraSetup.getzPosition(),
                     cameraSetup.getxRotation(),cameraSetup.getyRotation(),cameraSetup.getzRotation());
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
            System.out.println("---------------"+puzzleGroup_puzzleObject.getId());
            puzzleGroupObjectData.setActions(objectActionDataCollection);


            puzzleGroupObjectDataCollection.add(puzzleGroupObjectData);

        }
        return puzzleGroupObjectDataCollection;
    }

    @Autowired
    PuzzleObject_ObjectActionService puzzleObject_objectActionService;
    public Collection<ObjectActionData> getActionsForAPuzzleObjectById(Long poId){
        Collection<ObjectActionData> objectActionDataCollection = new ArrayList<ObjectActionData>();
        Collection<PuzzleObject_ObjectAction> puzzleObject_objectActionCollection = new ArrayList<PuzzleObject_ObjectAction>();
        puzzleObject_objectActionCollection = puzzleObject_objectActionService.findByOwnerObjectid(poId);

        Iterator<PuzzleObject_ObjectAction> iterator = puzzleObject_objectActionCollection.iterator();
        while(iterator.hasNext()) {
            PuzzleObject_ObjectAction puzzleObject_objectAction = iterator.next();
            ObjectAction objectAction = puzzleObject_objectAction.getObjectAction();
            PuzzleObjectActionOwnerType puzzleObjectActionOwnerType = puzzleObject_objectAction.getPuzzleObjectActionOwnerType();

            ObjectActionData objectActionData = new ObjectActionData();
            objectActionData.setActionName(objectAction.getValue());
            objectActionData.setId(objectAction.getId());

            objectActionDataCollection.add(objectActionData);
        }

        return objectActionDataCollection;
    }



}
