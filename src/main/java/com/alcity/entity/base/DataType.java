package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class DataType extends BaseTable implements Serializable {

    public DataType() {
    }

    public DataType(String label, String value, Long version, Long creationDate, Long lastModifiedDate, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, creationDate, lastModifiedDate, createdBy,updatedBy);
    }
}
