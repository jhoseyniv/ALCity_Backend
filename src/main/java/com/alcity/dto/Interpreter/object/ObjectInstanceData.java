package com.alcity.dto.Interpreter.object;

import java.util.Collection;

public class ObjectInstanceData {

    private Long id;
    private String name;

    private Position position;
    private Collection<ParameterData> properties;
    private Collection<ParameterData> variables;
    private Collection<ObjectActionData> actionsParameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Collection<ParameterData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<ParameterData> variables) {
        this.variables = variables;
    }

    public Collection<ParameterData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<ParameterData> properties) {
        this.properties = properties;
    }

    public Collection<ObjectActionData> getActionsParameters() {
        return actionsParameters;
    }

    public void setActionsParameters(Collection<ObjectActionData> actionsParameters) {
        this.actionsParameters = actionsParameters;
    }

    public ObjectInstanceData() {
    }

    public ObjectInstanceData(Long id, String name, Position position, Collection<ParameterData> properties, Collection<ObjectActionData> actionsParameters) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.properties = properties;
        this.actionsParameters = actionsParameters;
    }

}
