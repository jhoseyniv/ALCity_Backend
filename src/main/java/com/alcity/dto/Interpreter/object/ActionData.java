package com.alcity.dto.Interpreter.object;

import java.util.Collection;

public class ActionData {
    private Long id;
    private String actionName;

    private String handler;

    private Collection<RecordrData> parameters;
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

    public Collection<RecordrData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<RecordrData> parameters) {
        this.parameters = parameters;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ActionData() {
    }

    public ActionData(Long id, String actionName, String handler, Collection<RecordrData> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
