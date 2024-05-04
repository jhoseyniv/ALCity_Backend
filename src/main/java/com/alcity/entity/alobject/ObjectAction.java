package com.alcity.entity.alobject;

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


    }
