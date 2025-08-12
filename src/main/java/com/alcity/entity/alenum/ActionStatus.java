package com.alcity.entity.alenum;

public enum ActionStatus {
    OK,
    Info,
    Error;
    public static ActionStatus getById(long id)
    {
        for (ActionStatus e : ActionStatus.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static ActionStatus getByTitle(String title)
    {
        for (ActionStatus e : ActionStatus.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }
}
