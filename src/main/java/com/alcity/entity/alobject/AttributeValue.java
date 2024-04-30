package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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

    @Column(name="doubleValue")
    private Float doubleValue;

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

    public BinaryContent getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(BinaryContent binaryContent) {
        this.binaryContent = binaryContent;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "binary_content_id", nullable = true)
    @JsonIgnore
    private BinaryContent binaryContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binded_attribute_id", nullable = false)
    @JsonIgnore
    private Attribute bindedAttributeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_id", nullable = false)
    @JsonIgnore
    private Attribute attributeId;


    public AttributeValue() {
    }

    public AttributeValue(Boolean booleanValue, Integer intValue, Long longValue, String stringValue, Float doubleValue, BinaryContent binaryContent, Attribute bindedAttributeId, Attribute attributeId, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.stringValue = stringValue;
        this.doubleValue = doubleValue;
        this.binaryContent = binaryContent;
        this.bindedAttributeId = bindedAttributeId;
        this.attributeId = attributeId;
    }
}
