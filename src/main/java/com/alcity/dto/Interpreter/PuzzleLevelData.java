package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.RuleData;

import java.io.Serializable;
import java.util.Collection;

public class PuzzleLevelData implements Serializable {
    private String code;
    private String name;
    private Integer cols;
    private Integer rows;
    private CameraSetupData cameraSetup;
    private Collection<PuzzleLevelObjectiveData> objectives;

    private Collection<PuzzleGroupObjectData> objects;
    private Collection<RuleData> rules;


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


    public CameraSetupData getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetupData cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    public Collection<RuleData> getRules() {
        return rules;
    }

    public void setRules(Collection<RuleData> rules) {
        this.rules = rules;
    }

    public Collection<PuzzleGroupObjectData> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PuzzleGroupObjectData> objects) {
        this.objects = objects;
    }

    public PuzzleLevelData() {
    }

}
