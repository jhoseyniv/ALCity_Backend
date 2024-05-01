package com.alcity.dto.alobject;

import com.alcity.dto.base.DataTypeِDTO;

public class AttributeDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String name;
    private Long ownerId;
    private AttributeOwnerTypeDTO attributeOwnerTypeDTO;
    private DataTypeِDTO dataTypeِDTO;


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

    public AttributeOwnerTypeDTO getAttributeOwnerTypeDTO() {
        return attributeOwnerTypeDTO;
    }

    public void setAttributeOwnerTypeDTO(AttributeOwnerTypeDTO attributeOwnerTypeDTO) {
        this.attributeOwnerTypeDTO = attributeOwnerTypeDTO;
    }

    public DataTypeِDTO getDataTypeِDTO() {
        return dataTypeِDTO;
    }

    public void setDataTypeِDTO(DataTypeِDTO dataTypeِDTO) {
        this.dataTypeِDTO = dataTypeِDTO;
    }

    public AttributeDTO() {
    }

    public AttributeDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String name, Long ownerId, AttributeOwnerTypeDTO attributeOwnerTypeDTO, DataTypeِDTO dataTypeِDTO) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerTypeDTO = attributeOwnerTypeDTO;
        this.dataTypeِDTO = dataTypeِDTO;
    }
}
