package com.alcity.dto.plimpexport;

import java.io.Serializable;
import java.util.Collection;

public class InstanceData implements Serializable {
    private static final long serialVersionUID = 1184949731931955686L;

    private Long id;
    private Long pgoId;
    private String name;

    private PostionIntDTO position;
    private Collection<AttributeData> properties;
    private Collection<AttributeData> variables;

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

    public PostionIntDTO getPosition() {
        return position;
    }

    public void setPosition(PostionIntDTO position) {
        this.position = position;
    }

    public Collection<AttributeData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<AttributeData> variables) {
        this.variables = variables;
    }

    public Collection<AttributeData> getProperties() {
        return properties;
    }

    public void setProperties(Collection<AttributeData> properties) {
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

    public InstanceData(Long id, String name, Long pgoId, PostionIntDTO position, Collection<AttributeData> properties) {
        this.id = id;
        this.name = name;
        this.pgoId = pgoId;
        this.position = position;
        this.properties = properties;
    }

}
