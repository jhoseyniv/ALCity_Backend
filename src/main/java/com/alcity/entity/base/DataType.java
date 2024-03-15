package com.alcity.entity.base;

import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class DataType extends BaseItemSet implements Serializable {

    @OneToMany(mappedBy = "dataType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ALCityAttribute> attributeSet;


    public DataType() {
    }

    public DataType(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
