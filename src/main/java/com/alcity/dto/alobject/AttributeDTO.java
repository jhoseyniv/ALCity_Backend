package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.DataTypeِDTO;

public class AttributeDTO extends BaseTableDTO {

    private String name;
    private Long ownerId;
    private AttributeOwnerTypeDTO attributeOwnerTypeDTO;
    private DataTypeِDTO dataTypeِDTO;

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

    public AttributeDTO(Long id, Long version, String created, String updated, String name, Long ownerId, AttributeOwnerTypeDTO attributeOwnerTypeDTO, DataTypeِDTO dataTypeِDTO) {
        super(id, version, created, updated);
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerTypeDTO = attributeOwnerTypeDTO;
        this.dataTypeِDTO = dataTypeِDTO;
    }
}
