package com.alcity.dto.Interpreter.object;

import java.util.Collection;

public class ObjectActionData {
    private Long id;
    private String actionName;

    private String handler;

    private Collection<ParameterData> parameters;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public String getHandler() {
        return handler;
    }

    public Collection<ParameterData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<ParameterData> parameters) {
        this.parameters = parameters;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ObjectActionData() {
    }

    public ObjectActionData(Long id, String actionName,String handler,Collection<ParameterData> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
