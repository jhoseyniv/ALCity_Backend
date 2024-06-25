package com.alcity.entity.alenum;


public enum PLStatus {
    Completed,
    Canceled,
    Ongoing,
    Not_Started;

    public static PLStatus getById(long id)
    {
        for (PLStatus e : PLStatus.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    public static PLStatus getByTitle(String title)
    {
        for (PLStatus e : PLStatus.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }


}
