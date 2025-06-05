package com.alcity.dto.plimport;

import com.alcity.dto.plimport.object.ActionDataImport;
import com.alcity.dto.plimport.object.InstanceDataImport;
import com.alcity.dto.plimport.object.RecordDataImport;

import java.io.Serializable;
import java.util.Collection;

public class PLObjectImport implements Serializable {
    private Long id;
    private Long version;
    private String title;
    private String code;
    private Long iconGraphicId;
    private Long imageGraphicId;
    private Collection<ActionDataImport> actions;
    private Collection<RecordDataImport> variables;
    private Collection<RecordDataImport> properties;

    private  Collection<InstanceDataImport> instances;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getIconGraphicId() {
        return iconGraphicId;
    }

    public void setIconGraphicId(Long iconGraphicId) {
        this.iconGraphicId = iconGraphicId;
    }

    public Long getImageGraphicId() {
        return imageGraphicId;
    }

    public void setImageGraphicId(Long imageGraphicId) {
        this.imageGraphicId = imageGraphicId;
    }

    public Collection<ActionDataImport> getActions() {
        return actions;
    }

    public void setActions(Collection<ActionDataImport> actions) {
        this.actions = actions;
    }

    public Collection<RecordDataImport> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordDataImport> variables) {
        this.variables = variables;
    }

    public Collection<RecordDataImport> getProperties() {
        return properties;
    }

    public void setProperties(Collection<RecordDataImport> properties) {
        this.properties = properties;
    }

    public Collection<InstanceDataImport> getInstances() {
        return instances;
    }

    public void setInstances(Collection<InstanceDataImport> instances) {
        this.instances = instances;
    }

    public PLObjectImport() {
    }

    public PLObjectImport(Long id, Long version, String title, String code, Long iconGraphicId, Long imageGraphicId, Collection<ActionDataImport> actions) {
        this.id = id;
        this.version=version;
        this.title = title;
        this.code = code;
        this.iconGraphicId = iconGraphicId;
        this.imageGraphicId = imageGraphicId;
        this.actions = actions;
    }
}
