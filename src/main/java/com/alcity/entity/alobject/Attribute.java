package com.alcity.entity.alobject;


import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.users.AppMember;

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

    public Collection<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Collection<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @Enumerated(EnumType.ORDINAL)
    private AttributeOwnerType attributeOwnerType;

    @Enumerated(EnumType.ORDINAL)
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

    @OneToMany(mappedBy = "attributeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<AttributeValue> attributeValues;


    public Attribute() {
    }

    public Attribute(String name, Long ownerId, AttributeOwnerType attributeOwnerType, DataType dataType, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerType = attributeOwnerType;
        this.dataType = dataType;
    }
}
