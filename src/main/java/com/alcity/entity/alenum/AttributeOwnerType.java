package com.alcity.entity.alenum;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

public enum AttributeOwnerType {

    PuzzleObject_Property,
    PuzzleObject_Parameter,
    PuzzleGroup_Object_Property,

    Puzzle_Level_Variable,
    PuzzleGroup_ObjectInstance_Property,
    PuzzleGroup_Object_Variable,
    PuzzleGroup_ObjectInstance_Variable
}
//    @OneToMany(mappedBy = "attributeOwnerType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<ALCityAttribute> attributeSet;
//
//
//    public AttributeOwnerType() {
//    }
//
//    public AttributeOwnerType(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
//        super(label, value, version, created, updated, createdBy, updatedBy);
//    }
//}
