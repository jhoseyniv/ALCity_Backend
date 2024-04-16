package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.ObjectActionData;
import com.alcity.dto.puzzle.PuzzleLevelObjectiveDTO;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.PuzzleObjectActionOwnerType;
import com.alcity.entity.alobject.PuzzleObject_ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleGroup_PuzzleObject;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleLevelObjective;
import com.alcity.service.alobject.PuzzleObject_ObjectActionService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class InterpreterUtil {




//    public static Collection<PuzzleLevelObjectiveData> getPuzzleLevelObjectiveData(Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection) {
//        Collection<PuzzleLevelObjectiveData> puzzleLevelObjectiveDataCollection = new ArrayList<PuzzleLevelObjectiveData>();
//        Iterator<PuzzleLevelObjectiveDTO> itr_objectives = puzzleLevelObjectiveDTOCollection.iterator();
//
//        while (itr_objectives.hasNext()) {
//            PuzzleLevelObjectiveDTO puzzleLevelObjectiveDTO = itr_objectives.next();
//            PuzzleLevelObjectiveData puzzleLevelObjectiveData = new PuzzleLevelObjectiveData();
//            puzzleLevelObjectiveData.setId(puzzleLevelObjectiveDTO.getId());
//            puzzleLevelObjectiveData.setTitle(puzzleLevelObjectiveDTO.getTitle());
//            puzzleLevelObjectiveData.setCondition(puzzleLevelObjectiveDTO.getCondition());
//            puzzleLevelObjectiveData.setRewardAmount(puzzleLevelObjectiveDTO.getRewardAmount());
//            puzzleLevelObjectiveData.setSkillAmount(puzzleLevelObjectiveDTO.getSkillAmount());
//
//            puzzleLevelObjectiveDataCollection.add(puzzleLevelObjectiveData);
//        }
//        return puzzleLevelObjectiveDataCollection;
//    }
}
