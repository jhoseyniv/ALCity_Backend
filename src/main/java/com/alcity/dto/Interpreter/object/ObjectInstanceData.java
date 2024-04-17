package com.alcity.dto.Interpreter.object;

import java.util.Collection;

public class ObjectInstanceData {

    private Long id;
    private String name;
    private Integer x;
    private Integer y;
    private Integer z;
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

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
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

    public ObjectInstanceData(Long id, String name, Integer x, Integer y, Integer z, Collection<ParameterData> properties, Collection<ObjectActionData> actionsParameters) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.properties = properties;
        this.actionsParameters = actionsParameters;
    }

}
