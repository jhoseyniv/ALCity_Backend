package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;

import javax.persistence.*;
import java.io.Serializable;

@Entity

public class PuzzleLevelDifficulty extends  BaseTable  implements Serializable {

    public PuzzleLevelDifficulty() {
    }

    public PuzzleLevelDifficulty(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
