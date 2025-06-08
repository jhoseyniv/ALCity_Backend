package com.alcity.dto.plimport.object;

import java.io.Serializable;
import java.util.Collection;

public class InstanceDataImport implements Serializable {

    private Long id;
    private String name;

    //private Long pgoId;

    private PositionImport position;
    private Collection<RecordDataImport> properties;
    private Collection<RecordDataImport> variables;

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

    public PositionImport getPosition() {
        return position;
    }

    public void setPosition(PositionImport position) {
        this.position = position;
    }

    public Collection<RecordDataImport> getVariables() {
        return variables;
    }

    public void setVariables(Collection<RecordDataImport> variables) {
        this.variables = variables;
    }

    public Collection<RecordDataImport> getProperties() {
        return properties;
    }

    public void setProperties(Collection<RecordDataImport> properties) {
        this.properties = properties;
    }

//    public Long getPgoId() {
//        return pgoId;
//    }
//
//    public void setPgoId(Long pgoId) {
//        this.pgoId = pgoId;
//    }

    public InstanceDataImport() {
    }

    public InstanceDataImport(Long id, String name, PositionImport position, Collection<RecordDataImport> properties) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.properties = properties;
       // this.pgoId = pgoId;
    }

}
