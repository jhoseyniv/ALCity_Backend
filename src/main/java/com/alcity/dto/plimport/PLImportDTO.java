package com.alcity.dto.plimport;

import com.alcity.dto.plimport.object.RecordDataImport;
import com.alcity.dto.plimport.object.RuleDataImport;

import java.io.Serializable;
import java.util.Collection;

public class PLImportDTO implements Serializable {
    private Long id;
    private String code;
    private String name;

 //   private Long boardGraphicId;

    private Integer cols;
    private Integer rows;
    private CameraSetupImport cameraSetup;
    private Collection<PLObjectiveImport> objectives;

    private Collection<RecordDataImport> variables;

    private Collection<PLObjectImport> objects;
    private Collection<RuleDataImport> rules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<RecordDataImport> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordDataImport> variables) {
        this.variables = variables;
    }


    public Collection<PLObjectiveImport> getObjectives() {
        return objectives;
    }

    public void setObjectives(Collection<PLObjectiveImport> objectives) {
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


    public CameraSetupImport getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetupImport cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    public Collection<RuleDataImport> getRules() {
        return rules;
    }

    public void setRules(Collection<RuleDataImport> rules) {
        this.rules = rules;
    }

    public Collection<PLObjectImport> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PLObjectImport> objects) {
        this.objects = objects;
    }

    public PLImportDTO() {
    }

    public PLImportDTO(Long id, String code, String name, Integer cols, Integer rows) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.cols = cols;
        this.rows = rows;
    }
}
