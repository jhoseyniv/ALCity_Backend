package com.alcity.dto.pgimport;

import java.util.Collection;

public class PGObjectImportDTO {

    private Long id;
    private Long objectId;
    private String code;
    private String title;

    private Collection<PGObjectVariableImportDTO> properties;
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

    public Collection<PGObjectVariableImportDTO> getProperties() {
        return properties;
    }

    public void setProperties(Collection<PGObjectVariableImportDTO> properties) {
        this.properties = properties;
    }

    public Collection<PGObjectActionImportDTO> getActions() {
        return actions;
    }

    public void setActions(Collection<PGObjectActionImportDTO> actions) {
        this.actions = actions;
    }

    public PGObjectImportDTO() {
    }

    public PGObjectImportDTO(Long id, Long objectId, String code, String title,
                             Collection<PGObjectVariableImportDTO> properties, Collection<PGObjectActionImportDTO> actions) {
        this.id = id;
        this.objectId = objectId;
        this.code = code;
        this.title = title;
        this.properties = properties;
        this.actions = actions;
    }
}
