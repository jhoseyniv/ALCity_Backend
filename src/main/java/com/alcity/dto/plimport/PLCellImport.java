package com.alcity.dto.plimport;

import com.alcity.dto.plexport.object.Position;
import com.alcity.dto.plimport.object.RecordDataImport;

import java.io.Serializable;
import java.util.Collection;

public class PLCellImport implements Serializable {
    private Long id;
    private Position position;
    private Collection<RecordDataImport> properties;
    private Collection<RecordDataImport> variables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Collection<RecordDataImport> getProperties() {
        return properties;
    }

    public void setProperties(Collection<RecordDataImport> properties) {
        this.properties = properties;
    }

    public Collection<RecordDataImport> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordDataImport> variables) {
        this.variables = variables;
    }

    public PLCellImport() {
    }

    public PLCellImport(Long id, Position position, Collection<RecordDataImport> properties, Collection<RecordDataImport> variables) {
        this.id = id;
        this.position = position;
        this.properties = properties;
        this.variables = variables;
    }

}
