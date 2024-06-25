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
        throw new IllegalArgumentException("no");
    }

    public static PLDifficulty getByTitle(String title)
    {
        for (PLDifficulty e : PLDifficulty.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

    }
