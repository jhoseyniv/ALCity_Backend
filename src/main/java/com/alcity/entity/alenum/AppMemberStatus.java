package com.alcity.entity.alenum;

public enum AppMemberStatus {

    Active ,
    DeActive,
    Banned;
    public static AppMemberStatus getById(long id)
    {
        for (AppMemberStatus e : AppMemberStatus.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static AppMemberStatus getByTitle(String title)
    {
        for (AppMemberStatus e : AppMemberStatus.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
