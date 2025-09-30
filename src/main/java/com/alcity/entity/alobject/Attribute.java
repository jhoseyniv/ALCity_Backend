package com.alcity.entity.alobject;


import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attribute extends BaseTable implements Serializable {
    @Column(name="name" )
    private String name;

    @Column(name="ownerId")
    private Long ownerId;

    @Enumerated(EnumType.ORDINAL)
    private AttributeOwnerType attributeOwnerType;

    @Enumerated(EnumType.ORDINAL)
    private DataType dataType;

    @OneToMany(mappedBy = "attributeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<AttributeValue> attributeValues;

    public Attribute(String name, Long ownerId, AttributeOwnerType attributeOwnerType, DataType dataType, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.ownerId = ownerId;
        this.attributeOwnerType = attributeOwnerType;
        this.dataType = dataType;
    }
}
