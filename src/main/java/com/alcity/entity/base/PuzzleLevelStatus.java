package com.alcity.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity

public class PuzzleLevelStatus extends BaseTable implements Serializable {
    @Column(name="label")
    private String label;

    @Column(name="value")
    private String value;
}
