package com.alcity.dto.plexport;


import com.alcity.dto.plexport.object.RecordData;
import com.alcity.dto.plexport.object.RuleData;
import java.io.Serializable;
import java.util.Collection;

public class PLData implements Serializable {
    private static final long serialVersionUID = 6135931752455279053L;
    private String code;
    private String name;

   // private Long boardGraphicId;

    private Integer cols;
    private Integer rows;
    private CameraSetupData cameraSetup;
    private Collection<PLObjectiveData> objectives;

    private Collection<RecordData> variables;

    private Collection<POData> objects;

    private Collection<PLCellData> cells;

    private Collection<RuleData> rules;


    public Collection<RecordData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordData> variables) {
        this.variables = variables;
    }


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

    public Collection<POData> getObjects() {
        return objects;
    }

    public void setObjects(Collection<POData> objects) {
        this.objects = objects;
    }

    public Collection<PLCellData> getCells() {
        return cells;
    }

    public void setCells(Collection<PLCellData> cells) {
        this.cells = cells;
    }

    public PLData() {
    }

}
