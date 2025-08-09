package com.alcity.dto.pl;

import com.alcity.dto.pl.pexport.RecordData;

import java.io.Serializable;
import java.util.Collection;

public class PLCellData implements Serializable {
    private Long id;
    private PositionDTO position;
    private Collection<RecordData> properties;

    private Collection<RecordData> variables;

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

    public Collection<RecordData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<RecordData> properties) {
        this.properties = properties;
    }

    public Collection<RecordData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordData> variables) {
        this.variables = variables;
    }

    public PLCellData() {
    }

    public PLCellData(Long id, PositionDTO position, Collection<RecordData> properties, Collection<RecordData> variables) {
        this.id = id;
        this.position = position;
        this.properties = properties;
        this.variables = variables;
    }
}
