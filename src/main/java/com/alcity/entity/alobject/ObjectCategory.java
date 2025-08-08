package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;


@Entity
public class ObjectCategory extends BaseItemSet implements Serializable {


    @OneToMany(mappedBy = "objectCategory", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<BaseObject> puzzleObjectCollection;

    public Collection<BaseObject> getPuzzleObjectCollection() {
        return puzzleObjectCollection;
    }

    public void setPuzzleObjectCollection(Collection<BaseObject> puzzleObjectCollection) {
        this.puzzleObjectCollection = puzzleObjectCollection;
    }

    public ObjectCategory() {
    }

    public ObjectCategory(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
