package com.alcity.entity.alenum;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


public enum AttributeOwnerType {

    Action_Renderer_Parameter,
    AlCity_Object,
    AlCity_Object_Property,
    ALCity_Object_In_Puzzle_Group,
    Puzzle_Object_Action_Parameter,

    Puzzle_Group_Object_Property,
    Puzzle_Group_Object_Variable,
    Puzzle_Level_Variable,
    Puzzle_Level_Instance_Property,
    Puzzle_Level_Instance_Variable,
    Puzzle_Level_Instance_Action_Parameter,

    Puzzle_Level_Rule_Post_Action;
    public static AttributeOwnerType getById(long id)
    {
        for (AttributeOwnerType e : AttributeOwnerType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static AttributeOwnerType getByTitle(String title)
    {
        for (AttributeOwnerType e : AttributeOwnerType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }
}
