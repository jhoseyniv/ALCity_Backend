package com.alcity.entity.alenum;

public enum   ObjectAction  {

    Move,
    Remove,
    Rotate,
    Enable,
    Disable,
    Hide,
    Create,
    Convert,
    Show,
    PlaySound;
    public static ObjectAction getById(Integer id)
    {
        for (ObjectAction e : ObjectAction.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no id");
    }
    public static Long getOrdinalId(String name)
    {
        for (ObjectAction e : ObjectAction.values())
        {
            if (name.equalsIgnoreCase(e.toString())) return Long.valueOf(e.ordinal());
        }
        throw new IllegalArgumentException("no id");
    }

    }
