package com.alcity.dto.Interpreter.object;

import com.alcity.entity.alenum.ObjectAction;

import java.util.Collection;

public class ActionData {
    private Integer id;
    private ObjectAction actionName;

    private String handler;

    private Collection<RecordrData> parameters;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ObjectAction getActionName() {
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

    public void setActionName(ObjectAction actionName) {
        this.actionName = actionName;
    }

    public ActionData() {
    }

    public ActionData(Integer id, ObjectAction actionName, String handler, Collection<RecordrData> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
