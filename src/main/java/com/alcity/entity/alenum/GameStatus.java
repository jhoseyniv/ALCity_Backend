package com.alcity.entity.alenum;


public enum GameStatus  {
    Canceled ,
    Playing ,
    Unknown,
    Win,
    Lose,
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
        if(title == null || title.isEmpty()) return GameStatus.Not_Started;
        for (GameStatus e : GameStatus.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

    }
