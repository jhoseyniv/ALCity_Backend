package com.alcity.dto.pgimport;

import com.alcity.dto.alobject.AttributeDTOSave;
import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.entity.alobject.Attribute;

import java.util.Collection;

public class PGObjectImportDTO {

    private Long id;
    private Long objectId;
    private String code;
    private String title;

    private Collection<AttributeData> variables;
    private Collection<AttributeData> properties;
    private Collection<PGObjectActionImportDTO> actions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<AttributeData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<AttributeData> variables) {
        this.variables = variables;
    }

    public Collection<PGObjectActionImportDTO> getActions() {
        return actions;
    }

    public void setActions(Collection<PGObjectActionImportDTO> actions) {
        this.actions = actions;
    }

    public Collection<AttributeData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<AttributeData> properties) {
        this.properties = properties;
    }

    public PGObjectImportDTO() {
    }

    public PGObjectImportDTO(Long id, Long objectId, String code, String title,
                             Collection<AttributeData> variables,Collection<AttributeData> properties, Collection<PGObjectActionImportDTO> actions) {
        this.id = id;
        this.objectId = objectId;
        this.code = code;
        this.title = title;
        this.variables = variables;
        this.properties = properties;
        this.actions = actions;
    }
}
