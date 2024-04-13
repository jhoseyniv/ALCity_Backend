package com.alcity.dto.base;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;

public class PuzzleLevelDifficultyDTO extends BaseItemSet {

    public PuzzleLevelDifficultyDTO() {
    }

    public PuzzleLevelDifficultyDTO(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
