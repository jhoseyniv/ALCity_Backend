package com.alcity.entity.alobject;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.DataType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    public Set<ALCityAttributeValue> getAttributeValueSet() {
        return attributeValueSet;
    }

    public void setAttributeValueSet(Set<ALCityAttributeValue> attributeValueSet) {
        this.attributeValueSet = attributeValueSet;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_owner_type_id", nullable = false)
    @JsonIgnore
    private AttributeOwnerType attributeOwnerType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_type_id", nullable = false)
    @JsonIgnore
    private DataType dataType;


    @OneToMany(mappedBy = "attributeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ALCityAttributeValue> attributeValueSet;


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