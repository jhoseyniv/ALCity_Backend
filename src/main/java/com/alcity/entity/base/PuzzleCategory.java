package com.alcity.entity.base;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class PuzzleCategory extends BaseItemSet implements Serializable {

    @OneToMany(mappedBy = "puzzleCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PuzzleGroup> puzzleGroupCollection;

    public Collection<PuzzleGroup> getPuzzleGroupCollection() {
        return puzzleGroupCollection;
    }

    public void setPuzzleGroupCollection(Collection<PuzzleGroup> puzzleGroupCollection) {
        this.puzzleGroupCollection = puzzleGroupCollection;
    }

    public PuzzleCategory() {
    }

    public PuzzleCategory(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
