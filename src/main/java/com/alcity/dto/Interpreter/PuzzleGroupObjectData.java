package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.ObjectActionData;
import com.alcity.dto.Interpreter.object.ObjectPropertiesData;
import com.alcity.dto.Interpreter.object.ObjectVariableData;
import com.alcity.dto.puzzle.PuzzleGroupObjectInstanceDTO;

import java.util.Collection;

public class PuzzleGroupObjectData {
    private Long id;
    private Long version;
    private String title;
    private String code;
    private Long iconGraphicId;
    private Long imageGraphicId;
    private Collection<ObjectActionData> actions;
    private Collection<ObjectVariableData> variables;
    private Collection<ObjectPropertiesData> properties;

    private  Collection<PuzzleGroupObjectInstanceDTO> instances;


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

    public Collection<ObjectActionData> getActions() {
        return actions;
    }

    public void setActions(Collection<ObjectActionData> actions) {
        this.actions = actions;
    }

    public Collection<ObjectVariableData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<ObjectVariableData> variables) {
        this.variables = variables;
    }

    public Collection<ObjectPropertiesData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<ObjectPropertiesData> properties) {
        this.properties = properties;
    }

    public Collection<PuzzleGroupObjectInstanceDTO> getInstances() {
        return instances;
    }

    public void setInstances(Collection<PuzzleGroupObjectInstanceDTO> instances) {
        this.instances = instances;
    }

    public PuzzleGroupObjectData() {
    }

    public PuzzleGroupObjectData(Long id,Long version, String title, String code, Long iconGraphicId, Long imageGraphicId, Collection<ObjectActionData> actions) {
        this.id = id;
        this.version=version;
        this.title = title;
        this.code = code;
        this.iconGraphicId = iconGraphicId;
        this.imageGraphicId = imageGraphicId;
        this.actions = actions;
    }
}
