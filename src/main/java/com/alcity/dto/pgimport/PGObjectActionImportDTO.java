package com.alcity.dto.pgimport;

import com.alcity.dto.plimpexport.AttributeData;

import java.util.Collection;

public class PGObjectActionImportDTO {
    private  Long id;
    private String actionName;
    private String handler;
    private Collection<AttributeData> parameters;

    public Collection<AttributeData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<AttributeData> parameters) {
        this.parameters = parameters;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public PGObjectActionImportDTO() {
    }

    public PGObjectActionImportDTO(Long id, String actionName,String handler, Collection<AttributeData> parameters) {
        this.id = id;
        this.actionName = actionName;
        this.handler = handler;
        this.parameters = parameters;
    }
}
