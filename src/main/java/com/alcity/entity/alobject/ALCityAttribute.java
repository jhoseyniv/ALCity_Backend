package com.alcity.entity.alobject;


import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.DataType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class ALCityAttribute extends BaseTable implements Serializable {
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

    public Collection<ALCityAttributeValue> getAttributeValueSet() {
        return attributeValueSet;
    }

    public void setAttributeValueSet(Collection<ALCityAttributeValue> attributeValueSet) {
        this.attributeValueSet = attributeValueSet;
    }

    @Enumerated(EnumType.ORDINAL)
    private AttributeOwnerType attributeOwnerType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
    private Collection<ALCityAttributeValue> attributeValueSet;


    public ALCityAttribute() {
    }

    public ALCityAttribute(String name, Long ownerId, AttributeOwnerType attributeOwnerType, DataType dataType, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerType = attributeOwnerType;
        this.dataType = dataType;
    }
}
