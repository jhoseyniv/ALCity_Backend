package com.alcity.entity.base;

import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class DataTypeOperator extends BaseItemSet implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_type_id", nullable = false)
    @JsonIgnore
    private DataType dataType;

    public DataTypeOperator() {
    }

    public DataTypeOperator(DataType dataType ,String label, String value, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.dataType = dataType;
    }

}
