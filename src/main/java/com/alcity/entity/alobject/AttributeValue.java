package com.alcity.entity.alobject;

import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class AttributeValue extends BaseTable implements Serializable {


    @Column(name="booleanValue")
    private Boolean booleanValue;

    @Column(name="intValue")
    private Integer intValue;

    @Column(name="longValue")
    private Long longValue;

    @Column(name="stringValue")
    private String stringValue;

    @Column(name="objectValue")
    private String objectValue;

    @Column(name="doubleValue")
    private Float doubleValue;

    @Column(name="ownerId")
    private Long ownerId;
    @Column(name="binaryContentId")
    private Long binaryContentId;

    @Column(name="isExpression")
    private Boolean isExpression;

    @Column(name="expressionValue")
    private String expressionValue;

    @Enumerated(EnumType.ORDINAL)
    private AttributeOwnerType ownerType;


    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    //    @Column(name="binaryValueId")
//    private Long binaryValueId;

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Float getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Float doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Boolean getExpression() {
        return isExpression;
    }


    public void setExpression(Boolean expression) {
        isExpression = expression;
    }

    public Long getBinaryContentId() {
        return binaryContentId;
    }

    public void setBinaryContentId(Long binaryContentId) {
        this.binaryContentId = binaryContentId;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binded_attribute_id", nullable = false)
    @JsonIgnore
    private Attribute bindedAttributeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_id", nullable = false)
    @JsonIgnore
    private Attribute attributeId;

    public Attribute getBindedAttributeId() {
        return bindedAttributeId;
    }

    public void setBindedAttributeId(Attribute bindedAttributeId) {
        this.bindedAttributeId = bindedAttributeId;
    }

    public Attribute getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Attribute attributeId) {
        this.attributeId = attributeId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public AttributeOwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(AttributeOwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }

    public AttributeValue() {
    }

    public AttributeValue(Boolean booleanValue, Integer intValue, Long longValue, String stringValue, String objectValue, Float doubleValue, Long binaryContentId,String expressionValue,Boolean isExpression, Attribute bindedAttributeId, Attribute attributeId, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy,Long ownerId ,AttributeOwnerType ownerType) {
        super(version, created, updated, createdBy, updatedBy);
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.stringValue = stringValue;
        this.objectValue = objectValue;
        this.doubleValue = doubleValue;
        this.binaryContentId = binaryContentId;
        this.expressionValue = expressionValue;
        this.isExpression = isExpression;
        this.bindedAttributeId = bindedAttributeId;
        this.attributeId = attributeId;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
    }
}
