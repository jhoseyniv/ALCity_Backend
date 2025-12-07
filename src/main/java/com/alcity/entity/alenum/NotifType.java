package com.alcity.entity.alenum;

public enum NotifType {
    Challenge,
    Info,
    Work;

    public static NotifType getById(long id)
    {
        for (NotifType e : NotifType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    public static NotifType getByTitle(String title)
    {
        for (NotifType e : NotifType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
