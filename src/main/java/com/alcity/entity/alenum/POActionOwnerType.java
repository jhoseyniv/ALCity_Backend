package com.alcity.entity.alenum;


public enum POActionOwnerType {
    Object,
    Puzzle_Group_Object,
    Puzzle_Level_Instance;
    public static POActionOwnerType getById(Integer id)
    {
        for (POActionOwnerType e : POActionOwnerType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no id");
    }
    public static Long getOrdinalId(String name)
    {
        for (POActionOwnerType e : POActionOwnerType.values())
        {
            if (name.equalsIgnoreCase(e.toString())) return Long.valueOf(e.ordinal());
        }
        throw new IllegalArgumentException("no id");
    }
    public static POActionOwnerType getByTitle(String title)
    {
        for (POActionOwnerType e : POActionOwnerType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }



}
