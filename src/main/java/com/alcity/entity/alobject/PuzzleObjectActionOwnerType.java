package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PuzzleObjectActionOwnerType extends BaseItemSet implements Serializable {


    @OneToMany(mappedBy = "puzzleObjectActionOwnerType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleObject_ObjectAction> puzzleObject_objectActionSet;


    public PuzzleObjectActionOwnerType() {
    }

    public PuzzleObjectActionOwnerType(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
