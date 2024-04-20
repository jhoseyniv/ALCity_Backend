package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.*;

import java.util.Collection;

public class PGData {
    private Long id;
    private Long version;
    private String title;
    private String code;
    private Long iconGraphicId;
    private Long imageGraphicId;
    private Collection<ActionData> actions;
    private Collection<RecordrData> variables;
    private Collection<RecordrData> properties;

    private  Collection<InstanceData> instances;


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

    public Collection<ActionData> getActions() {
        return actions;
    }

    public void setActions(Collection<ActionData> actions) {
        this.actions = actions;
    }

    public Collection<RecordrData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordrData> variables) {
        this.variables = variables;
    }

    public Collection<RecordrData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<RecordrData> properties) {
        this.properties = properties;
    }

    public Collection<InstanceData> getInstances() {
        return instances;
    }

    public void setInstances(Collection<InstanceData> instances) {
        this.instances = instances;
    }

    public PGData() {
    }

    public PGData(Long id, Long version, String title, String code, Long iconGraphicId, Long imageGraphicId, Collection<ActionData> actions) {
        this.id = id;
        this.version=version;
        this.title = title;
        this.code = code;
        this.iconGraphicId = iconGraphicId;
        this.imageGraphicId = imageGraphicId;
        this.actions = actions;
    }
}
