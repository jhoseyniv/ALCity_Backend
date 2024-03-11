package com.alcity.entity.puzzle;


import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class PuzzleLevel extends RecordInformation implements Serializable {
    public PuzzleLevel() {
    }

    public PuzzleLevel(Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
    }
}
