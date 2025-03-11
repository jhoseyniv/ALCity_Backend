package com.alcity.dto.alobject;

public class AttributeValueDTOSave {
    private Long id;
    private Long longValue;
    private Float doubleValue;
    private Integer intValue;
    private Long binaryContentId;
    private String  expression;
    private String stringValue;
    private String objectValue;
    private Boolean booleanValue;
    private Long attributeId;
    private Long bindedAttributeId;

    private Long ownerId;

    private String ownerType;
    private Long newOwnerId;

    private String newOwnerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Float getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Float doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Long getBinaryContentId() {
        return binaryContentId;
    }

    public void setBinaryContentId(Long binaryContentId) {
        this.binaryContentId = binaryContentId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getBindedAttributeId() {
        return bindedAttributeId;
    }

    public void setBindedAttributeId(Long bindedAttributeId) {
        this.bindedAttributeId = bindedAttributeId;
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

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Long getNewOwnerId() {
        return newOwnerId;
    }

    public void setNewOwnerId(Long newOwnerId) {
        this.newOwnerId = newOwnerId;
    }

    public String getNewOwnerType() {
        return newOwnerType;
    }

    public void setNewOwnerType(String newOwnerType) {
        this.newOwnerType = newOwnerType;
    }

    public AttributeValueDTOSave(Long id, Long longValue, Float doubleValue, Integer intValue, Long binaryContentId, String expression, String stringValue, String objectValue, Boolean booleanValue, Long attributeId, Long bindedAttributeId, Long ownerId, String ownerType, Long newOwnerId, String newOwnerType) {
        this.id = id;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
        this.binaryContentId = binaryContentId;
        this.expression = expression;
        this.stringValue = stringValue;
        this.objectValue = objectValue;
        this.booleanValue = booleanValue;
        this.attributeId = attributeId;
        this.bindedAttributeId = bindedAttributeId;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.newOwnerId = newOwnerId;
        this.newOwnerType = newOwnerType;
    }
}
