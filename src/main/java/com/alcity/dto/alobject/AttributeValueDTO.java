package com.alcity.dto.alobject;

import jakarta.persistence.criteria.CriteriaBuilder;

public class AttributeValueDTO {
    private Long id;
    private Long longValue;
    private Float doubleValue;
    private Integer intValue;
    private Long binaryContentId;
    private String stringValue;
    private String objectValue;

    private Boolean booleanValue;

    private Long attributeId;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

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

    public AttributeValueDTO() {
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public AttributeValueDTO(Long id,Boolean booleanValue ,Long longValue, Float doubleValue, Integer intValue, Long binaryContentId, String stringValue, String objectValue,Long attributeId) {
        this.id = id;
        this.booleanValue = booleanValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
        this.binaryContentId = binaryContentId;
        this.stringValue = stringValue;
        this.objectValue = objectValue;
        this.attributeId = attributeId;
    }
}
