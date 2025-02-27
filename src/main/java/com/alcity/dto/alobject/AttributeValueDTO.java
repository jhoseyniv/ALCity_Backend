package com.alcity.dto.alobject;

import com.alcity.entity.alenum.AttributeOwnerType;
import jakarta.persistence.criteria.CriteriaBuilder;

public class AttributeValueDTO {
    private Long id;
    private Long longValue;
    private Float doubleValue;
    private Integer intValue;
    private Long binaryContentId;
    private String  experssion;
    private String stringValue;
    private String objectValue;
    private Boolean booleanValue;
    private Long attributeId;

    private Long ownerId;

    private Integer ownerTypeId;

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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerTypeId() {
        return ownerTypeId;
    }

    public void setOwnerTypeId(Integer ownerTypeId) {
        this.ownerTypeId = ownerTypeId;
    }

    public String getExperssion() {
        return experssion;
    }

    public void setExperssion(String experssion) {
        this.experssion = experssion;
    }

    public AttributeValueDTO() {
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public AttributeValueDTO(Long id,Boolean booleanValue ,Long longValue, Float doubleValue, Integer intValue, Long binaryContentId,String experssion, String stringValue, String objectValue,Long attributeId,Long ownerId,Integer ownerTypeId) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerTypeId = ownerTypeId;
        this.booleanValue = booleanValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
        this.binaryContentId = binaryContentId;
        this.experssion = experssion;
        this.stringValue = stringValue;
        this.objectValue = objectValue;
        this.attributeId = attributeId;
    }
}
