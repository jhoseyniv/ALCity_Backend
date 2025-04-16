package com.alcity.dto.Interpreter.object;

import com.alcity.entity.alenum.ObjectActionType;

import java.io.Serializable;
import java.util.Collection;

public class ActionDataImport implements Serializable {
    private Long id;
    private ObjectActionType actionName;

    private String handler;

    private Collection<RecordDataImport> parameters;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectActionType getActionName() {
        return actionName;
    }

    public String getHandler() {
        return handler;
    }

    public Collection<RecordDataImport> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<RecordDataImport> parameters) {
        this.parameters = parameters;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setActionName(ObjectActionType actionName) {
        this.actionName = actionName;
    }

    public ActionDataImport() {
    }

    public ActionDataImport(Long id, ObjectActionType actionName, String handler, Collection<RecordDataImport> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
