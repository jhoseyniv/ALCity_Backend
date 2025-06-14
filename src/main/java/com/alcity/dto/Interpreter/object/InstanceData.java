package com.alcity.dto.Interpreter.object;

import java.io.Serializable;
import java.util.Collection;

public class InstanceData implements Serializable {
    private static final long serialVersionUID = 1184949731931955686L;

    private Long id;
    private Long pgoId;
    private String name;

    private Position position;
    private Collection<RecordData> properties;
    private Collection<RecordData> variables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Collection<RecordData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordData> variables) {
        this.variables = variables;
    }

    public Collection<RecordData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<RecordData> properties) {
        this.properties = properties;
    }

    public Long getPgoId() {
        return pgoId;
    }

    public void setPgoId(Long pgoId) {
        this.pgoId = pgoId;
    }

    public InstanceData() {
    }

    public InstanceData(Long id, String name,Long pgoId, Position position, Collection<RecordData> properties) {
        this.id = id;
        this.name = name;
        this.pgoId = pgoId;
        this.position = position;
        this.properties = properties;
    }

}
