package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.RecordData;
import com.alcity.dto.Interpreter.object.RuleData;

import java.io.Serializable;
import java.util.Collection;

public class PLData implements Serializable {
    private String code;
    private String name;

 //   private Long boardGraphicId;

    private Integer cols;
    private Integer rows;
   // private CameraSetupData cameraSetup;
    private Collection<PLObjectiveData> objectives;

    private Collection<RecordData> variables;

    private Collection<POData> objects;
    private Collection<RuleData> rules;


    public Collection<RecordData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordData> variables) {
        this.variables = variables;
    }

//    public Long getBoardGraphicId() {
//        return boardGraphicId;
//    }
//
//    public void setBoardGraphicId(Long boardGraphicId) {
//        this.boardGraphicId = boardGraphicId;
//    }

    public Collection<PLObjectiveData> getObjectives() {
        return objectives;
    }

    public void setObjectives(Collection<PLObjectiveData> objectives) {
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

//
//    public CameraSetupData getCameraSetup() {
//        return cameraSetup;
//    }
//
//    public void setCameraSetup(CameraSetupData cameraSetup) {
//        this.cameraSetup = cameraSetup;
//    }

    public Collection<RuleData> getRules() {
        return rules;
    }

    public void setRules(Collection<RuleData> rules) {
        this.rules = rules;
    }

    public Collection<POData> getObjects() {
        return objects;
    }

    public void setObjects(Collection<POData> objects) {
        this.objects = objects;
    }

    public PLData() {
    }

}
