package com.alcity.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
public class PuzzleCategory extends BaseTable implements Serializable {
    @Column(name="label")
    private String label;

    @Column(name="value")
    private String value;

    public PuzzleCategory() {
    }

    public PuzzleCategory(Long version, Long creationDate, Long creatorUser, Long lastModifiedDate, Long lastModifiedUser, String label, String value) {
        super(version, creationDate, creatorUser, lastModifiedDate, lastModifiedUser);
        this.label = label;
        this.value = value;
    }
}
