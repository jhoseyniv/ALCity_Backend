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
    Puzzle_Object_Action_Parameter,
    PuzzleGroup_Object_Property,
    Puzzle_Level_Variable,
    PuzzleGroup_Object_Instance_Property,
    PuzzleGroup_Object_Variable,
    PuzzleGroup_Object_Instance_Variable,
    PuzzleGroup_Object_Instance_Action_Parameter,

    Puzzle_Level_Rule_Post_Action
}
