package com.alcity.entity.alenum;

public enum ObjectActionType {

    Move,
    Remove,
    Rotate,
    Show,
    Hide,
    Enable,
    Disable,
    Create,
    Convert,
    PlaySound,
    StopMoving,
    CreateTimer,
    StopTimer,
    Gamefinished,
    Flip
    ;
    public static ObjectActionType getById(Integer id)
    {
        for (ObjectActionType e : ObjectActionType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no id");
    }
    public static Long getOrdinalId(String name)
    {
        for (ObjectActionType e : ObjectActionType.values())
        {
            if (name.equalsIgnoreCase(e.toString())) return Long.valueOf(e.ordinal());
        }
        throw new IllegalArgumentException("no id");
    }
    public static ObjectActionType getByTitle(String title)
    {
        for (ObjectActionType e : ObjectActionType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
