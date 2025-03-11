package com.alcity.dto.alobject;

public class AttributeDTOSave {
    private Long id;
    private String name;
    private Long ownerId;
    private String ownerType;

    private String dataType;

    private AttributeValueDTOSave attributeValueDTOSave;



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

    public AttributeDTOSave() {
    }

    public AttributeValueDTOSave getAttributeValueDTOSave() {
        return attributeValueDTOSave;
    }

    public void setAttributeValueDTOSave(AttributeValueDTOSave attributeValueDTOSave) {
        this.attributeValueDTOSave = attributeValueDTOSave;
    }

    public AttributeDTOSave(Long id, String name, Long ownerId, String ownerType, String dataType, AttributeValueDTOSave attributeValueDTOSave ) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.dataType = dataType;
        this.attributeValueDTOSave = attributeValueDTOSave;
    }
}
