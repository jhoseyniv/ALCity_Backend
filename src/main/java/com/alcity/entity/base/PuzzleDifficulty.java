package com.alcity.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity

public class PuzzleDifficulty extends  BaseTable  implements Serializable {

    public PuzzleDifficulty() {
    }

    public PuzzleDifficulty(String label, String value, Long version, Long creationDate, Long lastModifiedDate, ALCitySystemUser creatorUser, ALCitySystemUser lastModifiedUser) {
        super(label, value, version, creationDate, lastModifiedDate, creatorUser, lastModifiedUser);
    }
}
