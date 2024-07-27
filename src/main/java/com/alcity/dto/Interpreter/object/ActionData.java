package com.alcity.dto.Interpreter.object;

import com.alcity.entity.alenum.ObjectAction;

import java.util.Collection;

public class ActionData {
    private Long id;
    private ObjectAction actionName;

    private String handler;

    private Collection<RecordData> parameters;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectAction getActionName() {
        return actionName;
    }

    public String getHandler() {
        return handler;
    }

    public Collection<RecordData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<RecordData> parameters) {
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

    public ActionData(Long id, ObjectAction actionName, String handler, Collection<RecordData> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
