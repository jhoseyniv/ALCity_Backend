package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity

public class PuzzleLevelStatus  extends BaseTable implements Serializable {

    public PuzzleLevelStatus() {
    }

    public PuzzleLevelStatus(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
