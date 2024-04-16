package com.alcity.dto.Interpreter;

import com.alcity.dto.puzzle.PuzzleGroupObjectInstanceDTO;
import com.alcity.dto.puzzle.PuzzleGroup_PuzzleObjectDTO;
import com.alcity.dto.puzzle.PuzzleLevelObjectiveDTO;

import java.io.Serializable;
import java.util.Collection;

public class PuzzleLevelInterpreterDTO implements Serializable {
    private String code;
    private String name;
    private Integer cols;
    private Integer rows;
    private CameraSetupInterpreter cameraSetup;
    private Collection<PuzzleLevelObjectiveData> objectives;

    private  Collection<PuzzleGroupObjectInstanceDTO> instances;
    private Collection<PuzzleGroup_PuzzleObjectDTO> objects;


    public Collection<PuzzleLevelObjectiveData> getObjectives() {
        return objectives;
    }

    public void setObjectives(Collection<PuzzleLevelObjectiveData> objectives) {
        this.objectives = objectives;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }


    public CameraSetupInterpreter getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetupInterpreter cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    public Collection<PuzzleGroupObjectInstanceDTO> getInstances() {
        return instances;
    }

    public void setInstances(Collection<PuzzleGroupObjectInstanceDTO> instances) {
        this.instances = instances;
    }

    public Collection<PuzzleGroup_PuzzleObjectDTO> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PuzzleGroup_PuzzleObjectDTO> objects) {
        this.objects = objects;
    }

    public PuzzleLevelInterpreterDTO() {
    }

}
