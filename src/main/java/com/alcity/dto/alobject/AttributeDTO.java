package com.alcity.dto.alobject;

import com.alcity.dto.base.DataTypeِDTO;

import java.util.Collection;

public class AttributeDTO  {
    private Long id;
    private String name;
    private Long ownerId;
    private String ownerType;

    private String dataType;

    private AttributeValueDTO attributeValueDTO;



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

    public String getOwnerType() {
        return ownerType;
    }
//sdfsf
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public AttributeDTO() {
    }

    public AttributeValueDTO getAttributeValueDTO() {
        return attributeValueDTO;
    }

    public void setAttributeValueDTO(AttributeValueDTO attributeValueDTO) {
        this.attributeValueDTO = attributeValueDTO;
    }

    public AttributeDTO(Long id, String name, Long ownerId, String ownerType, String dataType, AttributeValueDTO attributeValueDTO ) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.dataType = dataType;
        this.attributeValueDTO = attributeValueDTO;
    }
}
