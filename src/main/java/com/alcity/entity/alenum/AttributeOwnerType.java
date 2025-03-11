package com.alcity.entity.alenum;


public enum AttributeOwnerType {

    Action_Handler_Parameter, //AHP
    //Object_Bundle,
    Object_Action_Handler_Parameter,//OAHP
    Object_Property, //OP
    Object_Variable, //OV
    Puzzle_Group_Object,
    Puzzle_Group_Object_Action_Handler_Parameter,
    Puzzle_Group_Object_Property, //PGOP
    Puzzle_Group_Object_Variable, //PGOV

    Instance_Puzzle_Group_Object,
    Instance_Puzzle_Group_Object_Action_Handler_Parameter,
    Instance_Puzzle_Group_Object_Property, //PGOP
    Instance_Puzzle_Group_Object_Variable,

    Puzzle_Level_Variable,

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
