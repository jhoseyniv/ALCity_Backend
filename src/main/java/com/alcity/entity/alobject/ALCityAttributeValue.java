package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ALCityAttributeValue extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute__id", nullable = false)
    @JsonIgnore
    private ALCityAttribute attribute;

    @Column(name="booleanValue")
    private Boolean booleanValue;

    @Column(name="intValue")
    private Integer intValue;

    @Column(name="stringValue")
    private String stringValue;

    @Column(name="doubleValue")
    private Float doubleValue;

    @Column(name="binaryValueId")
    private Long binaryValueId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent binaryContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binded_attribute__id", nullable = false)
    @JsonIgnore
    private ALCityAttribute bindedAttributeId;

    public ALCityAttributeValue() {
    }

    public ALCityAttributeValue(Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy, ALCityAttribute attribute, Boolean booleanValue, Integer intValue, String stringValue, Float doubleValue, Long binaryValueId, BinaryContent binaryContent) {
        super(version, created, updated, createdBy, updatedBy);
        this.attribute = attribute;
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.doubleValue = doubleValue;
        this.binaryValueId = binaryValueId;
        this.binaryContent = binaryContent;
    }
}
