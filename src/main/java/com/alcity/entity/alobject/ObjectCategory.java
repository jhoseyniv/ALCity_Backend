package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.users.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;


@Entity
public class ObjectCategory extends BaseItemSet implements Serializable {


    @OneToMany(mappedBy = "objectCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<ALCityObject> puzzleObjectCollection;

    public Collection<ALCityObject> getPuzzleObjectCollection() {
        return puzzleObjectCollection;
    }

    public void setPuzzleObjectCollection(Collection<ALCityObject> puzzleObjectCollection) {
        this.puzzleObjectCollection = puzzleObjectCollection;
    }

    public ObjectCategory() {
    }

    public ObjectCategory(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
