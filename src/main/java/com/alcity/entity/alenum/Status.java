package com.alcity.entity.alenum;

public enum Status {
    ok,
    info,
    warning,
    error;
    public static Status getById(long id)
    {
        for (Status e : Status.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static Status getByTitle(String title)
    {
        for (Status e : Status.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }
}
