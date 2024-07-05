package com.alcity.entity.base;

import com.alcity.entity.alenum.DataType;
import com.alcity.entity.users.AppMember;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class DataTypeOperator extends BaseItemSet implements Serializable {

    @Enumerated(EnumType.ORDINAL)
    private DataType dataType;

    public DataTypeOperator() {
    }

    public DataTypeOperator(DataType dataType , String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.dataType = dataType;
    }

}
