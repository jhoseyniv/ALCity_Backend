package com.alcity.dto.importjson;

import com.alcity.dto.importjson.importobject.RecordDataImport;
import com.alcity.dto.importjson.importobject.RuleDataImport;

import java.io.Serializable;
import java.util.Collection;

public class PLDataImport implements Serializable {
    private String code;
    private String name;

 //   private Long boardGraphicId;

    private Integer cols;
    private Integer rows;
    private CameraSetupDataImport cameraSetup;
    private Collection<PLObjectiveDataImport> objectives;

    private Collection<RecordDataImport> variables;

    private Collection<PODataImport> objects;
    private Collection<RuleDataImport> rules;


    public Collection<RecordDataImport> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordDataImport> variables) {
        this.variables = variables;
    }


    public Collection<PLObjectiveDataImport> getObjectives() {
        return objectives;
    }

    public void setObjectives(Collection<PLObjectiveDataImport> objectives) {
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


    public CameraSetupDataImport getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetupDataImport cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    public Collection<RuleDataImport> getRules() {
        return rules;
    }

    public void setRules(Collection<RuleDataImport> rules) {
        this.rules = rules;
    }

    public Collection<PODataImport> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PODataImport> objects) {
        this.objects = objects;
    }

    public PLDataImport() {
    }

}
