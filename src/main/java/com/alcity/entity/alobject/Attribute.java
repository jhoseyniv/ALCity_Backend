package com.alcity.entity.alobject;


import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.DataType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Attribute extends BaseTable implements Serializable {
    @Column(name="name" )
    private String name;

    @Column(name="ownerId")
    private Long ownerId;

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

    public Collection<AttributeValue> getAttributeValueSet() {
        return attributeValueSet;
    }

    public void setAttributeValueSet(Collection<AttributeValue> attributeValueSet) {
        this.attributeValueSet = attributeValueSet;
    }

    @Enumerated(EnumType.ORDINAL)
    private AttributeOwnerType attributeOwnerType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "data_type_id", nullable = false)
    @JsonIgnore
    private DataType dataType;

    public AttributeOwnerType getAttributeOwnerType() {
        return attributeOwnerType;
    }

    public void setAttributeOwnerType(AttributeOwnerType attributeOwnerType) {
        this.attributeOwnerType = attributeOwnerType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @OneToMany(mappedBy = "attributeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<AttributeValue> attributeValueSet;


    public Attribute() {
    }

    public Attribute(String name, Long ownerId, AttributeOwnerType attributeOwnerType, DataType dataType, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerType = attributeOwnerType;
        this.dataType = dataType;
    }
}
