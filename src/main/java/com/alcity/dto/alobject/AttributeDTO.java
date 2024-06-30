package com.alcity.dto.alobject;

import com.alcity.dto.base.DataTypeِDTO;

import java.util.Collection;

public class AttributeDTO  {
    private Long id;
    private String name;
    private Long ownerId;
    private String attributeOwnerType;
    private String dataType;

    private Collection<AttributeValueDTO> attributeValueDTOS;

    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getAttributeOwnerType() {
        return attributeOwnerType;
    }

    public void setAttributeOwnerType(String attributeOwnerType) {
        this.attributeOwnerType = attributeOwnerType;
    }

    public AttributeDTO() {
    }

    public Collection<AttributeValueDTO> getAttributeValueDTOS() {
        return attributeValueDTOS;
    }

    public void setAttributeValueDTO(  Collection<AttributeValueDTO> attributeValueDTOS) {
        this.attributeValueDTOS = attributeValueDTOS;
    }

    public AttributeDTO(Long id, String name, Long ownerId, String attributeOwnerType, String dataType, Collection<AttributeValueDTO> attributeValueDTOS,
                        Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerType = attributeOwnerType;
        this.dataType = dataType;
        this.attributeValueDTOS = attributeValueDTOS;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
