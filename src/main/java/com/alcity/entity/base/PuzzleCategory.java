package com.alcity.entity.base;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PuzzleCategory extends BaseItemSet implements Serializable {

    @OneToMany(mappedBy = "puzzleCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PuzzleGroup> puzzleGroupSet;

    public Set<PuzzleGroup> getPuzzleGroupSet() {
        return puzzleGroupSet;
    }

    public void setPuzzleGroupSet(Set<PuzzleGroup> puzzleGroupSet) {
        this.puzzleGroupSet = puzzleGroupSet;
    }

    public PuzzleCategory() {
    }

    public PuzzleCategory(String label, String value, Long version, Long created, Long updated,ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
