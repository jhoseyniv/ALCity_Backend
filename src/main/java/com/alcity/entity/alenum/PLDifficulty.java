package com.alcity.entity.alenum;



public enum PLDifficulty {

    Easy,
    Medium,
    Hard;

    public static PLDifficulty getById(long id)
    {
        for (PLDifficulty e : PLDifficulty.values())
        {
            if (id == e.ordinal()) return e;
        }
        return Easy;
    }

    public static PLDifficulty getByTitle(String title)
    {
        for (PLDifficulty e : PLDifficulty.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        return PLDifficulty.Easy;
    }

    }
