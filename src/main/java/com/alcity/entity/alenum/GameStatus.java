package com.alcity.entity.alenum;


public enum GameStatus  {
    Canceled ,
    Playing ,
    Unknown,
    Completed,
    Not_Started,
    Paused;

    public static GameStatus getById(long id)
    {
        for (GameStatus e : GameStatus.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static GameStatus getByTitle(String title)
    {
        for (GameStatus e : GameStatus.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

    }
