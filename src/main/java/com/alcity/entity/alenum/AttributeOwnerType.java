package com.alcity.entity.alenum;


public enum AttributeOwnerType {

    Action_Renderer_Parameter, //ARP
    AlCity_Object,   //AO
    AlCity_Object_Property, //AOP
    AlCity_Object_Variable, //AOV
    ALCity_Object_In_Puzzle_Group, //AOinPG
    Puzzle_Object_Action_Parameter,//POAP

    Puzzle_Group_Object_Property, //PGOP
    Puzzle_Group_Object_Variable, //PGOV
    Puzzle_Level_Variable, //PLV
    Puzzle_Level_Instance_Property, //PLIP
    Puzzle_Level_Instance_Variable, //PLIV
    Puzzle_Level_Instance_Action_Parameter, //PLIAP

    Puzzle_Level_Rule_Post_Action; //PLRPA
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
