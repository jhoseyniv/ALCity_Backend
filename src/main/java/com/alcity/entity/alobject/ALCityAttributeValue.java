package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ALCityAttributeValue extends BaseTable implements Serializable {


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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "binary_content_id", nullable = true)
    @JsonIgnore
    private BinaryContent binaryContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binded_attribute_id", nullable = false)
    @JsonIgnore
    private ALCityAttribute bindedAttributeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_id", nullable = false)
    @JsonIgnore
    private ALCityAttribute attributeId;


    public ALCityAttributeValue() {
    }

    public ALCityAttributeValue(Boolean booleanValue, Integer intValue,Long longValue, String stringValue, Float doubleValue, BinaryContent binaryContent, ALCityAttribute bindedAttributeId, ALCityAttribute attributeId, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
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
