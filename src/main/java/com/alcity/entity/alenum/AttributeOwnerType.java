package com.alcity.entity.alenum;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


public enum AttributeOwnerType {

    PuzzleObject_Property,
    Puzzle_Object_Action_Parameter,

    Puzzle_Group_Object_Property,
    Puzzle_Group_Object_Variable,
    Puzzle_Level_Variable,
    PuzzleGroup_Object_Instance_Property,
    PuzzleGroup_Object_Instance_Variable,
    PuzzleGroup_Object_Instance_Action_Parameter,

    Puzzle_Level_Rule_Post_Action
}
