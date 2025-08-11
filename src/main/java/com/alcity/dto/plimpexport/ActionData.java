package com.alcity.dto.plimpexport;

import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.entity.alenum.ObjectActionType;

import java.io.Serializable;
import java.util.Collection;

public class ActionData implements Serializable {
    private static final long serialVersionUID = -4477809924195151524L;
    private Long id;
    private ObjectActionType actionName;

    private String handler;

    private Collection<AttributeData> parameters;
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

    public Collection<AttributeData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<AttributeData> parameters) {
        this.parameters = parameters;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setActionName(ObjectActionType actionName) {
        this.actionName = actionName;
    }

    public ActionData() {
    }

    public ActionData(Long id, ObjectActionType actionName, String handler, Collection<AttributeData> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
