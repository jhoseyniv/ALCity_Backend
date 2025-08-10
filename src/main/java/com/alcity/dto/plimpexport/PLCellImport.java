package com.alcity.dto.plimpexport;

import java.io.Serializable;
import java.util.Collection;

public class PLCellImport implements Serializable {
    private Long id;
    private PositionDTO position;
    private Collection<AttributeData> properties;
    private Collection<AttributeData> variables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public Collection<AttributeData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<AttributeData> properties) {
        this.properties = properties;
    }

    public Collection<AttributeData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<AttributeData> variables) {
        this.variables = variables;
    }

    public PLCellImport() {
    }

    public PLCellImport(Long id, PositionDTO position, Collection<AttributeData> properties, Collection<AttributeData> variables) {
        this.id = id;
        this.position = position;
        this.properties = properties;
        this.variables = variables;
    }

}
